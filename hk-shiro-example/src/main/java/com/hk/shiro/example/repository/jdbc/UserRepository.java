package com.hk.shiro.example.repository.jdbc;

import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.shiro.example.entity.User;

/**
 * @author sjq-278
 * @date 2018-12-17 15:04
 */
public interface UserRepository extends JdbcRepository<User, String> {
}
