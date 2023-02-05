package com.youlianmei.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Msg {
    Integer code;   //错误码
    String Message; //消息提示
    Map<String,Object> data=new HashMap<String,Object>();   //数据

    //成功
    public static Msg success(String message){
        Msg result=new Msg();
        result.setCode(10000);
        result.setMessage(message);
        return result;
    }

    //未登录
    public static Msg noLogin(String message){
        Msg result=new Msg();
        result.setCode(20000);
        result.setMessage(message);
        return result;
    }

    //无权访问
    public static Msg denyAccess(String message){
        Msg result=new Msg();
        result.setCode(30000);
        result.setMessage(message);
        return result;
    }

    //客户端操作失败
    public static Msg fail(String message){
        Msg result=new Msg();
        result.setCode(40000);
        result.setMessage(message);
        return result;
    }

    public Msg add(String key,Object value){
        this.data.put(key,value);
        return this;
    }
}