package com.hk.shiro.example.service.impl;

import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.authentication.shiro.ShiroUserPrincipal;
import com.hk.core.authentication.shiro.UserDetailsService;
import com.hk.core.data.jdbc.query.CompositeCondition;
import com.hk.core.data.jdbc.query.SimpleCondition;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.shiro.example.entity.User;
import com.hk.shiro.example.repository.jdbc.UserRepository;
import com.hk.shiro.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author sjq-278
 * @date 2018-12-17 15:05
 */
@Service
public class UserServiceImpl extends JdbcServiceImpl<User, String> implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected JdbcRepository<User, String> getBaseRepository() {
        return userRepository;
    }

    @Override
    public Optional<ShiroUserPrincipal> loadUserByLoginUsername(String userName) {
        Optional<User> userOptional = userRepository.findOne(new CompositeCondition(new SimpleCondition("account", userName)));
        ShiroUserPrincipal account = null;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserPrincipal userPrincipal = new UserPrincipal(user.getId(), user.getAccount(), user.getIsProtect(), user.getRealName(), user.getUserType()
                    , user.getPhone(), user.getEmail(), user.getSex(), user.getIconPath(), null, null);
//            userPrincipal.setRoleSet();配置角色与权限
//            userPrincipal.setPermissionSet();
            account = new ShiroUserPrincipal(userPrincipal, user.getPassword());
        }
        return Optional.ofNullable(account);
    }

}
