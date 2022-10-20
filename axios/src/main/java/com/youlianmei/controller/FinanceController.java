package com.youlianmei.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlianmei.dao.FinanceIntroDao;
import com.youlianmei.model.FinanceIntro;
import com.youlianmei.service.FinanceIntroService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController //@RestController相当于@Controller和@ResponseBody合在一起的作用；
@RequestMapping(value = "finance")
public class FinanceController {

    @Resource
    FinanceIntroService financeIntroService;

    @PostMapping("list")
    public Object list(@RequestBody FinanceIntroDao financeIntro) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        Page<FinanceIntro> result = financeIntroService.pageList(financeIntro);
        obj.put("code","10000");
        obj.put("list",result.getRecords());
        obj.put("total",result.getTotal());
        obj.put("page",result.getCurrent());
        return obj;
    }
}
