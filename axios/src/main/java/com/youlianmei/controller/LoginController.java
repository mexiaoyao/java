package com.youlianmei.controller;

import com.wf.captcha.base.Captcha;
import com.youlianmei.dao.LoginDao;
import com.youlianmei.model.LoginCodeEnum;
import com.youlianmei.model.LoginProperties;
import com.youlianmei.model.User;
import com.youlianmei.msg.Msg;
import com.youlianmei.service.UserService;
import com.youlianmei.utils.Md5;
import com.youlianmei.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {

    @Autowired
    LoginProperties loginProperties;

    @Autowired
    UserService userService;

    //通过java去操作redis缓存string类型的数据
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/code")
    public Msg getCode(){
        Msg msg=null;
        Captcha captcha = loginProperties.getCaptcha();

        String uuid = "code-key-"+ StringUtils.getUUid(2);

        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if(captcha.getCharType()-1 == LoginCodeEnum.ARITHMETIC.ordinal() && captchaValue.contains(".")){
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        //redisUtils.set(uuid,captchaValue,loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);

        // 验证码信息
        msg=Msg.success("验证码获取成功").add("img",captcha.toBase64()).add("uuid",uuid);
        return msg;
    }

    //需要权限为ROLE_USER才能访问/index
    @RequiresPermissions("ROLE_USER")
    @GetMapping("/index")
    public Msg index(@RequestHeader String token){
        return Msg.success("index");
    }

    //需要权限ROLE_ADMIN才能访问hello
    @RequiresPermissions("ROLE_ADMIN")
    @GetMapping("/hello")
    public Msg hello(@RequestHeader String token){
        return Msg.success("hello");
    }

    //登录接口
    @PostMapping("/login")
    public Msg login(@RequestBody LoginDao loginDao){
        Msg msg=null;
        if(StringUtils.isEmpty(loginDao.getUsername())){
            msg = Msg.fail("用户名为空");
            return msg;
        }
        if(StringUtils.isEmpty(loginDao.getUserpwd())){
            msg = Msg.fail("密码为空");
            return msg;
        }
        if(StringUtils.isEmpty(loginDao.getCode())){
            msg = Msg.fail("验证码为空");
            return msg;
        }
        if(StringUtils.isEmpty(loginDao.getUuid())){
            msg = Msg.fail("参数获取异常，请刷新，或者联系网站管理员！");
            return msg;
        }
        if(null==loginDao.getIsCheck() || !loginDao.getIsCheck()){
            msg = Msg.fail("请阅读风险提示，并确认！");
            return msg;
        }
        User user = userService.findByUsername(loginDao.getUsername());

        String pss = Md5.encrypt2ToMD5(loginDao.getUserpwd(), user.getSalt(), 3);
        if (user == null) {
            msg = Msg.fail("账号错误");
        } else if (!pss.equals(user.getPwd())) {
            msg = Msg.fail("密码错误");
        } else {
            //通过UUID生成token字符串,并将其以string类型的数据保存在redis缓存中，key为token，value为username
            String token= StringUtils.getUUid(1);
            stringRedisTemplate.opsForValue().set(token, loginDao.getUsername(),3600, TimeUnit.SECONDS);
            msg=Msg.success("登录成功").add("token",token).add("username",user.getName());
        }
        return msg;
    }

    //注销接口
    @PostMapping("/logout")
    public Msg logout(@RequestHeader("token")String token){
        //删除redis缓存中的token
        stringRedisTemplate.delete(token);
        return Msg.success("注销成功");
    }
}
