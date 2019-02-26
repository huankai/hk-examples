package com.hk.example1;

import com.hk.commons.poi.excel.model.ReadParam;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.model.SheetData;
import com.hk.commons.poi.excel.read.DomReadExcel;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.poi.excel.read.SaxReadExcel;
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
 * @author kevin
 */
public class ExcelReadTest {

    @Test
    public void read2003ByDom() {
        ReadParam<ExcelVo> readParam = ReadParam.<ExcelVo>builder().beanClazz(ExcelVo.class).build();
        ReadableExcel<ExcelVo> readableExcel = new DomReadExcel<>(readParam);
        ReadResult<ExcelVo> readResult = readableExcel.read(new File("C:/Users/sjq-278/Desktop/excel_test.xls"));
        print(readResult);
    }

    @Test
    public void read2007ByDom() {
        ReadParam<ExcelVo> readParam = ReadParam.<ExcelVo>builder().beanClazz(ExcelVo.class).parseAll(true).build();
        ReadableExcel<ExcelVo> readableExcel = new DomReadExcel<>(readParam);
        long start = System.currentTimeMillis();
        ReadResult<ExcelVo> readResult = readableExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xlsx"));
        System.out.println("time:" + (System.currentTimeMillis() - start));
        print(readResult);
    }

    @Test
    public void read2003BySax() {
        ReadParam<ExcelVo> readParam = ReadParam.<ExcelVo>builder().beanClazz(ExcelVo.class).build();
        ReadableExcel<ExcelVo> readableExcel = new SaxReadExcel<>(readParam);
        ReadResult<ExcelVo> readResult = readableExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xls"));
        print(readResult);
    }

    @Test
    public void read2007BySax() {
        ReadParam<ExcelVo> readParam = ReadParam.<ExcelVo>builder().beanClazz(ExcelVo.class).build();
        ReadableExcel<ExcelVo> readableExcel = new SaxReadExcel<>(readParam);
        ReadResult<ExcelVo> readResult = readableExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xlsx"));
        print(readResult);
    }

    /* ***** print ************* */
    private <T> void print(ReadResult<T> result) {
        System.out.println("解析的数据：");
        for (SheetData<T> sheetData : result.getSheetDataList()) {
            System.out.println("----------------------------------------");
            System.out.println(JsonUtils.serialize(sheetData.getData()));
            System.out.println(sheetData.getSheetIndex());
            System.out.println(sheetData.getSheetName());
            System.out.println(sheetData.getData().size());
            System.out.println(JsonUtils.serialize(sheetData.getErrorLogs()));
            System.out.println("----------------------------------------");
        }
//        result.getAllSheetData().forEach(item -> System.out.println(JsonUtils.serialize(item)));

//        System.out.println("----------------------------------------");
//        System.out.println("标题行：" + JsonUtils.serialize(result.getTitleList(), true));
//
//        System.out.println("------------------------------");
//        System.out.println("验证错误数据：" + JsonUtils.serialize(result.getErrorLogList()));
    }

}
