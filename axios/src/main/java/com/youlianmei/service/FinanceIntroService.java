package com.youlianmei.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlianmei.dao.FinanceIntroDao;
import com.youlianmei.model.FinanceIntro;

public interface FinanceIntroService extends IService<FinanceIntro> {

    Page<FinanceIntro> pageList(FinanceIntroDao financeIntro);

}
