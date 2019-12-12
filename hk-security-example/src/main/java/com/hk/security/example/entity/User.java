package com.hk.security.example.entity;

import com.hk.core.data.jpa.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author sjq-278
 * @date 2018-12-17 15:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "sys_user")
@SuppressWarnings("serial")
public class User extends AbstractAuditable {

    @NotEmpty
    @Column(name = "org_id")
    private String orgId;

    @NotEmpty
    @Column(name = "dept_id")
    private String deptId;

    @NotEmpty
    @Length(max = 20)
    @Column(name = "account")
    private String account;

    @NotEmpty
    @Length(max = 11)
    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @NotEmpty
    @Length(max = 20)
    @Column(name = "real_name")
    private String realName;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Column(name = "user_type")
    private Byte userType;

    @NotNull
    @Column(name = "is_protect")
    private Boolean isProtect;

    @NotNull
    @Column(name = "sex")
    private Byte sex;

    @Column(name = "icon_path")
    private String iconPath;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "province_id")
    private String provinceId;

    @Column(name = "city_id")
    private String cityId;

    @Column(name = "user_status")
    private Byte userStatus;

}
