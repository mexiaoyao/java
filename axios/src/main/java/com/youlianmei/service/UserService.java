package com.youlianmei.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlianmei.model.User;

public interface UserService extends IService<User> {

    //根据账号查找用户
    User findByUsername(String username);

    Page<User> pageListUser(Integer current, Integer limit, User user);

}
