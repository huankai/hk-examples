package com.hk.util.example.converters;

/**
 * 转换工具类测试
 *
 * @author: kevin
 */
@Deprecated
public class ConverterTest {

//	/**
//	 * 使用默认的转换
//	 */
//	@Test
//	public void converterTest() {
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		DateTime dateTime = ConverterUtils.getInstance().convert(timestamp, DateTime.class);
//		System.out.println(dateTime);
//	}
//
//	/**
//	 * 添加自定义转换
//	 */
//	@Test
//	public void customConverterTest() {
//		String date = "2017-12-12";
//		DateTime dateTime = ConverterUtils.getInstance()
//				.addConverter(new StringGenericConverter<DateTime>(DateTime.class) {
//
//					@Override
//					protected DateTime doConvert(String source) {
//						return DateTime.parse(source);
//					}
//				})
//				.convert(date, DateTime.class);
//		System.out.println(dateTime);
//	}
}
