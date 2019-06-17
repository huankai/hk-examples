package com.hk.mycat.example.entity;

import com.hk.core.data.jpa.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_permission2")
@EqualsAndHashCode(callSuper = true)
public class Permission extends AbstractUUIDPersistable {

    @Column(name = "permission_name")
    private String permissionName;
}
