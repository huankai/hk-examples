package com.hk.mysql.examples.service;

import com.hk.core.cache.service.JdbcCacheService;
import com.hk.mysql.examples.domain.Account;

/**
 * @author huangkai
 * @date 2019-02-26 17:25
 */
public interface AccountService extends JdbcCacheService<Account, String> {

}
