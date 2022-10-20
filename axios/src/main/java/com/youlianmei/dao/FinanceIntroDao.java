package com.youlianmei.dao;

import com.youlianmei.model.FinanceIntro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)   //标在子类上
@Data
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
public class FinanceIntroDao extends FinanceIntro {

    private Long sharesTotalNumberStart;

    private Long sharesTotalNumberEnd;


    private Long sharesAllowTotalNumberStart;

    private Long sharesAllowTotalNumberEnd;


    private Date loadTimeStart;

    private Date loadTimeEnd;


    private Date createTimeStart;

    private Date createTimeEnd;


    private Date updateTimeStart;

    private Date updateTimeEnd;


    private Integer pageNo;

    private Integer pageSize;
}
