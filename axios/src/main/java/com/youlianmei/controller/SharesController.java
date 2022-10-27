package com.youlianmei.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlianmei.dao.FinanceIntroDao;
import com.youlianmei.model.FinanceIntro;
import com.youlianmei.service.FinanceIntroService;
import com.youlianmei.service.SharesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController //@RestController相当于@Controller和@ResponseBody合在一起的作用；
@RequestMapping(value = "shares")
public class SharesController {

    @Resource
    SharesService sharesService;

    /**
     * 股票列表
     * **/
    @PostMapping("list")
    public Object list(@RequestBody FinanceIntroDao financeIntro) throws Exception {
        Map<String,Object> obj = new HashMap<>();

        obj.put("code","10000");

        return obj;
    }


}
