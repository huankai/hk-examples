package com.hk.mysql.examples.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huangkai
 * @date 2019/3/13 9:19
 */
@Data
public class Content implements Serializable {

    private String name;

    private String value;
}
