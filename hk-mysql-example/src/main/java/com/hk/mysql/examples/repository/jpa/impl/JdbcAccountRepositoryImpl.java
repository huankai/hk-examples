package com.hk.mysql.examples.repository.jpa.impl;

import com.hk.core.jdbc.JdbcDaoSupport;
import com.hk.core.jdbc.SelectArguments;
import com.hk.core.jdbc.query.SimpleCondition;
import com.hk.mysql.examples.domain.JdbcAccount;
import com.hk.mysql.examples.repository.jpa.custom.CustomJdbcAccountRepository;

/**
 * @author huangkai
 * @date 2019/3/13 16:35
 */
public class JdbcAccountRepositoryImpl extends JdbcDaoSupport implements CustomJdbcAccountRepository {

    @Override
    public JdbcAccount find(String id) {
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("test");
        arguments.getConditions().addCondition(new SimpleCondition("id", id));
        return jdbcSession.queryForOne(arguments, JdbcAccount.class).orElse(null);
    }
}
