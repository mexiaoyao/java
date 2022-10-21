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

    /**
     * 股票列表
     * **/
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

    /**
     * 上下架
     * **/
    @PostMapping("status")
    public Object status(@RequestBody FinanceIntroDao financeIntro) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        if(null==financeIntro.getId() || null==financeIntro.getStatus()){
            obj.put("code", 0);
            return obj;
        }
        Integer result = financeIntroService.financeStatus(financeIntro.getId(),financeIntro.getStatus());
        obj.put("code", result == 1 ? 10000 : 0);
        return obj;
    }

    /**
     * 删除
     * **/
    @PostMapping("delete")
    public Object delete(@RequestBody FinanceIntroDao financeIntro) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        if(null==financeIntro.getId()){
            obj.put("code", 0);
            return obj;
        }
        Integer result = financeIntroService.financeDelete(financeIntro.getId());
        obj.put("code", result == 1 ? 10000 : 0);
        return obj;
    }

    /**
     * 新增/编辑
     * **/
    @PostMapping("actionFinance")
    public Object actionFinance(@RequestBody FinanceIntroDao financeIntro) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        Integer result = financeIntroService.actionFinance(financeIntro);
        obj.put("code", result == 1 ? 10000 : 0);
        return obj;
    }


}
