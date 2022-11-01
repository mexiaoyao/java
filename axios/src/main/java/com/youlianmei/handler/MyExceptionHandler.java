package com.youlianmei.handler;

import com.youlianmei.msg.Msg;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice可以实现全局异常处理，可以简单理解为增强了的controller
@ControllerAdvice
public class MyExceptionHandler {

    //捕获AuthorizationException的异常
    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Msg handleException(AuthorizationException e) {
        Msg msg=Msg.denyAccess("权限不足呀！！！！！");
        return msg;
    }
}
