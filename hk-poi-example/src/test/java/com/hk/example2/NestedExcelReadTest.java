package com.hk.example2;

import java.io.File;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.hk.commons.fastjson.JsonUtils;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.model.ReadableParam;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.poi.excel.read.SimpleDomReadableExcel;
import com.hk.commons.poi.excel.read.SimpleSaxReadableExcel;
import com.hk.commons.poi.excel.read.validation.JSRValidation;

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
public class NestedExcelReadTest {

	@Test
	public void read2003ByDom() {
		ReadableParam<NestedExcelVo> readableParam = new ReadableParam<>();
		readableParam.setBeanClazz(NestedExcelVo.class);
		readableParam.setTitleRow(0);
		readableParam.setDataStartRow(1);
		ReadableExcel<NestedExcelVo> readableExcel = new SimpleDomReadableExcel<>(readableParam);
		ReadResult<NestedExcelVo> readResult = readableExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xls"));
		print(readResult);
	}

	@Test
	public void read2007ByDom() {
		ReadableParam<NestedExcelVo> readableParam = new ReadableParam<>();
		readableParam.setBeanClazz(NestedExcelVo.class);
		readableParam.setTitleRow(0);
		readableParam.setDataStartRow(1);
		readableParam.setValidationList(Lists.newArrayList(new JSRValidation<>()));
		ReadableExcel<NestedExcelVo> readableExcel = new SimpleDomReadableExcel<>(readableParam);
		ReadResult<NestedExcelVo> readResult = readableExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xlsx"));
		print(readResult);
	}

	@Test
	public void read2003BySax() {
		ReadableParam<NestedExcelVo> readableParam = new ReadableParam<>();
		readableParam.setBeanClazz(NestedExcelVo.class);
		readableParam.setTitleRow(0);
		readableParam.setDataStartRow(1);
		ReadableExcel<NestedExcelVo> readableExcel = new SimpleSaxReadableExcel<>(readableParam);
		ReadResult<NestedExcelVo> readResult = readableExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xls"));
		print(readResult);
	}

	@Test
	public void read2007BySax() {
		ReadableParam<NestedExcelVo> readableParam = new ReadableParam<>();
		readableParam.setBeanClazz(NestedExcelVo.class);
		readableParam.setTitleRow(0);
		readableParam.setDataStartRow(1);
		ReadableExcel<NestedExcelVo> readableExcel = new SimpleSaxReadableExcel<>(readableParam);
		ReadResult<NestedExcelVo> readResult = readableExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xlsx"));
		print(readResult);
	}

	/* ***** print ************* */
	private <T> void print(ReadResult<T> result) {
		System.out.println("解析的数据：");
		result.getAllSheetData().forEach(item -> System.out.println(JsonUtils.toJSONString(item)));

		System.err.println("----------------------------------------");
		System.out.println("标题行：" + JsonUtils.toJSONString(result.getTitleList()));

		System.err.println("------------------------------");
		System.out.println("验证错误数据：" + JsonUtils.toJSONString(result.getErrorLogList()));
	}

}
