package com.hk.security.example.entity;

import com.hk.core.data.jdbc.domain.AbstractAuditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author sjq-278
 * @date 2018-12-17 15:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(value = "sys_user")
public class User extends AbstractAuditable {

    @NotEmpty
    @Column(value = "org_id")
    private String orgId;

    @NotEmpty
    @Column(value = "dept_id")
    private String deptId;

    @NotEmpty
    @Length(max = 20)
    @Column(value = "account")
    private String account;

    @NotEmpty
    @Length(max = 11)
    @Column(value = "phone")
    private String phone;

    @Column(value = "email")
    private String email;

    @NotEmpty
    @Length(max = 20)
    @Column(value = "real_name")
    private String realName;

    @NotNull
    @Column(value = "password")
    private String password;

    @NotEmpty
    @Column(value = "user_type")
    private Byte userType;

    @NotNull
    @Column(value = "is_protect")
    private Boolean isProtect;

    @NotNull
    @Column(value = "sex")
    private Byte sex;

    @Column(value = "icon_path")
    private String iconPath;

    @Column(value = "birth")
    private LocalDate birth;

    @Column(value = "province_id")
    private String provinceId;

    @Column(value = "city_id")
    private String cityId;

    @Column(value = "user_status")
    private Byte userStatus;

}
