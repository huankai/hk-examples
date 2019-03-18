package com.hk.mysql.examples.service.impl;

import com.hk.core.cache.service.impl.EnableJpaCacheServiceImpl;
import com.hk.core.data.jpa.query.specification.Criteria;
import com.hk.core.data.jpa.query.specification.Restrictions;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.mysql.examples.domain.Account;
import com.hk.mysql.examples.repository.jpa.AccountRepository;
import com.hk.mysql.examples.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangkai
 * @date 2019-02-26 17:26
 */
@Service
@CacheConfig(cacheNames = {"Account"})
public class AccountServiceImpl extends EnableJpaCacheServiceImpl<Account, String> implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    protected BaseJpaRepository<Account, String> getBaseRepository() {
        return accountRepository;
    }
}
