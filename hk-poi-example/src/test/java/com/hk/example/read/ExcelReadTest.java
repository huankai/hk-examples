package com.hk.example.read;

/**
 * <pre>
 * 解析Excel,支持 dom /sax解析
 * 支持Number类型的数据统计
 * 支持自定义样式
 * 支持自定义批注
 * </pre>
 * 
 * @author huangkai
 *
 */
@SuppressWarnings("unchecked")
public class ExcelReadTest {

//	ReadableParams<ExcelVo> params = ReadableParams.newReadParamsBuilder()
//			.setTitleRow(0)// 标题行
//			.setDataStartRow(1)// 从第一行开始解析
//			.addValidationables(new DefaultHibernateValidation<ExcelVo>()) // 添加校验
//			.build();
//
//	/* *****DOM 解析 ************* */
//	
//	@Test
//	public void test() {
//		SimpleDomReadableExcel<ExcelVo> readableExcel = new SimpleDomReadableExcel<>(); 
//		Type[] typeList =  readableExcel.getClass().getGenericInterfaces();
////		Type[] typeArguments = typeList.getActualTypeArguments();
//		for(Type type : typeList) {
//			System.out.println(type.getTypeName());
//		}
//		System.out.println(readableExcel.getClass().getGenericSuperclass().getTypeName());
//	}
//	
//	/**
//	 * Dom解析 2003格式
//	 */
//	@Test
//	public void read2003ByDom() {
//		InputStream inputStream = ExcelReadTest.class.getResourceAsStream("excel.xls");
//		ReadableExcel<ExcelVo> readableExcel = new SimpleDomReadableExcel<>();
//		ReadableResult<ExcelVo> readableResult = readableExcel.read(params,inputStream);
//		print(readableResult);
//	}
//	
//
////	/**
////	 * Dom解析 2007格式
////	 */
////	@Test
////	public void read2007ByDom() {
////		InputStream inputStream = ExcelReadTest.class.getResourceAsStream("excel.xlsx");
////		ReadableExcel<ExcelVo> readableExcel = new DefaultDomReadableExcel<>(params);
////		ReadableResult<ExcelVo> readableResult = readableExcel.read(inputStream);
////		print(readableResult);
////	}
////
////	/* *****Sax 解析 ************* */
////
////	/**
////	 * Sax解析 2003格式
////	 */
////	@Test
////	public void read2003BySax() {
////		InputStream inputStream = ExcelReadTest.class.getResourceAsStream("excel.xls");
////		ReadableExcel<ExcelVo> readableExcel = new DefaultSaxReadableExcel<>(params);
////		ReadableResult<ExcelVo> readableResult = readableExcel.read(inputStream);
////		print(readableResult);
////	}
////
////	/**
////	 * Sax解析 2007格式
////	 */
////	@Test
////	public void read2007BySax() {
////		InputStream inputStream = ExcelReadTest.class.getResourceAsStream("excel.xlsx");
////		ReadableExcel<ExcelVo> readableExcel = new DefaultSaxReadableExcel<>(params);
////		ReadableResult<ExcelVo> readableResult = readableExcel.read(inputStream);
////		print(readableResult);
////	}
//
//	/* ***** print ************* */
//	private void print(ReadableResult<ExcelVo> result) {
//		System.out.println("解析的数据：");
//		result.getValidData().forEach(item -> System.out.println(JsonUtils.toJSONString(item)));
//
//		System.err.println("----------------------------------------");
//		System.out.println("标题行：" + JsonUtils.toJSONString(result.getTitles()));
//
//		System.err.println("------------------------------");
//		System.out.println("验证错误数据：" + JsonUtils.toJSONString(result.getErrorLogs()));
//	}

}
