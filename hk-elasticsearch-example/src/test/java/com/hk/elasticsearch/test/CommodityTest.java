package com.hk.elasticsearch.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import com.hk.commons.util.IDGenerator;
import com.hk.commons.util.JsonUtils;
import com.hk.core.test.BaseTest;
import com.hk.elasticsearch.example.ElasticsearchExapleApplication;
import com.hk.elasticsearch.example.entity.Commodity;
import com.hk.elasticsearch.example.entity.Commodity.CommodityFile;
import com.hk.elasticsearch.example.repository.elasticsearch.CommodityRepository;

/**
 * @author huangkai
 *
 */
@SpringBootTest(classes = {ElasticsearchExapleApplication.class})
public class CommodityTest extends BaseTest {
	
	@Autowired
	private CommodityRepository commodityRepository;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	/**
	 * 添加数据
	 */
	@Test
	public void addCommodity() {
		Commodity commodity = new Commodity();
		commodity.setId(IDGenerator.STRING_UUID.generate());
		commodity.setName("苹果6 Plus");
		commodity.setPrice(BigDecimal.valueOf(5288));
		List<CommodityFile> files = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			CommodityFile file = new CommodityFile();
			file.setFileName("fileName" + i);
			file.setFilePath("filePath"+i);
			file.setId(IDGenerator.STRING_UUID.generate());
			files.add(file);
		}
		commodity.setFiles(files);
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
	
	@Test
	public void criteriaQuery() {
		CriteriaQuery query = new CriteriaQuery(Criteria.where("name").is("苹果"));
		List<Commodity> result = elasticsearchTemplate.queryForList(query, Commodity.class);
		result.forEach(item -> System.out.println(JsonUtils.serialize(item,true)));
	}
	
	/**
	 * 删除
	 */
	@Test
	public void delete() {
		commodityRepository.deleteById("61679bdf0fde4805917559f72a931295");
	}
	

}
