package com.hk.security.example.repository.jdbc;

import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.security.example.entity.User;

/**
 * @author sjq-278
 * @date 2018-12-17 15:04
 */
public interface UserRepository extends JdbcRepository<User, String> {
}
