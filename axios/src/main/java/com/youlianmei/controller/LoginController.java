package com.youlianmei.controller;

import com.youlianmei.dao.UserLogin;
import com.youlianmei.model.User;
import com.youlianmei.msg.Msg;
import com.youlianmei.service.UserService;
import com.youlianmei.utils.ConstantsUtils;
import com.youlianmei.utils.Md5;
import com.youlianmei.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    UserService userService;

    //通过java去操作redis缓存string类型的数据
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //需要权限为ROLE_USER才能访问/index
    @RequiresPermissions("ROLE_USER")
    @GetMapping("/index")
    public Msg index(@RequestHeader String token){
        return Msg.success("index");
    }

    @GetMapping("/ces01")
    public String ces01(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
        return "ces01";
    }

    //需要权限ROLE_ADMIN才能访问hello
    @RequiresPermissions("ROLE_ADMIN")
    @GetMapping("/hello")
    public Msg hello(@RequestHeader String token){
        return Msg.success("hello");
    }

    //登录接口
    @PostMapping("/login")
    public Msg login(@RequestBody UserLogin user){
        if(StringUtils.isEmpty(user.getUserName())){
            return Msg.fail("账号不可为空");
        }
        if(StringUtils.isEmpty(user.getUserPwd())){
            return Msg.fail("密码不可为空");
        }

        //判断是否登录
        //https://www.dandelioncloud.cn/article/details/1514775782571458562
        subject.isAuthenticated();

        //shiro验证
        Subject subject= SecurityUtils.getSubject();
        //根据用户名密码生成一个令牌
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUserName(), user.getUserPwd());
        try {
            subject.login(token);    //执行登录操作
            //通过UUID生成token字符串,并将其以string类型的数据保存在redis缓存中，key为token，value为username
            String tokenRedis= UUID.randomUUID().toString().replaceAll("-","");
            //stringRedisTemplate.opsForValue().set(tokenRedis, user.getUsername(), ConstantsUtils.REDIS_OVER_TIME, TimeUnit.SECONDS);
            return Msg.success("登录成功").add("token",tokenRedis);
        } catch (UnknownAccountException e) {
            log.info("登录用户不存在",user.getUserName());
            return Msg.fail("用户不存在");
        } catch (IncorrectCredentialsException e) {
            log.info("登录密码错误");
            return Msg.fail("登录密码错误");
        }catch (AuthenticationException e) {
            log.warn("用户登录异常:" + e.getMessage());
            return Msg.fail("用户登录异常");
        }
    }

    //注销接口
    @PostMapping("/logout")
    public Msg logout(@RequestHeader("token")String token){
        //删除redis缓存中的token
        stringRedisTemplate.delete(token);
        return Msg.success("注销成功");
    }
}
