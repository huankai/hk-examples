package com.hk.security.example.service.impl;


import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.security.example.entity.User;
import com.hk.security.example.repository.jpa.UserRepository;
import com.hk.security.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author huangkai
 * @date 2018-12-17 15:05
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends JpaServiceImpl<User, String> implements UserService {

    private UserRepository userRepository;

    @Override
    protected final BaseJpaRepository<User, String> getBaseRepository() {
        return userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findOne(new CompositeCondition(new SimpleCondition("account", username)))
//                .orElseThrow(() -> new UsernameNotFoundException("不存在的用户:" + username));
//        return new SecurityUserPrincipal(user.getId(), user.getOrgId(), null, user.getDeptId(), null, user.getAccount(), user.getIsProtect(), user.getRealName(),
//                user.getUserType(), user.getPhone(), user.getEmail(), user.getSex(), user.getIconPath(), user.getPassword(), user.getUserStatus(), null, null);
//        userPrincipal.setOrgId(user.getOrgId());
//        userPrincipal.setDeptId(user.getDeptId());
//        return userPrincipal;
        return null;
    }

}
