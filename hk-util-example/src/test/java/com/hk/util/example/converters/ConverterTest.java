package com.hk.util.example.converters;

import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.junit.Test;

import com.hk.commons.converters.StringGenericConverter;
import com.hk.commons.util.ConverterUtils;

/**
 * 转换工具类测试
 * 
 * @author huangkai
 *
 */
public class ConverterTest {

	/**
	 * 使用默认的转换
	 */
	@Test
	public void converterTest() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		DateTime dateTime = ConverterUtils.getInstance().convert(timestamp, DateTime.class);
		System.out.println(dateTime);
	}

	/**
	 * 添加自定义转换
	 */
	@Test
	public void customConverterTest() {
		String date = "2017-12-12";
		DateTime dateTime = ConverterUtils.getInstance()
				.addConverter(new StringGenericConverter<DateTime>(DateTime.class) {

					@Override
					protected DateTime doConvert(String source) {
						return DateTime.parse(source);
					}
				})
				.convert(date, DateTime.class);
		System.out.println(dateTime);
	}
}
