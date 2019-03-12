package com.hk.mysql.examples.repository.jpa;

import com.hk.core.data.jpa.repository.StringIdJpaRepository;
import com.hk.mysql.examples.domain.Account;

/**
 * @author huangkai
 * @date 2019-02-26 17:24
 */
public interface AccountRepository extends StringIdJpaRepository<Account> {

}
