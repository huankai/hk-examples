package com.hk.mysql.examples.domain;

import com.hk.core.data.jpa.domain.AbstractUUIDPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author huangkai
 * @date 2019-02-26 17:22
 */
@Data
@Table(name = "test")
@EqualsAndHashCode(callSuper = true)
public class JdbcAccount extends AbstractUUIDPersistable {

    @Column(name = "name_")
    private String name;

    @Column(name = "value_")
    private String value;

    @Column(name = "time_")
    private LocalDateTime time;

    @Column(name = "content")
    private List<Content> content;

    @Data
    public static class Content implements Serializable {

        private String name;

        private String value;

    }
}
