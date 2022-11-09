package com.youlianmei.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
public class LoginDao {

    //用户名
    private String username;

    //用户密码
    private String userpwd;

    //code的session键名
    private String uuid;

    //验证码
    private String code;

    //风险提示是否已读
    private Boolean isCheck;


}
