package com.hk.mysql.example.test;

import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import com.hk.mysql.examples.domain.Account;
import com.hk.mysql.examples.service.AccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Account account = accountService.getById("1");
        account.setNickName("ddd");
        accountService.updateByIdSelective(account);
    }

}
