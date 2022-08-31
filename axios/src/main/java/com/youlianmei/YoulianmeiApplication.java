package com.youlianmei;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.youlianmei.mapper")
public class YoulianmeiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoulianmeiApplication.class, args);
    }

}
