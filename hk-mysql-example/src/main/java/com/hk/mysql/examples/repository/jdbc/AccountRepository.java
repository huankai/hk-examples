package com.hk.mysql.examples.repository.jdbc;

import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.mysql.examples.domain.Account;

/**
 * @author huangkai
 * @date 2019-02-26 17:24
 */
public interface AccountRepository extends StringIdJdbcRepository<Account> {

}
