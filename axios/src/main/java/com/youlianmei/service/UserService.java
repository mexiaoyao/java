package com.youlianmei.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlianmei.model.User;

import java.util.List;

public interface UserService extends IService<User> {

    Page<User> pageListUser(Integer current, Integer limit, User user);

}
