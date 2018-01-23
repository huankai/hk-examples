package com.hk.example3;

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
public class IterableNestedExcelReadTest {

//	@Test
//	public void read2003ByDom() {
//		ReadableParam<IterableNestedExcelVo> readableParam = new ReadableParam<>();
//		readableParam.setBeanClazz(IterableNestedExcelVo.class);
//		readableParam.setTitleRow(0);
//		readableParam.setDataStartRow(1);
//		ReadableExcel<IterableNestedExcelVo> readableExcel = new SimpleDomReadableExcel<>(readableParam);
//		ReadResult<IterableNestedExcelVo> readResult = readableExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xls"));
//		print(readResult);
//	}
//
//	@Test
//	public void read2007ByDom() {
//		ReadableParam<IterableNestedExcelVo> readableParam = new ReadableParam<>();
//		readableParam.setBeanClazz(IterableNestedExcelVo.class);
//		readableParam.setTitleRow(0);
//		readableParam.setDataStartRow(1);
//		ReadableExcel<IterableNestedExcelVo> readableExcel = new SimpleDomReadableExcel<>(readableParam);
//		ReadResult<IterableNestedExcelVo> readResult = readableExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xlsx"));
//		print(readResult);
//	}
//
//	@Test
//	public void read2003BySax() {
//		ReadableParam<IterableNestedExcelVo> readableParam = new ReadableParam<>();
//		readableParam.setBeanClazz(IterableNestedExcelVo.class);
//		readableParam.setTitleRow(0);
//		readableParam.setDataStartRow(1);
//		ReadableExcel<IterableNestedExcelVo> readableExcel = new SimpleSaxReadableExcel<>(readableParam);
//		ReadResult<IterableNestedExcelVo> readResult = readableExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xls"));
//		print(readResult);
//	}
//
//	@Test
//	public void read2007BySax() {
//		ReadableParam<IterableNestedExcelVo> readableParam = new ReadableParam<>();
//		readableParam.setBeanClazz(IterableNestedExcelVo.class);
//		readableParam.setTitleRow(0);
//		readableParam.setDataStartRow(1);
//		ReadableExcel<IterableNestedExcelVo> readableExcel = new SimpleSaxReadableExcel<>(readableParam);
//		ReadResult<IterableNestedExcelVo> readResult = readableExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xlsx"));
//		print(readResult);
//	}
//
//	/* ***** print ************* */
//	private <T> void print(ReadResult<T> result) {
//		System.out.println("解析的数据：");
//		result.getAllSheetData().forEach(item -> System.out.println(JsonUtils.toJSONString(item)));
//
//		System.err.println("----------------------------------------");
//		System.out.println("标题行：" + JsonUtils.toJSONString(result.getTitleList()));
//
//		System.err.println("------------------------------");
//		System.out.println("验证错误数据：" + JsonUtils.toJSONString(result.getErrorLogList()));
//	}

}
