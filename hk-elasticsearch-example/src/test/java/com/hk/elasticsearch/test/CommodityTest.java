package com.hk.elasticsearch.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hk.commons.util.IDGenerator;
import com.hk.commons.util.JsonUtils;
import com.hk.core.test.BaseTest;
import com.hk.elasticsearch.example.ElasticsearchExapleApplication;
import com.hk.elasticsearch.example.entity.Commodity;
import com.hk.elasticsearch.example.repository.elasticsearch.CommodityRepository;

/**
 * @author huangkai
 *
 */
@SpringBootTest(classes = {ElasticsearchExapleApplication.class})
public class CommodityTest extends BaseTest {
	
	@Autowired
	private CommodityRepository commodityRepository;
	
	/**
	 * 添加数据
	 */
	@Test
	public void addCommodity() {
		Commodity commodity = new Commodity();
		commodity.setId(IDGenerator.STRING_UUID.generate());
		commodity.setName("苹果6 Plus");
		commodity.setPrice(BigDecimal.valueOf(5288));
		commodityRepository.save(commodity);
	}
	
	/**
	 * 查询数据
	 */
	@Test
	public void list() {
		Iterable<Commodity> result = commodityRepository.findAll();
		result.forEach(item -> System.out.println(JsonUtils.serialize(item,true)));
	}
	
	/**
	 * 删除
	 */
	@Test
	public void delete() {
		commodityRepository.deleteById("1");
	}
	

}
