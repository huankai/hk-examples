package com.hk.mysql.examples.repository.jpa;

import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.mysql.examples.domain.JdbcAccount;
import com.hk.mysql.examples.repository.jpa.custom.CustomJdbcAccountRepository;

/**
 * @author huangkai
 * @date 2019-03-12 22:41
 */
public interface JdbcAccountRepository extends BaseJpaRepository<JdbcAccount, String>, CustomJdbcAccountRepository {
}
