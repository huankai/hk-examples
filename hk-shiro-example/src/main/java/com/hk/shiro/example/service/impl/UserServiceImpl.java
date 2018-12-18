package com.hk.shiro.example.service.impl;

import com.hk.core.authentication.api.UserPrincipal;
import com.hk.core.authentication.shiro.UserDetailsService;
import com.hk.core.data.jdbc.query.CompositeCondition;
import com.hk.core.data.jdbc.query.SimpleCondition;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.shiro.example.entity.User;
import com.hk.shiro.example.repository.jdbc.UserRepository;
import com.hk.shiro.example.service.UserService;
import org.apache.shiro.authc.Account;
import org.apache.shiro.authc.SimpleAccount;
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
    public Optional<Account> loadUserByLoginUsername(String userName) {
        Optional<User> userOptional = userRepository.findOne(new CompositeCondition(new SimpleCondition("account", userName)));
        Account account = null;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            account = new SimpleAccount(new UserPrincipal(user.getId(), user.getAccount(), user.getIsProtect(), user.getRealName(),
                    user.getUserType(), user.getPhone(), user.getEmail(), user.getSex(), user.getIconPath()), user.getPassword(),
                    null, null, null);
        }
        return Optional.ofNullable(account);
    }

}
