package com.youlianmei.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlianmei.dao.GradeDictDao;
import com.youlianmei.dao.GradeQuestionDao;
import com.youlianmei.model.GradeDict;
import com.youlianmei.model.GradeQuestion;
import com.youlianmei.model.Shares;
import com.youlianmei.service.GradeDictService;
import com.youlianmei.service.GradeQuestionService;
import com.youlianmei.utils.FileUploadUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        obj.put("code",10000);
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
            obj.put("code", 20000);
            return obj;
        }
        Integer result = gradeQuestionService.deleteBathById(list);
        obj.put("code", result == 1 ? 10000 : 20000);
        return obj;
    }

    /**
     * 新增/编辑
     * **/
    @PostMapping("actionDo")
    public Object actionDo(@RequestBody GradeQuestionDao gradeQuestionDao) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        gradeQuestionDao.setCreateName("admin");
        Integer result = gradeQuestionService.actionDo(gradeQuestionDao);
        obj.put("code", result == 1 ? 10000 : 20000);
        return obj;
    }

    @PostMapping(value = "/upload")
    public Object upload(@RequestParam("fileName") MultipartFile fileName, GradeQuestionDao gradeQuestionDao) {
        Map<String,Object> obj = new HashMap<>();
        if(FileUploadUtils.isEmpty(fileName)){
            obj.put("code","20000");
            obj.put("msg","空文件");
            return obj;
        }
        if(!FileUploadUtils.isExcel(fileName)){
            obj.put("code","20000");
            obj.put("msg","只支持EXCEL文件");
            return obj;
        }
        try {
            List<GradeQuestion> list = EasyExcel.read(fileName.getInputStream())
                    .head(GradeQuestion.class)
                    .sheet()
                    .doReadSync();

            obj.put("code","10000");
        }catch(Exception e){
            obj.put("code","20000");
            obj.put("msg","导入失败");
        }
        return obj;
    }





}
