package com.youlianmei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlianmei.model.Shares;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

//在对应的Mapper 接口上 基础基本的 BaseMapper<T> T是对应的pojo类
@Repository   //告诉容器你是持久层的 @Repository是spring提供的注释，能够将该类注册成Bean
public interface SharesMapper extends BaseMapper<Shares> {

    Integer createTable(@Param("tableName") String tableName);

    @Update({"DROP TABLE IF EXISTS ${tableName}"})
    Integer delateTable(@Param("tableName") String tableName);
}
