package com.youlianmei.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlianmei.dao.GradeQuestionDao;
import com.youlianmei.mapper.GradeQuestionMapper;
import com.youlianmei.model.GradeQuestion;
import com.youlianmei.service.GradeQuestionService;
import com.youlianmei.utils.DateUtils;
import com.youlianmei.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class GradeQuestionServiceImpl extends ServiceImpl<GradeQuestionMapper, GradeQuestion> implements GradeQuestionService {

    @Override
    @Transactional(rollbackFor = Exception.class) //一般编辑，添加，删除（即对数据库进行操作）时用到，此处只是展示案例
    public Page<GradeQuestion> pageList(GradeQuestionDao dao){
        StringUtils.questionPath(dao,2);
        int pageNo = null==dao.getPageNo() ? 1 : dao.getPageNo();
        int pageSize = null==dao.getPageSize() ? 10 : dao.getPageSize();
        //创建Page对象
        Page<GradeQuestion> eduTeacherPage = new Page<>(pageNo,pageSize);
        //构建条件
        QueryWrapper<GradeQuestion> wrapper = new QueryWrapper<>();
        //多条件组合查询
        if(StringUtils.isNotEmpty(dao.getId())){
            wrapper.eq("id",dao.getId());
        }
        if(StringUtils.isNotEmpty(dao.getDictTypePath())){
            wrapper.like("dict_type_path",dao.getDictTypePath());
        }
        if(StringUtils.isNotEmpty(dao.getDictSourcePath())){
            wrapper.like("dict_source_path",dao.getDictSourcePath());
        }
        if(StringUtils.isNotEmpty(dao.getDictGradePath())){
            wrapper.like("dict_grade_path",dao.getDictGradePath());
        }
        if(StringUtils.isNotEmpty(dao.getQuestion())){
            wrapper.like("question",dao.getQuestion());
        }
        if (StringUtils.isNotEmpty(dao.getAnswers())){
            wrapper.like("answers",dao.getAnswers());
        }
        if (StringUtils.isNotEmpty(dao.getAnswerRight())){
            wrapper.like("answerRight",dao.getAnswerRight());
        }
        if (null!=dao.getCreateTimeStart()){
            //大于等于
            wrapper.ge("create_time",dao.getCreateTimeStart());
        }
        if (null!=dao.getCreateTimeEnd()){
            dao.setCreateTimeEnd(DateUtils.dateAddOneDay(dao.getCreateTimeEnd()));
            //小于
            wrapper.lt("create_time", dao.getCreateTimeEnd());
        }
        if (null!=dao.getUpdateTimeStart()){
            //大于等于
            wrapper.ge("update_time",dao.getUpdateTimeStart());
        }
        if (null!=dao.getUpdateTimeEnd()){
            dao.setUpdateTimeEnd(DateUtils.dateAddOneDay(dao.getUpdateTimeEnd()));
            //小于
            wrapper.lt("update_time",dao.getUpdateTimeEnd());
        }
        if (!StringUtils.isEmpty(dao.getRemarks())){
            wrapper.like("remarks",dao.getRemarks());
        }
        //多条件组合查询
        wrapper.orderByDesc("create_time");
        //调用mybatis plus分页方法进行查询
        return baseMapper.selectPage(eduTeacherPage,wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer actionDo(GradeQuestionDao dao){
        StringUtils.questionPath(dao,1);
        GradeQuestion insert = new GradeQuestion();
        BeanUtils.copyProperties(dao, insert);
        return StringUtils.isEmpty(insert.getId()) ? baseMapper.insert(insert) : baseMapper.updateById(insert);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteBathById(List<String> list){
        try {
            for (String str : list) {
                GradeQuestion gradeQuestion = new GradeQuestion();
                gradeQuestion.setId(str);
                baseMapper.deleteById(str);
            }
        }catch(Exception e){
            return 0;
        }
        return 1;
    }

    @Override
    public GradeQuestion selectById(String id){
        return baseMapper.selectById(id);
    }

}
