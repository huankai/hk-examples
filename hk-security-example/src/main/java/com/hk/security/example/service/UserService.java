package com.hk.security.example.service;

import com.hk.core.service.jdbc.JdbcBaseService;
import com.hk.security.example.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author sjq-278
 * @date 2018-12-17 15:04
 */
public interface UserService extends JdbcBaseService<User, String>,UserDetailsService {
}
