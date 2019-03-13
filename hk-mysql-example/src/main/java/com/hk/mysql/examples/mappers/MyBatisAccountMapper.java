package com.hk.mysql.examples.mappers;

import com.hk.data.ibatis.BaseMapper;
import com.hk.mysql.examples.domain.MyBatisAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author huangkai
 * @date 2019/3/13 9:09
 */
@Mapper
public interface MyBatisAccountMapper extends BaseMapper<MyBatisAccount> {

    MyBatisAccount getById(@Param(value = "id") String id);


}
