package com.hk.springcloud.stream.kafka.transaction.repository.jpa;

import com.hk.core.data.jpa.repository.StringIdJpaRepository;
import com.hk.springcloud.stream.kafka.transaction.domain.City;

/**
 * @author huangkai
 * @date 2018-11-09 13:51
 */
public interface CityRepository extends StringIdJpaRepository<City> {
}
