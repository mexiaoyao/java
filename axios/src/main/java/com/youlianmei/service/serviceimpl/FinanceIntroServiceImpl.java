package com.youlianmei.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlianmei.dao.FinanceIntroDao;
import com.youlianmei.mapper.FinanceIntroMapper;
import com.youlianmei.model.FinanceIntro;
import com.youlianmei.service.FinanceIntroService;
import com.youlianmei.utils.DateUtils;
import com.youlianmei.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class FinanceIntroServiceImpl extends ServiceImpl<FinanceIntroMapper, FinanceIntro> implements FinanceIntroService {

    @Override
    @Transactional(rollbackFor = Exception.class) //一般编辑，添加，删除（即对数据库进行操作）时用到，此处只是展示案例
    public Page<FinanceIntro> pageList(FinanceIntroDao financeIntro){
        Integer pageNo = null==financeIntro.getPageNo() ? 1 : financeIntro.getPageNo();
        Integer pageSize = null==financeIntro.getPageSize() ? 10 : financeIntro.getPageSize();
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
            //小于
            wrapper.lt("shares_allow_total_number",financeIntro.getSharesAllowTotalNumberEnd());
        }
        if (null!=financeIntro.getStatus() && financeIntro.getStatus().intValue()!=0){
            wrapper.eq("status",financeIntro.getStatus());
        }
        if (null!=financeIntro.getLoadTimeStart()){
            //大于等于
            wrapper.ge("load_time",financeIntro.getLoadTimeStart());
        }
        if (null!=financeIntro.getLoadTimeEnd()){
            //小于
            wrapper.lt("load_time",financeIntro.getLoadTimeEnd());
        }
        if (null!=financeIntro.getCreateTimeStart()){
            //大于等于
            wrapper.ge("create_time",financeIntro.getCreateTimeStart());
        }
        if (null!=financeIntro.getCreateTimeEnd()){
            financeIntro.setCreateTimeEnd(DateUtils.dateAddOneDay(financeIntro.getCreateTimeEnd()));
            //小于
            wrapper.lt("create_time", financeIntro.getCreateTimeEnd());
        }
        if (null!=financeIntro.getUpdateTimeStart()){
            //大于等于
            wrapper.ge("update_time",financeIntro.getUpdateTimeStart());
        }
        if (null!=financeIntro.getUpdateTimeEnd()){
            financeIntro.setUpdateTimeEnd(DateUtils.dateAddOneDay(financeIntro.getUpdateTimeEnd()));
            //小于
            wrapper.lt("update_time",financeIntro.getUpdateTimeEnd());
        }
        if (!StringUtils.isEmpty(financeIntro.getRemarks())){
            wrapper.like("remarks",financeIntro.getRemarks());
        }
        //多条件组合查询
        wrapper.orderByDesc("create_time");
        //调用mybatis plus分页方法进行查询
        return baseMapper.selectPage(eduTeacherPage,wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer financeStatus(String id, Byte status){
        FinanceIntro update = new FinanceIntro();
        update.setId(id);
        update.setStatus(status);
        return baseMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer financeDelete(String id){
        return baseMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer actionFinance(FinanceIntroDao financeIntro){
        FinanceIntro insert = new FinanceIntro();
        if(null!=financeIntro.getId() && !"".equals(financeIntro.getId().trim())){
            insert.setId(financeIntro.getId());
        }else{
            insert.setLoadTime(new Date());
            insert.setStatus((byte) 2);
        }
        if(null!=financeIntro.getIndexType()){
            insert.setIndexType(financeIntro.getIndexType());
        }
        if(StringUtils.isNotEmpty(financeIntro.getCodeNumber())){
            insert.setCodeNumber(financeIntro.getCodeNumber());
        }
        if(StringUtils.isNotEmpty(financeIntro.getSharesName())){
            insert.setSharesName(financeIntro.getSharesName());
        }
        if(StringUtils.isNotEmpty(financeIntro.getSharesAlise())){
            insert.setSharesAlise(financeIntro.getSharesAlise());
        }
        if(null!=financeIntro.getSharesTotalNumber()){
            insert.setSharesTotalNumber(financeIntro.getSharesTotalNumber());
        }
        if(null!=financeIntro.getSharesAllowTotalNumber()){
            insert.setSharesAllowTotalNumber(financeIntro.getSharesAllowTotalNumber());
        }
        if(StringUtils.isNotEmpty(financeIntro.getRemarks())){
            insert.setRemarks(financeIntro.getRemarks());
        }

        insert.setUpdateTime(new Date());
        return null==insert.getId() ? baseMapper.insert(insert) : baseMapper.updateById(insert);
    }

}
