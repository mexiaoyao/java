package com.youlianmei.controller;

import com.wf.captcha.base.Captcha;
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
import java.util.UUID;
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
    public Object getCode(){

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
        Map<String,Object> obj = new HashMap<>();
        obj.put("code", 10000);
        obj.put("img",captcha.toBase64());
        obj.put("uuid",uuid);
        return obj;
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
    public Msg login(@RequestParam("username")String username, @RequestParam("password")String password){
        User user = userService.findByUsername(username);
        Msg msg=null;
        String pss = Md5.encrypt2ToMD5(password, user.getSalt(), 3);
        if (user == null) {
            msg = Msg.fail("账号错误");
        } else if (!pss.equals(user.getPwd())) {
            msg = Msg.fail("密码错误");
        } else {
            //通过UUID生成token字符串,并将其以string类型的数据保存在redis缓存中，key为token，value为username
            String token= StringUtils.getUUid(1);
            stringRedisTemplate.opsForValue().set(token,username,3600, TimeUnit.SECONDS);
            msg=Msg.success("登录成功").add("token",token);
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
