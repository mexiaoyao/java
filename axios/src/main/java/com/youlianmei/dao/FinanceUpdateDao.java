package com.youlianmei.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youlianmei.model.FinanceUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)   //标在子类上
@Data
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
public class FinanceUpdateDao extends FinanceUpdate {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTimeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTimeEnd;

    private Integer pageNo;

    private Integer pageSize;
}
