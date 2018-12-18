/**
 *
 */
package com.hk.weixin.example.config;

import com.hk.commons.util.ByteConstants;
import com.hk.core.authentication.api.ClientAppInfo;
import com.hk.core.authentication.security.SecurityUserPrincipal;
import com.hk.core.authentication.security.UserDetailClientService;
import com.hk.weixin.example.domain.User;
import com.hk.weixin.example.repository.jpa.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * @author kevin
 * @date 2018年2月5日下午3:03:07
 */
public class UserDetailServcieImpl implements UserDetailClientService {

    private UserRepository userRepository;

    @Override
    public SecurityUserPrincipal loadUserByLoginUsername(String username) {
        Optional<User> optional = userRepository.findUniqueByLoginName(username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("不存在的用户:" + username);
        }
        User user = optional.get();
        return new SecurityUserPrincipal(user.getId(), user.getPhone(), false,
                user.getNickName(), ByteConstants.ONE, user.getPhone(),
                user.getEmail(), ByteConstants.ONE, user.getIconPath(), user.getPassWord(), ByteConstants.ONE);
    }

    @Override
    public ClientAppInfo getClientInfoById(String clientId) {
        return null;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
