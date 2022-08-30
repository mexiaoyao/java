package com.youlianmei.controller;

import com.youlianmei.model.User;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //@RestController相当于@Controller和@ResponseBody合在一起的作用；
@RequestMapping(value = "/")
public class IndexController {

    @GetMapping("indexCode")
    public String code(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
        return "123";
    }

    @GetMapping("userList")
    public Object userList(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
        Map<String,Object> result = new HashMap<>();
        List<User> list = new ArrayList<User>();
        result.put("code",1000);
        result.put("list",list);
        result.put("total",1000);
        result.put("page",1);
        return result;
    }
}
