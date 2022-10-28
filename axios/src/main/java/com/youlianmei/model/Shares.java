package com.youlianmei.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
//@TableName(value = "t_shares")//指定表名---此表是动态表，所以此处注释
public class Shares implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String sharesId;

    private String sharesName;

    private String codeNumber;

    private Double todayOpenPrice;

    private Double yesterdayClosePrice;

    private Double todayMaxPrice;

    private Double todayMinPrice;

    private Double todayAveragePrice;

    private Long dealAmount;

    private Byte source;

    private Long sharesTotalNumber;

    private Long sharesAllowTotalNumber;

    private Long dealSharesNumber;

    private Double amplitude;

    private Double turnoverRate;

    /**
     * <p>{@link DateTimeFormat}：接收页面传来的时间字符串并转为{@link Date}
     * <p>{@link JsonFormat}：{@link Date}转为{@link String}返回页面
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sharesDte;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private String remarks;
}
