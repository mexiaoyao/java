package com.youlianmei.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlianmei.model.FinanceIntro;
import com.youlianmei.model.User;
import com.youlianmei.service.FinanceIntroService;
import com.youlianmei.service.UserService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //@RestController相当于@Controller和@ResponseBody合在一起的作用；
@RequestMapping(value = "finance")
public class FinanceController {

    @Resource
    FinanceIntroService financeIntroService;


    @PostMapping("list")
    public Object list(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
        Map<String,Object> result = new HashMap<>();
        /*User user = new User();
        user.setName("11");*/
        List<FinanceIntro> list = financeIntroService.list();
        result.put("code",1000);
        result.put("list",list);
        result.put("total",1000);
        result.put("page",1);
        return result;
    }
}
