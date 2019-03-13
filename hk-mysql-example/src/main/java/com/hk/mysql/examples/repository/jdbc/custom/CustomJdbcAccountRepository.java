package com.hk.mysql.examples.repository.jdbc.custom;

import com.hk.mysql.examples.domain.JdbcAccount;

/**
 * @author huangkai
 * @date 2019/3/13 16:35
 */
public interface CustomJdbcAccountRepository {

    JdbcAccount find(String id);
}
