package com.youlianmei.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlianmei.dao.GradeQuestionDao;
import com.youlianmei.dao.GradeQuestionImportDao;
import com.youlianmei.dao.ListDao;
import com.youlianmei.model.GradeQuestion;
import com.youlianmei.service.GradeQuestionService;
import com.youlianmei.utils.DateUtils;
import com.youlianmei.utils.FileUploadUtils;
import com.youlianmei.utils.ListUtils;
import com.youlianmei.utils.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
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
     * 获取符合条件的考题数量
     * **/
    @PostMapping("taotalnum")
    public Object taotalnum(@RequestBody GradeQuestionDao gradeQuestionDao) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        Integer result = gradeQuestionService.pageNum(gradeQuestionDao);
        obj.put("code",10000);
        obj.put("total",result);
        return obj;
    }

    /**
     * 获取符合条件的考题数量
     * **/
    @PostMapping("anyList")
    public Object anyList(@RequestBody GradeQuestionDao gradeQuestionDao) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        List<GradeQuestion> result = gradeQuestionService.listany(gradeQuestionDao);
        obj.put("code",10000);
        obj.put("total",result);
        return obj;
    }

    /**
     * 删除
     * **/
    @PostMapping("delete")
    public Object delete(@RequestBody ListDao listDao) throws Exception {
        Map<String,Object> obj = new HashMap<>();
        if(null==listDao.getStringList() || listDao.getStringList().size()==0){
            obj.put("code", 20000);
            obj.put("msg", "参数为空");
            return obj;
        }
        Integer result = gradeQuestionService.deleteBathById(listDao.getStringList());
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
        if( ListUtils.isEmpty(gradeQuestionDao.getDictTaskId()) ){
            obj.put("code","20000");
            obj.put("msg","试卷种类为空");
            return obj;
        }
        if( ListUtils.isEmpty(gradeQuestionDao.getDictGradeId()) ){
            obj.put("code","20000");
            obj.put("msg","所属年级为空");
            return obj;
        }
        if(  ListUtils.isEmpty(gradeQuestionDao.getDictSourceId()) ){
            obj.put("code","20000");
            obj.put("msg","题目来源为空");
            return obj;
        }
        if(  ListUtils.isEmpty(gradeQuestionDao.getDictTypeId()) ){
            obj.put("code","20000");
            obj.put("msg","题目类型为空");
            return obj;
        }
        if( null== gradeQuestionDao.getType() ){
            obj.put("code","20000");
            obj.put("msg","考题种类为空");
            return obj;
        }
        try {
            List<GradeQuestionImportDao> list = EasyExcel.read(fileName.getInputStream())
                    .head(GradeQuestionImportDao.class)
                    .sheet()
                    .doReadSync();

            if(list.isEmpty()){
                obj.put("code","20000");
                obj.put("msg","请上传非空文件，或者格式错误");
                return obj;
            }
            List<GradeQuestionDao> gradeQuestionDaoList = new ArrayList<GradeQuestionDao>();
            //对应dictTaskPath
            List<Integer> dictTaskId = gradeQuestionDao.getDictTaskId();
            List<String> dictTaskName = gradeQuestionDao.getDictTaskName();
            //对应dictGradePath
            List<Integer> dictGradeId = gradeQuestionDao.getDictGradeId();
            List<String> dictGradeName = gradeQuestionDao.getDictGradeName();
            //对应dictSourcePath
            List<Integer> dictSourceId = gradeQuestionDao.getDictSourceId();
            List<String> dictSourceName = gradeQuestionDao.getDictSourceName();
            //对应dictTypePath
            List<Integer> dictTypeId = gradeQuestionDao.getDictTypeId();
            List<String> dictTypeName = gradeQuestionDao.getDictTypeName();

            Byte type = gradeQuestionDao.getType();

            for( GradeQuestionImportDao gradeQuestionImportDao : list ){
                GradeQuestionDao temp = new GradeQuestionDao();
                temp.setDictTaskId(dictTaskId);
                temp.setDictTaskName(dictTaskName);
                temp.setDictGradeId(dictGradeId);
                temp.setDictGradeName(dictGradeName);
                temp.setDictSourceId(dictSourceId);
                temp.setDictSourceName(dictSourceName);
                temp.setDictTypeId(dictTypeId);
                temp.setDictTypeName(dictTypeName);
                temp.setType(type);
                if( StringUtils.isNotEmpty(gradeQuestionImportDao.getIntro()) ) temp.setIntro( gradeQuestionImportDao.getIntro().trim());
                if( StringUtils.isNotEmpty(gradeQuestionImportDao.getQuestion()) ) temp.setQuestion( gradeQuestionImportDao.getQuestion().trim());
                if( StringUtils.isNotEmpty(gradeQuestionImportDao.getAnswers()) ) temp.setAnswers( gradeQuestionImportDao.getAnswers().trim());
                if( StringUtils.isNotEmpty(gradeQuestionImportDao.getAnswerRight()) ) temp.setAnswerRight( gradeQuestionImportDao.getAnswerRight().trim());
                if( StringUtils.isNotEmpty(gradeQuestionImportDao.getRemarks()) ) temp.setRemarks( gradeQuestionImportDao.getRemarks().trim());

                temp.setId(StringUtils.getUUid());
                temp.setCreateTime(DateUtils.getDate());
                temp.setUpdateTime(DateUtils.getDate());

                temp.setUsedNum(0);
                temp.setGoodNum(0);
                temp.setPoorNum(0);
                temp.setShareNum(0);

                temp.setCreateName("admin");
                gradeQuestionDaoList.add(temp);
            }
            Boolean ret = gradeQuestionService.insertBatch(gradeQuestionDaoList);
            if(ret){
                obj.put("code","10000");
                obj.put("msg","导入成功");
            }else{
                obj.put("code","20000");
                obj.put("msg","导入失败");
            }
        }catch(Exception e){
            obj.put("code","20000");
            obj.put("msg","导入失败");
        }
        return obj;
    }





}
