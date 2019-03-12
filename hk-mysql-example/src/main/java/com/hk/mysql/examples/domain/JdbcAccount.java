package com.hk.mysql.examples.domain;

import com.hk.core.data.jdbc.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.List;


/**
 * @author huangkai
 * @date 2019-02-26 17:22
 */
@Data
@Table(value = "t_account")
@EqualsAndHashCode(callSuper = true)
public class JdbcAccount extends AbstractUUIDPersistable {

    @Column(value = "sheyuan_id")
    private String sheyuanId;

    @Column(value = "nick_name")
    private String nickName;


    @Column(value = "content")
    private List<Content> content;

    @Data
    public static class Content implements Serializable {

        private String name;

        private String value;

    }
}
