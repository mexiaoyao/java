package com.youlianmei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youlianmei.dao.GradeDictDao;
import com.youlianmei.model.GradeDict;

import java.util.List;

public interface GradeDictService extends IService<GradeDict> {

    List<GradeDict> list(GradeDictDao dao);

    List<GradeDict> listAll(Integer type);

    List<GradeDict> listAllByParentId(Integer parentId);

    Integer actionDo(GradeDictDao dao);

    Integer deleteById(Integer id);

    GradeDict selectById(Integer id);


}
