package com.hk.mysql.example.test;

import com.hk.commons.poi.excel.annotations.ReadExcel;
import com.hk.commons.poi.excel.annotations.WriteExcel;
import com.hk.commons.poi.excel.model.ReadParam;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.model.WriteParam;
import com.hk.commons.poi.excel.read.DomReadExcel;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.poi.excel.write.WriteableExcel;
import com.hk.commons.poi.excel.write.XSSFWriteableExcel;
import com.hk.commons.util.StringUtils;
import com.hk.core.data.jdbc.JdbcSession;
import com.hk.core.data.jdbc.SelectArguments;
import com.hk.core.page.ListResult;
import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

/**
 * @author: sjq-278
 * @date: 2018-12-12 10:32
 */

@SpringBootTest(classes = {MysqlExampleApplication.class})
public class SchoolTest extends BaseTest {


    @Autowired
    private JdbcSession jdbcSession;


    public static class SchoolExcel {

        @WriteExcel(index = 0, value = "2018届学校名称", width = 50)
        @ReadExcel(start = 0)
        private String orgName;

        @WriteExcel(index = 1, value = "2019届学校名称", width = 100)
        @ReadExcel(start = 1)
        private String exportNewNames;

        public SchoolExcel() {
        }

        public SchoolExcel(String orgName, String exportNewNames) {
            this.orgName = orgName;
            this.exportNewNames = exportNewNames;
        }


        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getExportNewNames() {
            return exportNewNames;
        }

        public void setExportNewNames(String exportNewNames) {
            this.exportNewNames = exportNewNames;
        }
    }

    @Test
    public void test() throws FileNotFoundException {
        List<Map<String, Object>> schoolList = getSchoolList().getResult();
        List<SchoolExcel> excelData = getExcelData();
        List<SchoolExcel> newExcelData = new ArrayList<>();
        for (SchoolExcel excelDatum : excelData) {
            String[] names = StringUtils.splitByComma(excelDatum.getOrgName());
            Set<String> namesSet = new LinkedHashSet<>();
//            Set<String> exportNewNameSet = new LinkedHashSet<>();
            for (String name : names) {
                for (Map<String, Object> map : schoolList) {
                    String schName = (String) map.get("schName");
                    String schCode = (String) map.get("schCode");
                    if (StringUtils.equals(schName, name)) {
                        namesSet.add(name + "[" + schCode + "]");
                    }
                }

//                String[] exportNewNames = StringUtils.splitByComma(excelDatum.getExportNewNames());
//                for (String exportNewName : exportNewNames) {
//                    String oldName = exportNewName;
//                    int cityIndex = StringUtils.indexOf(exportNewName, "市");
//                    if (cityIndex != -1) {
//                        exportNewName = StringUtils.substring(exportNewName, cityIndex + 1);
//                    }
////                    int subfix = StringUtils.indexOf(exportNewName, "(");
////                    if (subfix != -1) {
////                        exportNewName = StringUtils.substringBefore(exportNewName, "(");
////                    }
////                    subfix = StringUtils.indexOf(exportNewName, "（");
////                    if (subfix != -1) {
////                        exportNewName = StringUtils.substringBefore(exportNewName, "（");
////                    }
//                    int areaIndex = StringUtils.indexOf(exportNewName, "区");
//                    if (areaIndex != -1) {
//                        exportNewName = StringUtils.substring(exportNewName, areaIndex + 1);
//                    }
//                    for (Map<String, Object> map : schoolList) {
//                        String schName = (String) map.get("schName");
//                        String schCode = (String) map.get("schCode");
//                        if (StringUtils.contains(schName, exportNewName)) {
//                            exportNewNameSet.add(oldName + "[" + schCode + "]");
//                        }
//                    }
//                }

            }
            newExcelData.add(new SchoolExcel(StringUtils.collectionToDelimitedString(namesSet, ","), excelDatum.getExportNewNames()));
        }
//        System.out.println(JsonUtils.serialize(newExcelData, true));
        WriteableExcel<SchoolExcel> writeableExcel = new XSSFWriteableExcel<>();
        writeableExcel.write(WriteParam.<SchoolExcel>builder().beanClazz(SchoolExcel.class).data(newExcelData).build(), new FileOutputStream(new File("C:/Users/sjq-278/Desktop/excel2.xlsx")));

    }

    private ListResult<Map<String, Object>> getSchoolList() {
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("scm_school");
        return jdbcSession.queryForList(arguments, false);
    }

    private List<SchoolExcel> getExcelData() {
        ReadParam<SchoolExcel> readParam = ReadParam.<SchoolExcel>builder().beanClazz(SchoolExcel.class).build();
        ReadableExcel<SchoolExcel> readableExcel = new DomReadExcel<>(readParam);
        ReadResult<SchoolExcel> result = readableExcel.read(new File("C:/Users/sjq-278/Desktop/excel.xlsx"));
        return result.getAllSheetData();
//        System.out.println(JsonUtils.serialize(result.getAllSheetData(), true));
//        System.out.println(result.getErrorLogList().size());
    }


}
