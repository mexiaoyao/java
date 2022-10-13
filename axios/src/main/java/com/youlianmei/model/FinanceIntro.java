package com.youlianmei.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
@TableName(value = "t_finance_intro")//指定表名
public class FinanceIntro {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private Byte indexType;

    private String codeNumber;

    private String sharesName;

    private String sharesAlise;

    private Long sharesTotalNumber;

    private Long sharesAllowTotalNumber;

    private Byte status;

    private Date loadTime;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private String remarks;
}
