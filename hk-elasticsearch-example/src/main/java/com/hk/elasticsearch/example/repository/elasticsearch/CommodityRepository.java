package com.hk.elasticsearch.example.repository.elasticsearch;

import com.hk.elasticsearch.example.entity.Commodity;
import org.springframework.data.repository.CrudRepository;

/**
 * @author huangkai
 * @date 2019-03-09 16:13
 */
public interface CommodityRepository extends CrudRepository<Commodity, String> {

}
