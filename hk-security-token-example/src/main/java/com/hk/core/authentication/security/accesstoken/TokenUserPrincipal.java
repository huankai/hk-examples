package com.hk.core.authentication.security.accesstoken;

import com.hk.core.authentication.security.SecurityUserPrincipal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author huangkai
 * @date 2019/3/5 15:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TokenUserPrincipal extends SecurityUserPrincipal implements Serializable {

    /**
     * token
     */
    private String token;

    /**
     * token 生效时间
     */
    private LocalDateTime start;

    /**
     * 过期时间
     */
    private LocalDateTime expire;

    public TokenUserPrincipal(String token, LocalDateTime expire, SecurityUserPrincipal principal) {
        super(principal.getUserId(), principal.getOrgId(), principal.getOrgName(),
                principal.getDeptId(), principal.getDeptName(), principal.getAccount(), principal.isProtectUser(),
                principal.getRealName(), principal.getUserType(), principal.getPhone(),
                principal.getEmail(), principal.getSex(), principal.getIconPath(), principal.getPassword(), principal.getUserType(),
                principal.getRoles(), principal.getPermissions());
        this.token = token;
        this.expire = expire;
    }

    public TokenUserPrincipal(String token, LocalDateTime expire, String userId, String orgId, String orgName,
                              String deptId, String deptName,
                              String account, boolean protectUser,
                              String realName, Byte userType, String phone,
                              String email, Byte sex, String iconPath, String password,
                              Byte userStatus, Set<String> roles, Set<String> permissions) {
        super(userId, orgId, orgName, deptId, deptName, account, protectUser, realName, userType, phone, email, sex, iconPath, password, userStatus,
                roles, permissions);
        this.token = token;
        this.expire = expire;


    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expire);
    }
}
