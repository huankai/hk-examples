package com.hk.weixin.example.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.hk.core.data.jpa.domain.AbstractAuditable;
import com.hk.core.data.jpa.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.time.LocalDate;

/**
 * @author: kevin
 * @date 2017年10月31日下午1:08:08
 */
@SuppressWarnings("serial")
public class ModelHolder {

    private ModelHolder() {

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    public static class SysUserThirdBase extends AbstractUUIDPersistable {

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
    public static class SysLoginLogBase extends AbstractUUIDPersistable {

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
    public static class RolePermissionBase extends AbstractUUIDPersistable {

        @Column(name = "role_id")
        private String roleId;

        @Column(name = "permission_id")
        private String permissionId;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    public static class UserRoleBase extends AbstractUUIDPersistable {

        @Column(name = "user_id")
        private String userId;

        @Column(name = "role_id")
        private String roleId;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    public static class RoleBase extends AbstractUUIDPersistable {

        @Column(name = "app_id")
        private String appId;

        @Column(name = "role_name")
        private String roleName;

        @Column(name = "show_name")
        private String showName;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    public static class PermissionBase extends AbstractUUIDPersistable {

        /**
         *
         */
        @Column(name = "app_id")
        private String appId;

        @Column(name = "permission")
        private String permission;

        @Column(name = "show_name")
        private String showName;

    }

    @Data
    @MappedSuperclass
    @EqualsAndHashCode(callSuper = true)
    public static class UserBase extends AbstractAuditable {

        @Column(name = "user_name")
        private String userName;

        @JSONField(serialize = false, deserialize = false)
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
