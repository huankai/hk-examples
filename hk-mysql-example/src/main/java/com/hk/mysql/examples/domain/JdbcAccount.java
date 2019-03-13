package com.hk.mysql.examples.domain;

import com.hk.core.data.jdbc.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author huangkai
 * @date 2019-02-26 17:22
 */
@Data
@Table(value = "test")
@EqualsAndHashCode(callSuper = true)
public class JdbcAccount extends AbstractUUIDPersistable {

    @Column(value = "name_")
    private String name;

    @Column(value = "value_")
    private String value;

    @Column(value = "time_")
    private LocalDateTime time;

    @Column(value = "content")
    private List<Content> content;

    @Data
    public static class Content implements Serializable {

        private String name;

        private String value;

    }
}
