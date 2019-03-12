package com.hk.mysql.examples.domain;

import com.hk.core.data.jpa.domain.AbstractUUIDPersistable;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;


/**
 * @author huangkai
 * @date 2019-02-26 17:22
 */
@Data
@Entity
@Table(name = "t_account")
@EqualsAndHashCode(callSuper = true)
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Account extends AbstractUUIDPersistable {

    @Column(name = "sheyuan_id")
    private String sheyuanId;

    @Column(name = "nick_name")
    private String nickName;


    /**
     * <pre>
     *
     * 使用 json 类型需要注意：
     * 1、需要添加依赖包 : https://github.com/vladmihalcea/hibernate-types
     * 2、要序列号的对象 {@link Content} 需要实现 {@link Serializable}
     * 3、加上 {@link Type} 注解，并在类上加上 {@link TypeDef} 注解
     * </pre>
     */
    @Column(name = "content")
    @Type(type = "json")
    private List<Content> content;

    @Data
    public static class Content implements Serializable {

        private String name;

        private String value;

    }
}
