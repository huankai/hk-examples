package com.hk.mycat.example.service;

import com.hk.core.service.jpa.JpaBaseService;
import com.hk.mycat.example.entity.User;

public interface UserService extends JpaBaseService<User,String> {

    User insert2(User user);

    User find();
}
