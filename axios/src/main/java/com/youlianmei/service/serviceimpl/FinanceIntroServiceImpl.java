package com.youlianmei.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlianmei.mapper.FinanceIntroMapper;
import com.youlianmei.model.FinanceIntro;
import com.youlianmei.service.FinanceIntroService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FinanceIntroServiceImpl extends ServiceImpl<FinanceIntroMapper, FinanceIntro> implements FinanceIntroService {

    @Override
    @Transactional(rollbackFor = Exception.class) //一般编辑，添加，删除（即对数据库进行操作）时用到，此处只是展示案例
    public Page<FinanceIntro> pageList(Integer current, Integer limit, FinanceIntro financeIntro){
        //创建Page对象
        Page<FinanceIntro> eduTeacherPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<FinanceIntro> wrapper = new QueryWrapper<>();
        //多条件组合查询
        wrapper.orderByDesc("create_time");
        //调用mybatis plus分页方法进行查询
        return baseMapper.selectPage(eduTeacherPage,wrapper);
    }

}
