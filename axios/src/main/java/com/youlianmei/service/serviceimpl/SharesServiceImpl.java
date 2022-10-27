package com.youlianmei.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlianmei.mapper.SharesMapper;
import com.youlianmei.model.Shares;
import com.youlianmei.service.SharesService;
import com.youlianmei.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SharesServiceImpl extends ServiceImpl<SharesMapper, Shares> implements SharesService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createTable(String tableName){
        if( StringUtils.isEmpty(tableName)){
            return;
        }
        baseMapper.createTable(tableName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delateTable(String tableName){
        if( StringUtils.isEmpty(tableName)){
            return;
        }
        baseMapper.delateTable(tableName);
    }




}
