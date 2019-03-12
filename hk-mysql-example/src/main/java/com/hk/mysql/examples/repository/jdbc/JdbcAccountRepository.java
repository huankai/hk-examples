package com.hk.mysql.examples.repository.jdbc;

import com.hk.core.data.jdbc.repository.JdbcRepository;
import com.hk.mysql.examples.domain.JdbcAccount;

/**
 * @author huangkai
 * @date 2019-03-12 22:41
 */
public interface JdbcAccountRepository extends JdbcRepository<JdbcAccount, String> {
}
