package com.youlianmei.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlianmei.mapper.UserMapper;
import com.youlianmei.model.User;
import com.youlianmei.service.UserService;
import com.youlianmei.utils.StringUtils;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Page<User> pageListUser(Integer current, Integer limit, User user){
        //创建Page对象
        Page<User> eduTeacherPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //多条件组合查询
        //判断条件值是否为空,如果不为空拼接条件
        if (!StringUtils.isEmpty(user.getName())){
            //构建条件 模糊查询
            wrapper.like("name",user.getName());
        }
        if (null!=user.getCreateTime()){
            //大于等于
            wrapper.ge("create_time",user.getCreateTime());
        }
        if (null!=user.getUpdateTime()){
            //小于等于
            wrapper.le("update_time",user.getUpdateTime());
        }
        wrapper.orderByDesc("create_time");
        //调用mybatis plus分页方法进行查询
        return baseMapper.selectPage(eduTeacherPage,wrapper);
    }

}
