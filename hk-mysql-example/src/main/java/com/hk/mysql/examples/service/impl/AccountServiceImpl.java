package com.hk.mysql.examples.service.impl;

import com.hk.core.cache.service.impl.EnableJdbcCacheServiceImpl;
import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.mysql.examples.domain.Account;
import com.hk.mysql.examples.repository.jdbc.AccountRepository;
import com.hk.mysql.examples.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author huangkai
 * @date 2019-02-26 17:26
 */
@Service
@CacheConfig(cacheNames = {"Account"})
public class AccountServiceImpl extends EnableJdbcCacheServiceImpl<Account, String> implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    protected JdbcRepository<Account, String> getBaseRepository() {
        return accountRepository;
    }
}
