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
@TableName(value = "t_grade_question")//指定表名
public class GradeQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String question;

    private String answers;

    private String answerRight;

    private Integer usedNum;

    private String dictId;

    private Integer goodNum;

    private Integer poorNum;

    private Byte status;

    /**
     * <p>{@link DateTimeFormat}：接收页面传来的时间字符串并转为{@link Date}
     * <p>{@link JsonFormat}：{@link Date}转为{@link String}返回页面
     */
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * <p>{@link DateTimeFormat}：接收页面传来的时间字符串并转为{@link Date}
     * <p>{@link JsonFormat}：{@link Date}转为{@link String}返回页面
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Integer shareNum;

    private String createName;

    private String remarks;
}
