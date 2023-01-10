package com.youlianmei.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
public class ListDao implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> stringList;


}
