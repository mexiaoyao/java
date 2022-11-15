package com.youlianmei.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlianmei.dao.GradeDictDao;
import com.youlianmei.mapper.GradeDictMapper;
import com.youlianmei.model.GradeDict;
import com.youlianmei.service.GradeDictService;
import com.youlianmei.utils.DateUtils;
import com.youlianmei.utils.ListUtils;
import com.youlianmei.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class GradeDictServiceImpl extends ServiceImpl<GradeDictMapper, GradeDict> implements GradeDictService {

    @Override
    @Transactional(rollbackFor = Exception.class) //一般编辑，添加，删除（即对数据库进行操作）时用到，此处只是展示案例
    public List<GradeDict> list(GradeDictDao dao){
        //构建条件
        QueryWrapper<GradeDict> wrapper = new QueryWrapper<>();
        //多条件组合查询
        if(StringUtils.isNotEmpty(dao.getId())){
            wrapper.eq("id",dao.getId());
        }
        if(StringUtils.isNotEmpty(dao.getDictName())){
            wrapper.like("dictName",dao.getDictName());
        }
        if(StringUtils.isNotEmpty(dao.getParentId())){
            wrapper.eq("parent_id",dao.getParentId());
        }
        if ( DateUtils.dateIsNotNull(dao.getCreateTimeStart())){
            //大于等于
            wrapper.ge("create_time",dao.getCreateTimeStart());
        }
        if (DateUtils.dateIsNotNull(dao.getCreateTimeEnd())){
            dao.setCreateTimeEnd(DateUtils.dateAddOneDay(dao.getCreateTimeEnd()));
            //小于
            wrapper.lt("create_time", dao.getCreateTimeEnd());
        }
        if (DateUtils.dateIsNotNull(dao.getUpdateTimeStart())){
            //大于等于
            wrapper.ge("update_time",dao.getUpdateTimeStart());
        }
        if (DateUtils.dateIsNotNull(dao.getUpdateTimeEnd())){
            dao.setUpdateTimeEnd(DateUtils.dateAddOneDay(dao.getUpdateTimeEnd()));
            //小于
            wrapper.lt("update_time",dao.getUpdateTimeEnd());
        }
        if (!StringUtils.isEmpty(dao.getCreateName())){
            wrapper.like("createName",dao.getCreateName());
        }
        //多条件组合查询
        wrapper.orderByDesc("create_time");
        //调用mybatis plus分页方法进行查询
        List<GradeDict> result = baseMapper.selectList(wrapper);
        return ListUtils.gradeDictTree(result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer actionDo(GradeDictDao dao){
        GradeDict insert = new GradeDict();
        BeanUtils.copyProperties(dao, insert);
        if( StringUtils.isEmpty(insert.getDictId())  ){
            insert.setDictId(StringUtils.getUUid());
        }
        return null==insert.getId() ? baseMapper.insert(insert) : baseMapper.updateById(insert);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteById(String id){
        return baseMapper.deleteById(id);
    }


    @Override
    public GradeDict selectById(String id){
        return baseMapper.selectById(id);
    }

}
