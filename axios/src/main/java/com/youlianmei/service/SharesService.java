package com.youlianmei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youlianmei.model.Shares;

public interface SharesService extends IService<Shares> {

    void createTable(String tableName);

    void delateTable(String tableName);

}
