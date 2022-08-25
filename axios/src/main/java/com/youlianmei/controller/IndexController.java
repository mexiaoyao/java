package com.youlianmei.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController //@RestController相当于@Controller和@ResponseBody合在一起的作用；
@RequestMapping(value = "/")
public class IndexController {

    @GetMapping("indexCode")
    public String code(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
        return "123";
    }
}
