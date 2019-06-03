package com.hk.mycat.example.entity;

import com.hk.core.data.jpa.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends AbstractUUIDPersistable {

    @Column(name = "role_name")
    private String roleName;
}
