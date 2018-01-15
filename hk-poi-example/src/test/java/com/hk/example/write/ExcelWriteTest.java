package com.hk.example.write;

public class ExcelWriteTest {
	
//	@SuppressWarnings("unchecked")
//	WriteableParamsBuilder<ExcelVo> paramsBuilder = WriteableParams.newWriteableParamsBuilder()//
//			.setBeanClazz(ExcelVo.class)//
//			.setDataStartRow(1)
//			.setTitleRow(0);
//	
//	@Test
//	public void writeTo2003() throws FileNotFoundException {
//		List<ExcelVo> data = Lists.newArrayList();
//		for (int i = 0; i < 20; i++) {
//			data.add(new ExcelVo("name"+ i, 2+i, LocalDate.now(), i % 2 == 0, LocalDateTime.now()));
//		}
//		WriteableExcel<ExcelVo> writeableExcel = new DefaultHSSFWriteableExcel<>();
//		writeableExcel.write(paramsBuilder.setData(data).put(LocalDateTime.class, DataFormat.DATETIME_FORMAT).build(),
//				new FileOutputStream(new File("C:/Users/huangkai/Desktop/excel.xls")));
//	}
//	
//	@Test
//	public void writeTo2007() throws FileNotFoundException {
//		List<ExcelVo> data = Lists.newArrayList();
//		for (int i = 0; i < 20; i++) {
//			data.add(new ExcelVo("name"+ i, 2+i, LocalDate.now(), i % 2 == 0, LocalDateTime.now()));
//		}
//		WriteableExcel<ExcelVo> writeableExcel = new DefaultXSSFWriteableExcel<>();
//		writeableExcel.write(paramsBuilder.setData(data).put(LocalDateTime.class, DataFormat.DATETIME_FORMAT).build(),
//				new FileOutputStream(new File("C:/Users/huangkai/Desktop/excel.xlsx")));
//	}

}
