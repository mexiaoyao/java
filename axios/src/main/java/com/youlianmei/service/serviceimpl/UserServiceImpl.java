package com.youlianmei.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlianmei.mapper.UserMapper;
import com.youlianmei.model.User;
import com.youlianmei.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



}
