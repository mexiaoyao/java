package com.youlianmei.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlianmei.model.GradeQuestion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//在对应的Mapper 接口上 基础基本的 BaseMapper<T> T是对应的pojo类
@Repository   //告诉容器你是持久层的 @Repository是spring提供的注释，能够将该类注册成Bean
public interface GradeQuestionMapper extends BaseMapper<GradeQuestion> {

    @Insert("<script>" +
    "INSERT INTO t_grade_question(" +
            "id," +
            "dict_task_path,dict_task_path_name,dict_grade_path,dict_grade_path_name,dict_source_path,dict_source_path_name,dict_type_path,dict_type_path_name," +
            "intro,question,answers,answer_right," +
            "type,create_time,update_time,img_url,create_name,remarks" +
            ")VALUES" +
    "<foreach collection='GradeQuestionList' item='GradeQuestion'   separator=','> " +
    "(" +
            "#{GradeQuestion.id}," +
            "#{GradeQuestion.dictTaskPath},#{GradeQuestion.dictTaskPathName},#{GradeQuestion.dictGradePath},#{GradeQuestion.dictGradePathName},#{GradeQuestion.dictSourcePath},#{GradeQuestion.dictSourcePathName},#{GradeQuestion.dictTypePath},#{GradeQuestion.dictTypePathName}," +
            "#{GradeQuestion.intro},#{GradeQuestion.question},#{GradeQuestion.answers},#{GradeQuestion.answerRight}," +
            "#{GradeQuestion.type},#{GradeQuestion.createTime},#{GradeQuestion.updateTime},#{GradeQuestion.imgUrl},#{GradeQuestion.createName},#{GradeQuestion.remarks}" +
            ")" +
    "</foreach> " +
    "</script>")
    boolean insertBatch(@Param("GradeQuestionList") List<GradeQuestion> GradeQuestionList);

}
