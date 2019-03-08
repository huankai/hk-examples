package com.hk.example1;

import com.google.common.collect.Lists;
import com.hk.commons.poi.excel.model.WriteParam;
import com.hk.commons.poi.excel.write.HSSFWriteableExcel;
import com.hk.commons.poi.excel.write.SXSSFWriteableExcel;
import com.hk.commons.poi.excel.write.WriteableExcel;
import com.hk.commons.poi.excel.write.XSSFWriteableExcel;
import com.hk.commons.poi.excel.write.handler.SimpleWriteableHandler;
import com.hk.commons.util.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ExcelWriteTest {

    @Test
    public void writeTo2003() throws FileNotFoundException {
        List<ExcelVo> data = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {
            data.add(new ExcelVo("name" + i, 2 + i, LocalDate.now(), i % 2 == 0, LocalDateTime.now()));
        }
        WriteParam<ExcelVo> writeParam = WriteParam.<ExcelVo>builder()
                .beanClazz(ExcelVo.class)
                .data(data)
                .build();
//        writeParam.addClassFormat(LocalDate.class, DataFormat.DATE_FORMAT_CN);
        WriteableExcel<ExcelVo> writeableExcel = new HSSFWriteableExcel<>(new SimpleWriteableHandler<ExcelVo>() {
            @Override
            protected String getCommentText(ExcelVo obj, String propertyName, Class<?> propertyType) {
                if (StringUtils.equals("age", propertyName)) {
                    return "年龄";
                }
                return super.getCommentText(obj, propertyName, propertyType);
            }
        });
        writeableExcel.write(writeParam, new FileOutputStream(new File("C:/Users/Administration/Desktop/excel_test.xls")));
    }

    @Test
    public void writeTo2007ByXSSF() throws FileNotFoundException {
        List<ExcelVo> data = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {
            data.add(new ExcelVo("name" + i, 2 + i, LocalDate.now(), i % 2 == 0, LocalDateTime.now()));
        }
        WriteParam<ExcelVo> writeParam = WriteParam.<ExcelVo>builder().beanClazz(ExcelVo.class).data(data).build();

        WriteableExcel<ExcelVo> writeableExcel = new XSSFWriteableExcel<>(new SimpleWriteableHandler<ExcelVo>() {

            @Override
            protected String getCommentText(ExcelVo obj, String propertyName, Class<?> propertyType) {
                if (StringUtils.equals("age", propertyName)) {
                    return "年龄...";
                }
                return super.getCommentText(obj, propertyName, propertyType);
            }
        });
        writeableExcel.write(writeParam, new FileOutputStream(new File("C:/Users/Administration/Desktop/excel.xlsx")));
    }

    @Test
    public void writeTo2007BySXSSF() throws FileNotFoundException {
        List<ExcelVo> data = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {
            data.add(new ExcelVo("name" + i, 2 + i, LocalDate.now(), i % 2 == 0, LocalDateTime.now()));
        }
        WriteParam<ExcelVo> writeParam = WriteParam.<ExcelVo>builder().beanClazz(ExcelVo.class).data(data).build();
        WriteableExcel<ExcelVo> writeableExcel = new SXSSFWriteableExcel<>(new SimpleWriteableHandler<ExcelVo>() {

            @Override
            protected String getCommentText(ExcelVo obj, String propertyName, Class<?> propertyType) {
                if (StringUtils.equals("age", propertyName)) {
                    return "年龄...";
                }
                return super.getCommentText(obj, propertyName, propertyType);
            }
        });
        writeableExcel.write(writeParam, new FileOutputStream(new File("C:/Users/Administration/Desktop/sxssf_excel.xlsx")));
    }

}
