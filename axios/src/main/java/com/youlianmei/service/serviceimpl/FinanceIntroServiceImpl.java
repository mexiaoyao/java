package com.youlianmei.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlianmei.dao.FinanceIntroDao;
import com.youlianmei.mapper.FinanceIntroMapper;
import com.youlianmei.model.FinanceIntro;
import com.youlianmei.service.FinanceIntroService;
import com.youlianmei.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FinanceIntroServiceImpl extends ServiceImpl<FinanceIntroMapper, FinanceIntro> implements FinanceIntroService {

    @Override
    @Transactional(rollbackFor = Exception.class) //一般编辑，添加，删除（即对数据库进行操作）时用到，此处只是展示案例
    public Page<FinanceIntro> pageList(FinanceIntroDao financeIntro, Integer pageNo, Integer pageSize){
        //创建Page对象
        Page<FinanceIntro> eduTeacherPage = new Page<>(pageNo,pageSize);
        //构建条件
        QueryWrapper<FinanceIntro> wrapper = new QueryWrapper<>();
        //多条件组合查询
        if (null!=financeIntro.getIndexType() && financeIntro.getIndexType().intValue()!=0){
            wrapper.eq("index_type",financeIntro.getIndexType());
        }
        if (!StringUtils.isEmpty(financeIntro.getCodeNumber())){
            wrapper.like("code_number",financeIntro.getCodeNumber());
        }
        if (!StringUtils.isEmpty(financeIntro.getSharesName())){
            wrapper.like("shares_name",financeIntro.getSharesName());
        }
        if (!StringUtils.isEmpty(financeIntro.getSharesAlise())){
            wrapper.like("shares_alise",financeIntro.getSharesAlise());
        }
        if (null!=financeIntro.getSharesTotalNumberStart()){
            //大于等于
            wrapper.ge("shares_total_number",financeIntro.getSharesTotalNumberStart());
        }
        if (null!=financeIntro.getSharesTotalNumberStart()){
            //小于等于
            wrapper.le("shares_total_number",financeIntro.getSharesTotalNumberStart());
        }
        if (null!=financeIntro.getSharesTotalNumberStart()){
            //大于等于
            wrapper.ge("shares_allow_total_number",financeIntro.getSharesTotalNumberStart());
        }
        if (null!=financeIntro.getSharesAllowTotalNumberEnd()){
            //小于等于
            wrapper.le("shares_allow_total_number",financeIntro.getSharesAllowTotalNumberEnd());
        }
        if (null!=financeIntro.getStatus() && financeIntro.getStatus().intValue()!=0){
            wrapper.eq("status",financeIntro.getStatus());
        }
        if (null!=financeIntro.getLoadTimeStart()){
            //大于等于
            wrapper.ge("load_time",financeIntro.getLoadTimeStart());
        }
        if (null!=financeIntro.getLoadTimeEnd()){
            //小于等于
            wrapper.le("load_time",financeIntro.getLoadTimeEnd());
        }
        if (null!=financeIntro.getCreateTimeStart()){
            //大于等于
            wrapper.ge("create_time",financeIntro.getCreateTimeStart());
        }
        if (null!=financeIntro.getCreateTimeEnd()){
            //小于等于
            wrapper.le("create_time",financeIntro.getCreateTimeEnd());
        }
        if (null!=financeIntro.getUpdateTimeStart()){
            //大于等于
            wrapper.ge("update_time",financeIntro.getUpdateTimeStart());
        }
        if (null!=financeIntro.getUpdateTimeEnd()){
            //小于等于
            wrapper.le("update_time",financeIntro.getUpdateTimeEnd());
        }
        if (!StringUtils.isEmpty(financeIntro.getRemarks())){
            wrapper.like("remarks",financeIntro.getRemarks());
        }
        //多条件组合查询
        wrapper.orderByDesc("create_time");
        //调用mybatis plus分页方法进行查询
        return baseMapper.selectPage(eduTeacherPage,wrapper);
    }

}
