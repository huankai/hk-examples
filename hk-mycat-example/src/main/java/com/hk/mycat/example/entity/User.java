package com.hk.mycat.example.entity;

import com.hk.core.data.jpa.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author huangkai
 * @date 2019-06-02 21:48
 */

@Data
@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractUUIDPersistable {

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pass_word")
    private String password;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "create_date")
    private LocalDateTime createDate;

}
