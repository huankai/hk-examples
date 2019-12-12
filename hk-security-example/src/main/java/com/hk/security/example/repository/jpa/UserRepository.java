package com.hk.security.example.repository.jpa;

import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.security.example.entity.User;

/**
 * @author sjq-278
 * @date 2018-12-17 15:04
 */
public interface UserRepository extends BaseJpaRepository<User, String> {
}
