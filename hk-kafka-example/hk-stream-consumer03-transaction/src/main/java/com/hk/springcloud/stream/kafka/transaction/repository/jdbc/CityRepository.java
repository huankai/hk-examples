package com.hk.springcloud.stream.kafka.transaction.repository.jdbc;

import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.springcloud.stream.kafka.transaction.domain.City;

/**
 * @author huangkai
 * @date 2018-11-09 13:51
 */
public interface CityRepository extends StringIdJdbcRepository<City> {
}
