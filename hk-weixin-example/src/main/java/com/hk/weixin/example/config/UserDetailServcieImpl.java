/**
 *
 */
package com.hk.weixin.example.config;

import com.hk.core.authentication.security.AbstractUserDetailService;
import com.hk.core.authentication.security.SecurityUserPrincipal;
import com.hk.weixin.example.domain.User;
import com.hk.weixin.example.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * @author kally
 * @date 2018年2月5日下午3:03:07
 */
public class UserDetailServcieImpl extends AbstractUserDetailService {

    private UserRepository userRepository;

    @Override
    protected SecurityUserPrincipal loadUserByLoginUsername(String username) {
        Optional<User> optional = userRepository.findUniqueByLoginName(username);
        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("不存在的用户:" + username);
        }
        User user = optional.get();
        SecurityUserPrincipal userPrincipal = new SecurityUserPrincipal(false, user.getId(), user.getUserName(),
                user.getPassWord(), user.getNickName(), user.getUserType().byteValue(), user.getPhone(), user.getEmail(),
                user.getSex().byteValue(), user.getIconPath(), user.getUserStatus().byteValue());
        return userPrincipal;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
