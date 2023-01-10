package com.youlianmei.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlianmei.dao.GradeQuestionDao;
import com.youlianmei.mapper.GradeQuestionMapper;
import com.youlianmei.model.GradeQuestion;
import com.youlianmei.service.GradeQuestionService;
import com.youlianmei.utils.DateUtils;
import com.youlianmei.utils.ListUtils;
import com.youlianmei.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


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
        ListUtils.gradeQuestionWhere(wrapper, dao);
        //多条件组合查询
        wrapper.orderByDesc("create_time");
        //调用mybatis plus分页方法进行查询
        return baseMapper.selectPage(eduTeacherPage,wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class) //一般编辑，添加，删除（即对数据库进行操作）时用到，此处只是展示案例
    public Integer pageNum(GradeQuestionDao dao){
        StringUtils.questionPath(dao,2);
        //构建条件
        QueryWrapper<GradeQuestion> wrapper = new QueryWrapper<>();
        ListUtils.gradeQuestionWhere(wrapper, dao);
        wrapper.orderByDesc("create_time");
        //多条件组合查询
        //调用mybatis plus分页方法进行查询
        return baseMapper.selectCount(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class) //一般编辑，添加，删除（即对数据库进行操作）时用到，此处只是展示案例
    public List<GradeQuestion> listany(GradeQuestionDao dao){
        Integer limit = null==dao.getLimit() ? 10 : dao.getLimit();
        List<GradeQuestion> result = new ArrayList<GradeQuestion>();
        StringUtils.questionPath(dao,2);
        //构建条件
        QueryWrapper<GradeQuestion> wrapper = new QueryWrapper<>();
        ListUtils.gradeQuestionWhere(wrapper, dao);
        Integer total = baseMapper.selectCount(wrapper);
        if (total == null || limit <= 0 || total == 0) {
            return result;
        }
        List<GradeQuestion> list = Optional.of(limit).filter(l -> l > total).map(l -> baseMapper.selectList(wrapper)).orElseGet(() -> baseMapper.selectList(wrapper.last("LIMIT " + new SecureRandom().nextInt(total - (limit - 1)) + "," + limit)));
        Collections.shuffle(list); //重新排序
        return list;
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
            baseMapper.deleteBatchIds(list);
        }catch(Exception e){
            return 0;
        }
        return 1;
    }

    @Override
    public GradeQuestion selectById(String id){
        return baseMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertBatch(List<GradeQuestionDao> gradeQuestionDaoList){
        try {
            List<GradeQuestion> gradeQuestionList = new ArrayList<GradeQuestion>();
            for (GradeQuestionDao gradeQuestionDao : gradeQuestionDaoList) {
                StringUtils.questionPath(gradeQuestionDao,1);
                GradeQuestion gradeQuestion = new GradeQuestion();
                BeanUtils.copyProperties(gradeQuestionDao, gradeQuestion);
                gradeQuestionList.add(gradeQuestion);
            }
            return baseMapper.insertBatch(gradeQuestionList);
        }catch(Exception e){
            return false;
        }
    }

}
