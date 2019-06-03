package com.hk.mycat.example.entity;

import com.hk.core.data.jpa.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "t_user_role")
@EqualsAndHashCode(callSuper = true)
public class UserRole extends AbstractUUIDPersistable {

    private String userId;

    private String roleId;
}
