package com.youlianmei.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlianmei.model.User;
import com.youlianmei.service.UserService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //@RestController相当于@Controller和@ResponseBody合在一起的作用；
@RequestMapping(value = "/")
public class IndexController {

    @Resource
    UserService userService;

    @GetMapping("indexCode")
    public String code(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
        return "123";
    }

    @GetMapping("userList")
    public Object userList(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
        Map<String,Object> result = new HashMap<>();
        /*User user = new User();
        user.setName("11");*/
        List<User> list = userService.list();
        result.put("code",1000);
        result.put("list",list);
        result.put("total",1000);
        result.put("page",1);
        return result;
    }

    @GetMapping("userInsert")
    public Object userInsert(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
        Map<String,Object> result = new HashMap<>();
        User user = new User();
        user.setName("11");
        user.setAge(15);
        Boolean ret = userService.save(user);
        result.put("code",1000);
        result.put("ret",ret);
        return result;
    }

    @GetMapping("listPage")
    public Object listPage(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
        Map<String,Object> result = new HashMap<>();
        User user = new User();
        user.setName("11");
        user.setAge(15);
        Page<User> ret = userService.getBaseMapper().selectPage(new Page<>(1, 2),null);
        result.put("code",1000);
        result.put("current",ret.getCurrent());
        result.put("list",ret.getRecords());
        result.put("total",ret.getTotal());
        return result;
    }
}
