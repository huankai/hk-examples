package com.hk.mysql.example.test;

import com.hk.commons.util.JsonUtils;
import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import com.hk.mysql.examples.domain.Account;
import com.hk.mysql.examples.domain.JdbcAccount;
import com.hk.mysql.examples.repository.jdbc.JdbcAccountRepository;
import com.hk.mysql.examples.repository.jpa.AccountRepository;
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

    @Test
    public void update() {
        Account account = accountService.getOne("1");
        account.setNickName("ddd");
        accountService.updateByIdSelective(account);
    }

    @Test
    public void insertTest() {
        Account account = new Account();
        account.setSheyuanId("test2");
        account.setNickName("testName");
        List<Account.Content> contents = new ArrayList<>();
        Account.Content content;
        for (int i = 0; i < 5; i++) {
            content = new Account.Content();
            content.setName("contentName" + i);
            content.setValue("contentValue" + i);
            contents.add(content);
        }
        account.setContent(contents);
        Account result = accountService.insert(account);
        System.out.println(JsonUtils.serialize(result, true));

    }

    @Test
    public void getTest() {
        Account account = accountService.findById("52c62ab7d96243c59af02a016b2cd943").orElse(null);
        System.out.println(JsonUtils.serialize(account, true));
    }


//    Jdbc

    @Autowired
    private JdbcAccountRepository jdbcAccountRepository;

    @Test
    public void jdbcTest() {
        JdbcAccount jdbcAccount = jdbcAccountRepository.getById("4028817469724b8d0169724b9eaa0000");
        System.out.println(JsonUtils.serialize(jdbcAccount, true));
    }


}
