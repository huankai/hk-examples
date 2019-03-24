package com.hk.netty.example.seven;

import com.hk.thrift.Person;
import com.hk.thrift.PersonService;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现 persionService 接口
 *
 * @author huangkai
 * @date 2019-03-23 21:32
 */
public class ThriftPersonServiceImpl implements PersonService.Iface {

    private static final Map<String, Person> PERSON_MAP = new HashMap<>();

    @Override
    public Person getByUsername(String username) {
        return PERSON_MAP.get(username);
    }

    @Override
    public void save(Person person) {
        System.out.println("username:" + person.getUsername());
        System.out.println("age:" + person.getAge());
        PERSON_MAP.put(person.getUsername(), person);
    }
}
