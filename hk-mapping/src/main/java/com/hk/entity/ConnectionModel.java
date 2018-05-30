package com.hk.entity;

import lombok.Data;

/**
 * @author huangkai
 * @date 2018-5-30 20:35
 */
@Data
public class ConnectionModel {

    private String jdbcUrl;

    private String username;

    private String password;

    private String driverName;
}
