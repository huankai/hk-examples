package com.hk.elasticsearch.example.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <pre>
 * {@link Document} 注解参数说明：
 * indexName: 索引名称
 * type: 索引类型
 * replicas: 分片数，设置后不能更改，因为  ES 底层是使用 分片数 hash 取模，如果更改了此参数，会导致数据查询不到的问题
 * shards: 每个主分片的副本数，默认为1，此值可以修改，对于单台服务器部署的 ES 是没有副本数的，因为 ES 要求 主分片与副本不能在同一台节点上。
 * </pre>
 * 
 * @author huangkai
 * @date 2019-03-09 16:10
 */
@Data
@Document(indexName = "test", type = "commodity", replicas = 1, shards = 5)
public class Commodity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	/**
	 * analyzer: 指定分词器类型
	 */
	@Field(analyzer = "ik_smart")
	private String name;

	private BigDecimal price;
	
	private List<CommodityFile> files;
	
	@Data
	public static class CommodityFile {
		
		private String id;
		
		private String fileName;
		
		private String filePath;
	}
}
