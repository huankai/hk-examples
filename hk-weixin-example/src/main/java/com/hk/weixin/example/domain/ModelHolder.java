package com.hk.weixin.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hk.core.data.jpa.domain.AbstractSnowflakeAuditable;
import com.hk.core.data.jpa.domain.AbstractSnowflakeIdPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.time.LocalDate;

/**
 * @author kevin
 * @date 2017年10月31日下午1:08:08
 */
@SuppressWarnings("serial")
public class ModelHolder {

    private ModelHolder() {

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    public static class SysUserThirdBase extends AbstractSnowflakeIdPersistable {

        @OneToOne
        private User user;

        @Column(name = "user_third_name")
        private String userThirdName;

        @Column(name = "image_url")
        private String imageUrl;

        @Column(name = "account_type")
        private Integer accountType;

        @Column(name = "access_token")
        private String accessToken;

        @Column(name = "refresh_token")
        private String refreshToken;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    public static class SysLoginLogBase extends AbstractSnowflakeIdPersistable {

        @ManyToOne(optional = false)
        private User user;

        @Column(name = "ip_address")
        private String ipAddress;

        /**
         * <pre>
         * false:登陆失败
         * true：登陆成功
         * </pre>
         */
        @Column(name = "log_type")
        private Boolean logType;

        @Column(name = "log_message")
        private String logMessage;

    }


    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    public static class RolePermissionBase extends AbstractSnowflakeIdPersistable {

        @Column(name = "role_id")
        private Long roleId;

        @Column(name = "permission_id")
        private Long permissionId;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    public static class UserRoleBase extends AbstractSnowflakeIdPersistable {

        @Column(name = "user_id")
        private Long userId;

        @Column(name = "role_id")
        private Long roleId;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    public static class RoleBase extends AbstractSnowflakeIdPersistable {

        @Column(name = "app_id")
        private Long appId;

        @Column(name = "role_name")
        private String roleName;

        @Column(name = "show_name")
        private String showName;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    public static class PermissionBase extends AbstractSnowflakeIdPersistable {

        /**
         *
         */
        @Column(name = "app_id")
        private Long appId;

        @Column(name = "permission")
        private String permission;

        @Column(name = "show_name")
        private String showName;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    public static class UserBase extends AbstractSnowflakeAuditable {

        @Column(name = "user_name")
        private String userName;

        @JsonIgnore
        @Column(name = "pass_word")
        private String passWord;

        @Column(name = "nick_name")
        private String nickName;

        @Column(name = "email")
        private String email;

        @Column(name = "phone")
        private String phone;

        @Column(name = "icon_path")
        private String iconPath;

        /**
         * <pre>
         *     用户性别：
         *     1：男
         *     2：女
         *     9：未知
         * </pre>
         */
        @Column(name = "sex")
        private Integer sex;

        /**
         * 生日
         */
        @Column(name = "brithday")
        private LocalDate brithday;

        /**
         * <pre>
         *   用户类型：
         *   1:系统管理员
         * </pre>
         */
        @Column(name = "user_type")
        private Integer userType;

        /**
         * 是否保护的账号
         */
        @Column(name = "is_protected")
        private Boolean isProtected;

        /**
         * <pre>
         *     用户状态：
         *     0：禁用
         *     1：启用
         * </pre>
         */
        @Column(name = "user_status")
        private Integer userStatus;

        /**
         * 备注
         */
        @Column(name = "remark")
        private String remark;

    }
}
