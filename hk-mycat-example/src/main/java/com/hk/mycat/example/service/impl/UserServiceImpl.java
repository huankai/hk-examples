package com.hk.mycat.example.service.impl;

import com.hk.commons.util.JsonUtils;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.mycat.example.entity.User;
import com.hk.mycat.example.repository.jpa.UserRepository;
import com.hk.mycat.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends JpaServiceImpl<User, String> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected BaseJpaRepository<User, String> getBaseRepository() {
        return userRepository;
    }

    /**
     * 这里如果不添加 {@link Transactional} ，没有事物 ，报错了也会提交报错前保存的记录，
     * 为什么不在类方法上添加 {@link Transactional} ，如果在该 类上添加了，该类中（不包括父类）的所有 public 修饰的方法都会添加 事物，
     * 在使用 MyCat　读写分离时　，读请求不会分配到　mysql 的从服务器
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public User insert2(User user) {
        User save = userRepository.save(user);
        System.out.println("insert2------->" + JsonUtils.serialize(find(), true));
//        boolean flat = true;
//        if (flat) {
//            throw new RuntimeException("Null");
//        }
        return save;

    }

    @Override
    public User find() {
        return findById("3").orElse(null);
    }
}
