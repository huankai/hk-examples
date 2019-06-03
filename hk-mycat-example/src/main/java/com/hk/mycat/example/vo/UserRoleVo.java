package com.hk.mycat.example.vo;

import com.hk.mycat.example.entity.Role;
import com.hk.mycat.example.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRoleVo extends User implements Serializable {

    private List<Role> roles;

}
