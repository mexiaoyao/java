package com.youlianmei.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlianmei.dao.FinanceUpdateDao;
import com.youlianmei.model.FinanceUpdate;

public interface FinanceUpdateService extends IService<FinanceUpdate> {

    Page<FinanceUpdate> pageList(FinanceUpdateDao financeUpdate);

    Integer financeUpdateStatus(String id, Byte status, String resorn);

    Integer addFinanceUpdate(FinanceUpdateDao financeUpdate);

    Integer getAgain(FinanceUpdateDao financeUpdate);

}
