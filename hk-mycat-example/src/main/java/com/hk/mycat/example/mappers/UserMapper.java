package com.hk.mycat.example.mappers;

import com.hk.data.ibatis.BaseMapper;
import com.hk.mycat.example.vo.UserRoleVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserRoleVo> {

    UserRoleVo findByUserId(String id);

}
