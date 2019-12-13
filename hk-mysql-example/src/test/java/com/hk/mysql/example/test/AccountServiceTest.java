package com.hk.mysql.example.test;

import com.hk.commons.util.JsonUtils;
import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import com.hk.mysql.examples.domain.Account;
import com.hk.mysql.examples.domain.Content;
import com.hk.mysql.examples.domain.JdbcAccount;
import com.hk.mysql.examples.domain.MyBatisAccount;
import com.hk.mysql.examples.mappers.MyBatisAccountMapper;
import com.hk.mysql.examples.repository.jpa.JdbcAccountRepository;
import com.hk.mysql.examples.service.AccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangkai
 * @date 2019/3/8 13:13
 */
@SpringBootTest(classes = MysqlExampleApplication.class)
public class AccountServiceTest extends BaseTest {

    @Autowired
    private AccountService accountService;

    /**
     * 更新测试：乐观锁
     */
    @Test
    public void update() {
        Account account = new Account();
        account.setId("402881196e889c58016e889c831b0000");
        account.setNickName("dd3d66xxx");
        account.setVersion(1);
        accountService.insertOrUpdate(account);
    }

    @Test
    public void insertTest() {
        Account account = new Account();
        account.setSheyuanId("test2");
        account.setNickName("testName");
        account.setVersion(0);
        List<Content> contents = new ArrayList<>();
        Content content = null;
        for (int i = 0; i < 5; i++) {
            content = new Content();
            content.setName("contentName" + i);
            content.setValue("contentValue" + i);
            contents.add(content);
        }

        account.setContentOne(content);
        account.setContent(contents);
        Account result = accountService.insert(account);
        System.out.println(JsonUtils.serialize(result, true));

    }

    @Test
    public void getTest() {
        Account account = accountService.findById("40288132697494240169749432aa0000").orElse(null);
        System.out.println(JsonUtils.serialize(account, true));
    }


//    Jdbc

    @Autowired
    private JdbcAccountRepository jdbcAccountRepository;

    @Test
    public void jdbcTest() {
        JdbcAccount jdbcAccount = jdbcAccountRepository.find("4028813269758016016975802f810000");
        System.out.println(JsonUtils.serialize(jdbcAccount, true));
    }

//    MyBatis

    @Autowired
    private MyBatisAccountMapper myBatisAccountMapper;

    @Test
    public void mybatisTest() {
        MyBatisAccount account = myBatisAccountMapper.getById("40288132697494240169749432aa0000");
        System.out.println(JsonUtils.serialize(account, true));
    }

}
