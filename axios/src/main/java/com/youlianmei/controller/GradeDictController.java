package com.youlianmei.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlianmei.dao.GradeQuestionDao;
import com.youlianmei.dao.GradeDictDao;
import com.youlianmei.model.GradeQuestion;
import com.youlianmei.model.GradeDict;
import com.youlianmei.service.GradeQuestionService;
import com.youlianmei.service.GradeDictService;
import com.youlianmei.service.SharesService;
import com.youlianmei.utils.ConstantsUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController //@RestController相当于@Controller和@ResponseBody合在一起的作用；
@RequestMapping(value = "gradeDict")
public class GradeDictController {

    @Resource
    GradeDictService gradeDictService;


    /**
     * 列表
     * **/
    @PostMapping("list")
    public Object list(@RequestBody GradeDictDao gradeDictDao) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        Page<GradeDict> result = gradeDictService.pageList(gradeDictDao);
        obj.put("code","10000");
        obj.put("list",result.getRecords());
        obj.put("total",result.getTotal());
        obj.put("page",result.getCurrent());
        return obj;
    }

    /**
     * 删除
     * **/
    @PostMapping("delete")
    public Object delete(@RequestBody GradeDictDao gradeDictDao) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        if(null==gradeDictDao.getId()){
            obj.put("code", 0);
            return obj;
        }
        //如果数据就禁止删除--待开发
        Integer result = gradeDictService.deleteById(gradeDictDao.getId());
        obj.put("code", result == 1 ? 10000 : 0);
        return obj;
    }

    /**
     * 新增/编辑
     * **/
    @PostMapping("actionDo")
    public Object actionDo(@RequestBody GradeDictDao gradeDictDao) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        Integer result = gradeDictService.actionDo(gradeDictDao);
        obj.put("code", result == 1 ? 10000 : 0);
        return obj;
    }


}