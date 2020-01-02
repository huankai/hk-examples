package com.hk.live.service;

import com.hk.commons.util.ByteConstants;
import com.hk.core.authentication.security.SecurityUserPrincipal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private List<User> users;

    public UserDetailServiceImpl(PasswordEncoder passwordEncoder) {
        users = new ArrayList<>();
        users.add(new User(1L, "admin", passwordEncoder.encode("admin"), (byte) 1));// 教师
        users.add(new User(2L, "user1", passwordEncoder.encode("admin"), (byte) 2));// 学生
        users.add(new User(3L, "user2", passwordEncoder.encode("admin"), (byte) 2));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.stream()
                .filter(item -> item.getPhone().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
        return new SecurityUserPrincipal(user.getUserId(), null, null, null, null,
                username, username, user.getUserType(), user.getPhone(), null, null, null, user.getPassword(), ByteConstants.ONE, null, null);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {

        private Long userId;

        private String phone;

        private String password;

        private Byte userType;
    }
}
