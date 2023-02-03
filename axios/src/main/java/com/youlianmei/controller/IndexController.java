package com.youlianmei.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlianmei.model.User;
import com.youlianmei.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    //需要权限ROLE_ADMIN才能访问hello
    @RequiresPermissions("ROLE_IndexCode")
    @GetMapping("indexCode")
    public String indexCode(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
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
        Boolean ret = userService.save(user);
        result.put("code",1000);
        result.put("ret",ret);
        return result;
    }

    @GetMapping("listPage")
    public Object listPage(
            @RequestParam("current") Integer current,       //当前页
            @RequestParam("limit") Integer limit,           //要查询记录数
            User user                                       //查询条件封装的对象
    ) throws Exception {
        Map<String,Object> result = new HashMap<>();
        Page<User> ret = userService.pageListUser(current, limit, user);
        result.put("code",1000);
        result.put("current",ret.getCurrent());
        result.put("list",ret.getRecords());
        result.put("total",ret.getTotal());
        return result;
    }
}
