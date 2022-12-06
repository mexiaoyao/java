package com.youlianmei.dao;

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
public class GradeQuestionImportDao implements Serializable {

    private static final long serialVersionUID = 1L;

    private String intro;

    private String question;

    private String answers;

    private String answerRight;

    private String remarks;
}
