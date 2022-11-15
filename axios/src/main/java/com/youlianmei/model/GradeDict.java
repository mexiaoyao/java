package com.youlianmei.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
@TableName(value = "t_grade_dict")//指定表名
public class GradeDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String dictId;

    private String dictName;

    private String parentId;

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

    private String createName;

    // 用来解决实体类中有的属性但是数据表中没有的字段
    @TableField(exist = false)  // 默认为true
    private List<GradeDict> children;
}
