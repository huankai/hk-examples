package com.hk.mysql.examples.repository.jpa;

import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.mysql.examples.domain.SnowFlakeIdEntity;

/**
 * @author kevin
 * @date 2019-7-2 17:42
 */
public interface SnowFlakeIdEntityRepository extends LongIdJpaRepository<SnowFlakeIdEntity> {

}
