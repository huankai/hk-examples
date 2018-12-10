package com.hk.example2;

import com.google.common.collect.Lists;
import com.hk.commons.poi.excel.model.ReadParam;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.read.DomReadExcel;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.poi.excel.read.SaxReadExcel;
import com.hk.commons.poi.excel.read.validation.JSRValidation;
import com.hk.commons.util.JsonUtils;
import org.junit.Test;

import java.io.File;


/**
 * <pre>
 * 解析Excel,支持 dom /sax解析
 * 支持Number类型的数据统计
 * 支持自定义样式
 * 支持自定义批注
 * </pre>
 *
 * @author: kevin
 */
public class NestedExcelReadTest {

    @Test
    public void read2003ByDom() {
        ReadParam<NestedExcelVo> readParam = ReadParam.<NestedExcelVo>builder().beanClazz(NestedExcelVo.class).build();
        ReadableExcel<NestedExcelVo> readExcel = new DomReadExcel<>(readParam);
        ReadResult<NestedExcelVo> readResult = readExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xls"));
        print(readResult);
    }

    @Test
    public void read2007ByDom() {
        ReadParam<NestedExcelVo> readParam = ReadParam.<NestedExcelVo>builder().beanClazz(NestedExcelVo.class).validationList(Lists.newArrayList(new JSRValidation<>())).build();
        ReadableExcel<NestedExcelVo> readExcel = new DomReadExcel<>(readParam);
        ReadResult<NestedExcelVo> readResult = readExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xlsx"));
        print(readResult);
    }

    @Test
    public void read2003BySax() {
        ReadParam<NestedExcelVo> readParam = ReadParam.<NestedExcelVo>builder().beanClazz(NestedExcelVo.class).build();
        ReadableExcel<NestedExcelVo> readExcel = new SaxReadExcel<>(readParam);
        ReadResult<NestedExcelVo> readResult = readExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xls"));
        print(readResult);
    }

    @Test
    public void read2007BySax() {
        ReadParam<NestedExcelVo> readParam = ReadParam.<NestedExcelVo>builder().beanClazz(NestedExcelVo.class).build();
        ReadableExcel<NestedExcelVo> readExcel = new SaxReadExcel<>(readParam);
        ReadResult<NestedExcelVo> readResult = readExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xlsx"));
        print(readResult);
    }

    /* ***** print ************* */
    private <T> void print(ReadResult<T> result) {
        System.out.println("解析的数据：");
        result.getAllSheetData().forEach(item -> System.out.println(JsonUtils.serialize(item)));

        System.err.println("----------------------------------------");
        System.out.println("标题行：" + JsonUtils.serialize(result.getTitleList()));

        System.err.println("------------------------------");
        System.out.println("验证错误数据：" + JsonUtils.serialize(result.getErrorLogList()));
    }

}
