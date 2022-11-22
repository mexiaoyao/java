package com.youlianmei.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlianmei.dao.GradeQuestionDao;
import com.youlianmei.model.GradeQuestion;

import java.util.List;

public interface GradeQuestionService extends IService<GradeQuestion> {

    Page<GradeQuestion> pageList(GradeQuestionDao GradeQuestion);

    Integer deleteBathById(List<String> list);

    Integer actionDo(GradeQuestionDao dao);

    GradeQuestion selectById(String id);

}
