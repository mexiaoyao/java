package com.youlianmei.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
@EqualsAndHashCode(callSuper = false)
@TableName(value = "t_user")//指定表名
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    private String pwd;

    private String role;

    private String salt;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private Date updateTime;

    private Date lastTime;
}
