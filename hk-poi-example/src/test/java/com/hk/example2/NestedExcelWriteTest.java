package com.hk.example2;

import com.google.common.collect.Lists;
import com.hk.commons.poi.excel.model.WriteParam;
import com.hk.commons.poi.excel.write.HSSFWriteableExcel;
import com.hk.commons.poi.excel.write.SXSSFWriteableExcel;
import com.hk.commons.poi.excel.write.WriteableExcel;
import com.hk.commons.poi.excel.write.XSSFWriteableExcel;
import com.hk.commons.poi.excel.write.handler.SimpleWriteableHandler;
import com.hk.commons.util.StringUtils;
import com.hk.example1.ExcelVo;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class NestedExcelWriteTest {

    @Test
    public void writeTo2003() throws FileNotFoundException {
        List<NestedExcelVo> data = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {
            data.add(new NestedExcelVo("" + i,
                    new ExcelVo("name" + i, 2 + i, LocalDate.now(), i % 2 == 0, LocalDateTime.now())));
        }
        WriteParam<NestedExcelVo> writeParam = WriteParam.<NestedExcelVo>builder()
                .beanClazz(NestedExcelVo.class)
                .data(data)
                .build();
        WriteableExcel<NestedExcelVo> writeableExcel = new HSSFWriteableExcel<>(
                new SimpleWriteableHandler<NestedExcelVo>() {
                    @Override
                    protected String getCommentText(NestedExcelVo obj, String propertyName, Class<?> propertyType) {
                        if (StringUtils.equals("age", propertyName)) {
                            return "年龄";
                        }
                        return super.getCommentText(obj, propertyName, propertyType);
                    }
                });
        writeableExcel.write(writeParam, new FileOutputStream(new File("C:/Users/sjq-278/Desktop/excel.xls")));
    }

    @Test
    public void writeTo2007ByXSSF() throws FileNotFoundException {
        List<NestedExcelVo> data = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {
            data.add(new NestedExcelVo("" + i,
                    new ExcelVo("name" + i, 2 + i, LocalDate.now(), i % 2 == 0, LocalDateTime.now())));
        }
        WriteParam<NestedExcelVo> writeParam = WriteParam.<NestedExcelVo>builder()
                .beanClazz(NestedExcelVo.class)
                .data(data)
                .build();
        WriteableExcel<NestedExcelVo> writeableExcel = new XSSFWriteableExcel<>(
                new SimpleWriteableHandler<NestedExcelVo>() {
                    @Override
                    protected String getCommentText(NestedExcelVo obj, String propertyName, Class<?> propertyType) {
                        if (StringUtils.equals("age", propertyName)) {
                            return "年龄";
                        }
                        return super.getCommentText(obj, propertyName, propertyType);
                    }
                });
        writeableExcel.write(writeParam, new FileOutputStream(new File("C:/Users/sjq-278/Desktop/excel.xlsx")));
    }

    @Test
    public void writeTo2007BySXSSF() throws FileNotFoundException {
        List<NestedExcelVo> data = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {
            data.add(new NestedExcelVo("" + i,
                    new ExcelVo("name" + i, 2 + i, LocalDate.now(), i % 2 == 0, LocalDateTime.now())));
        }
        WriteParam<NestedExcelVo> writeParam = WriteParam.<NestedExcelVo>builder()
                .beanClazz(NestedExcelVo.class)
                .data(data)
                .build();
        WriteableExcel<NestedExcelVo> writeableExcel = new SXSSFWriteableExcel<>(
                new SimpleWriteableHandler<NestedExcelVo>() {
                    @Override
                    protected String getCommentText(NestedExcelVo obj, String propertyName, Class<?> propertyType) {
                        if (StringUtils.equals("age", propertyName)) {
                            return "年龄";
                        }
                        return super.getCommentText(obj, propertyName, propertyType);
                    }
                });
        writeableExcel.write(writeParam, new FileOutputStream(new File("C:/Users/sjq-278/Desktop/sxssf_nested_excel.xlsx")));
    }


}
