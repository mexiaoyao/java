package com.youlianmei.service;

import org.apache.shiro.realm.AuthorizingRealm;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerRealm extends AuthorizingRealm {
    @Autowired
    private UserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //参数authenticationToken就是传递的subject.login(token)中的参数
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        //从token中获取用户名
        String username=token.getUsername();
        //根据用户名查询用户的安全数据（数据库中的用户数据）
        User user= userDao.findByUsername(username);
        if(user==null){
            return null;
        }

        AuthenticationInfo info=new SimpleAuthenticationInfo(//将密码封装为shiro需要的格式
                username,//当前用户用户名,跟上面的doGetAuthorizationInfo方法是对应的
                user.getPassword(),//从数据库查询出来的安全密码
                getName());
        return info;
    }
}
