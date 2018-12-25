package com.hk.security.example.service.impl;


import com.hk.core.authentication.security.SecurityUserPrincipal;
import com.hk.core.data.jdbc.query.CompositeCondition;
import com.hk.core.data.jdbc.query.SimpleCondition;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.core.service.jdbc.impl.JdbcServiceImpl;
import com.hk.security.example.entity.User;
import com.hk.security.example.repository.jdbc.UserRepository;
import com.hk.security.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author huangkai
 * @date 2018-12-17 15:05
 */
@Service
public class UserServiceImpl extends JdbcServiceImpl<User, String> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected JdbcRepository<User, String> getBaseRepository() {
        return userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOne(new CompositeCondition(new SimpleCondition("account", username)))
                .orElseThrow(() -> new UsernameNotFoundException("不存在的用户:" + username));
        SecurityUserPrincipal userPrincipal = new SecurityUserPrincipal(user.getId(), user.getAccount(), user.getIsProtect(), user.getRealName(),
                user.getUserType(), user.getPhone(), user.getEmail(), user.getSex(), user.getIconPath(), user.getPassword(), user.getUserStatus());
        userPrincipal.setOrgId(user.getOrgId());
        userPrincipal.setDeptId(user.getDeptId());
        return userPrincipal;
    }

}
