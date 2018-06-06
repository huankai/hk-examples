package com.hk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author huangkai
 * @date 2018-5-30 20:35
 */
@Data
@AllArgsConstructor
public abstract class ConnectionModel {

    private String jdbcUrl;

    private String username;

    private String password;

    public abstract String getDriverName();

    public static class MySql5Connection extends ConnectionModel {


        public MySql5Connection(String jdbcUrl, String username, String password) {
            super(jdbcUrl, username, password);
        }

        @Override
        public String getDriverName() {
            return "com.mysql.jdbc.Driver";
        }
    }


}
