package com.youlianmei.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlianmei.dao.GradeDictDao;
import com.youlianmei.dao.GradeQuestionDao;
import com.youlianmei.model.GradeDict;
import com.youlianmei.model.GradeQuestion;
import com.youlianmei.service.GradeDictService;
import com.youlianmei.service.GradeQuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //@RestController相当于@Controller和@ResponseBody合在一起的作用；
@RequestMapping(value = "gradeQuestion")
public class GradeQuestionController {

    @Resource
    GradeQuestionService gradeQuestionService;


    /**
     * 列表
     * **/
    @PostMapping("list")
    public Object list(@RequestBody GradeQuestionDao gradeQuestionDao) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        Page<GradeQuestion> result = gradeQuestionService.pageList(gradeQuestionDao);
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
    public Object delete(@RequestBody List<String> list) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        if(null==list || list.size()==0){
            obj.put("code", 0);
            return obj;
        }
        Integer result = gradeQuestionService.deleteBathById(list);
        obj.put("code", result == 1 ? 10000 : 0);
        return obj;
    }

    /**
     * 新增/编辑
     * **/
    @PostMapping("actionDo")
    public Object actionDo(@RequestBody GradeQuestionDao gradeQuestionDao) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        Integer result = gradeQuestionService.actionDo(gradeQuestionDao);
        obj.put("code", result == 1 ? 10000 : 0);
        return obj;
    }


}
