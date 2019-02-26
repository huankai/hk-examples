package com.hk.mysql.examples.domain;

import com.hk.core.data.jdbc.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author huangkai
 * @date 2019-02-26 17:22
 */
@Table(value = "t_account")
@Data
@EqualsAndHashCode(callSuper = true)
public class Account extends AbstractUUIDPersistable {

    @Column(value = "sheyuan_id")
    private String sheyuanId;

    @Column(value = "nick_name")
    private String nickName;
}
