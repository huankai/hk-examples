package com.hk.example3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.hk.commons.poi.excel.model.WriteParam;
import com.hk.commons.poi.excel.write.HSSFWriteableExcel;
import com.hk.commons.poi.excel.write.WriteableExcel;
import com.hk.commons.poi.excel.write.handler.SimpleWriteableHandler;
import com.hk.commons.util.StringUtils;
import com.hk.example1.ExcelVo;

public class IterableNestedExcelWriteTest {
	
	@Test
	public void writeTo2003() throws FileNotFoundException {
		WriteParam<IterableNestedExcelVo> writeParam = new WriteParam<>();
		writeParam.setBeanClazz(IterableNestedExcelVo.class);
		writeParam.setTitleRow(0);
		writeParam.setDataStartRow(1);
		List<IterableNestedExcelVo> data = Lists.newArrayList();
		for (int i = 0; i < 20; i++) {
			data.add(new IterableNestedExcelVo(String.valueOf(i), Lists.newArrayList(new ExcelVo("name" + i, 2 + i, LocalDate.now(), i % 2 == 0, LocalDateTime.now()),
					new ExcelVo("N-A-M-E" + i, 2 + i, LocalDate.now(), i % 2 == 0, LocalDateTime.now()))));
		}
		writeParam.setData(data);
		WriteableExcel<IterableNestedExcelVo> writeableExcel = new HSSFWriteableExcel<>(
				new SimpleWriteableHandler<IterableNestedExcelVo>() {
					@Override
					protected String getCommentText(IterableNestedExcelVo obj, String propertyName, Class<?> propertyType) {
						if (StringUtils.equals("age", propertyName)) {
							return "年龄";
						}
						return super.getCommentText(obj, propertyName, propertyType);
					}
				});
		writeableExcel.write(writeParam, new FileOutputStream(new File("C:/Users/sjq-278/Desktop/excel.xls")));
	}
	
//	@Test
//	public void writeTo2007ByXSSF() throws FileNotFoundException {
//		WriteParam<IterableNestedExcelVo> writeParam = new WriteParam<>();
//		writeParam.setBeanClazz(IterableNestedExcelVo.class);
//		writeParam.setTitleRow(0);
//		writeParam.setDataStartRow(1);
//		List<IterableNestedExcelVo> data = Lists.newArrayList();
//		for (int i = 0; i < 20; i++) {
//			data.add(new IterableNestedExcelVo("" + i,
//					new ExcelVo("name" + i, 2 + i, LocalDate.now(), i % 2 == 0, LocalDateTime.now())));
//		}
//		writeParam.setData(data);
//		WriteableExcel<IterableNestedExcelVo> writeableExcel = new XSSFWriteableExcel<>(
//				new SimpleWriteableHandler<IterableNestedExcelVo>() {
//					@Override
//					protected String getCommentText(IterableNestedExcelVo obj, String propertyName, Class<?> propertyType) {
//						if (StringUtils.equals("age", propertyName)) {
//							return "年龄";
//						}
//						return super.getCommentText(obj, propertyName, propertyType);
//					}
//				});
//		writeableExcel.write(writeParam, new FileOutputStream(new File("C:/Users/sjq-278/Desktop/excel.xlsx")));
//	}
//
//	@Test
//	public void writeTo2007BySXSSF() throws FileNotFoundException {
//		WriteParam<IterableNestedExcelVo> writeParam = new WriteParam<>();
//		writeParam.setBeanClazz(IterableNestedExcelVo.class);
//		writeParam.setTitleRow(0);
//		writeParam.setDataStartRow(1);
//		List<IterableNestedExcelVo> data = Lists.newArrayList();
//		for (int i = 0; i < 20; i++) {
//			data.add(new IterableNestedExcelVo("" + i,
//					new ExcelVo("name" + i, 2 + i, LocalDate.now(), i % 2 == 0, LocalDateTime.now())));
//		}
//		writeParam.setData(data);
//		WriteableExcel<IterableNestedExcelVo> writeableExcel = new SXSSFWriteableExcel<>(
//				new SimpleWriteableHandler<IterableNestedExcelVo>() {
//					@Override
//					protected String getCommentText(IterableNestedExcelVo obj, String propertyName, Class<?> propertyType) {
//						if (StringUtils.equals("age", propertyName)) {
//							return "年龄";
//						}
//						return super.getCommentText(obj, propertyName, propertyType);
//					}
//				});
//		writeableExcel.write(writeParam, new FileOutputStream(new File("C:/Users/sjq-278/Desktop/sxssf_nested_excel.xlsx")));
//	}

	

}
