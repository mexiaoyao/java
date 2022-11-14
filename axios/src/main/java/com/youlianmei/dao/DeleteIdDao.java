package com.youlianmei.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
public class DeleteIdDao implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Byte status;
}
