package com.youlianmei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youlianmei.dao.GradeDictDao;
import com.youlianmei.model.GradeDict;

import java.util.List;

public interface GradeDictService extends IService<GradeDict> {

    List<GradeDict> list(GradeDictDao dao);

    List<GradeDict> listAll();

    Integer actionDo(GradeDictDao dao);

    Integer deleteById(String id);

    GradeDict selectById(String id);

}
