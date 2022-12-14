package com.youlianmei.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.youlianmei.model.GradeQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)   //标在子类上
@Data
@AllArgsConstructor //有参构造器
@NoArgsConstructor //无参构造器
public class GradeQuestionDao extends GradeQuestion {

    /**
     * 对应dictTaskPath
     * **/
    private List<Integer> dictTaskId;

    private List<String> dictTaskName;

    /**
     * 对应dictGradePath
     * **/
    private List<Integer> dictGradeId;

    private List<String> dictGradeName;

    /**
     * 对应dictSourcePath
     * **/
    private List<Integer> dictSourceId;

    private List<String> dictSourceName;

    /**
     * 对应dictTypePath
     * **/
    private List<Integer> dictTypeId;

    private List<String> dictTypeName;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTimeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTimeEnd;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTimeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTimeEnd;

    private Integer pageNo;

    private Integer pageSize;

    private Integer limit;
}
