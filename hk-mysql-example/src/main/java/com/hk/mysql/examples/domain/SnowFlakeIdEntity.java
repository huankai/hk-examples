package com.hk.mysql.examples.domain;

import com.hk.core.data.jpa.domain.AbstractSnowflakeIdPersistable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 使用雪花算法生成id 测试
 * <pre>
 * CREATE TABLE `snow_test` (
 * `id` bigint(20) NOT NULL,
 * `name` varchar(100)DEFAULT NULL,
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
 * </pre>
 *
 * @author kevin
 * @date 2019-7-2 17:41
 */
@Data
@Entity
@Table(name = "snow_test")
@EqualsAndHashCode(callSuper = true)
public class SnowFlakeIdEntity extends AbstractSnowflakeIdPersistable {

    @Column(name = "name")
    private String name;


}
