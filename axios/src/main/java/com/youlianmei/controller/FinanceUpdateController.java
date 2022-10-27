package com.youlianmei.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlianmei.dao.FinanceIntroDao;
import com.youlianmei.dao.FinanceUpdateDao;
import com.youlianmei.model.FinanceIntro;
import com.youlianmei.model.FinanceUpdate;
import com.youlianmei.service.FinanceIntroService;
import com.youlianmei.service.FinanceUpdateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController //@RestController相当于@Controller和@ResponseBody合在一起的作用；
@RequestMapping(value = "financeUpdate")
public class FinanceUpdateController {

    @Resource
    FinanceUpdateService financeUpdateService;

    /**
     * 股票列表
     * **/
    @PostMapping("list")
    public Object list(@RequestBody FinanceUpdateDao financeUpdateDao) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        Page<FinanceUpdate> result = financeUpdateService.pageList(financeUpdateDao);
        obj.put("code","10000");
        obj.put("list",result.getRecords());
        obj.put("total",result.getTotal());
        obj.put("page",result.getCurrent());
        return obj;
    }

    /**
     * 修改状态---上线后，删掉此接口
     * **/
    @PostMapping("status")
    public Object status(@RequestBody FinanceUpdateDao financeUpdateDao) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        if(null==financeUpdateDao.getId() || null==financeUpdateDao.getStatus() || null!=financeUpdateDao.getRemarks()){
            obj.put("code", 0);
            return obj;
        }
        Integer result = financeUpdateService.financeUpdateStatus(financeUpdateDao.getId(),financeUpdateDao.getStatus(),financeUpdateDao.getRemarks());
        obj.put("code", result == 1 ? 10000 : 0);
        return obj;
    }

    /**
     * 新增
     * **/
    @PostMapping("add")
    public Object add(@RequestBody FinanceUpdateDao financeUpdateDao) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        Integer result = financeUpdateService.addFinanceUpdate(financeUpdateDao);
        obj.put("code", result == 1 ? 10000 : 0);
        return obj;
    }

    /**
     * 数据重新获取，上线后 需删除此接口
     * **/
    @PostMapping("getAgain")
    public Object getAgain(@RequestBody FinanceUpdateDao financeUpdateDao) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        if(null==financeUpdateDao.getId()){
            obj.put("code", 0);
            return obj;
        }
        Integer result = financeUpdateService.getAgain(financeUpdateDao);
        obj.put("code", result == 1 ? 10000 : 0);
        return obj;
    }


}
