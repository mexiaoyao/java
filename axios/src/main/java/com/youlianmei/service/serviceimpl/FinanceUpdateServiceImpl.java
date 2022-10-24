package com.youlianmei.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlianmei.dao.FinanceUpdateDao;
import com.youlianmei.mapper.FinanceUpdateMapper;
import com.youlianmei.model.FinanceUpdate;
import com.youlianmei.service.FinanceUpdateService;
import com.youlianmei.utils.DateUtils;
import com.youlianmei.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FinanceUpdateServiceImpl extends ServiceImpl<FinanceUpdateMapper, FinanceUpdate> implements FinanceUpdateService {

    @Override
    @Transactional(rollbackFor = Exception.class) //一般编辑，添加，删除（即对数据库进行操作）时用到，此处只是展示案例
    public Page<FinanceUpdate> pageList(FinanceUpdateDao financeUpdateDao){
        Integer pageNo = null==financeUpdateDao.getPageNo() ? 1 : financeUpdateDao.getPageNo();
        Integer pageSize = null==financeUpdateDao.getPageSize() ? 10 : financeUpdateDao.getPageSize();
        //创建Page对象
        Page<FinanceUpdate> eduTeacherPage = new Page<>(pageNo,pageSize);
        //构建条件
        QueryWrapper<FinanceUpdate> wrapper = new QueryWrapper<>();
        //多条件组合查询
        if (!StringUtils.isEmpty(financeUpdateDao.getSharesId())){
            wrapper.like("shares_id",financeUpdateDao.getSharesId());
        }
        if (null!=financeUpdateDao.getStatus() && financeUpdateDao.getStatus().intValue()!=0){
            wrapper.eq("status",financeUpdateDao.getStatus());
        }
        if (null!=financeUpdateDao.getCreateTimeStart()){
            //大于等于
            wrapper.ge("create_time",financeUpdateDao.getCreateTimeStart());
        }
        if (null!=financeUpdateDao.getCreateTimeEnd()){
            financeUpdateDao.setCreateTimeEnd(DateUtils.dateAddOneDay(financeUpdateDao.getCreateTimeEnd()));
            //小于
            wrapper.lt("create_time", financeUpdateDao.getCreateTimeEnd());
        }
        if (!StringUtils.isEmpty(financeUpdateDao.getRemarks())){
            wrapper.like("remarks",financeUpdateDao.getRemarks());
        }
        //多条件组合查询
        wrapper.orderByDesc("create_time");
        //调用mybatis plus分页方法进行查询
        return baseMapper.selectPage(eduTeacherPage,wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer financeUpdateStatus(String id, Byte status, String resorn){
        FinanceUpdate update = new FinanceUpdate();
        update.setId(id);
        update.setStatus(status);
        update.setRemarks(resorn);
        return baseMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addFinanceUpdate(FinanceUpdateDao financeUpdate){
        FinanceUpdate insert = new FinanceUpdate();
        if(null!=financeUpdate.getStatus()){
            insert.setStatus(financeUpdate.getStatus());
        }
        if(StringUtils.isNotEmpty(financeUpdate.getRemarks())){
            insert.setRemarks(financeUpdate.getRemarks());
        }
        return baseMapper.insert(insert);
    }


}
