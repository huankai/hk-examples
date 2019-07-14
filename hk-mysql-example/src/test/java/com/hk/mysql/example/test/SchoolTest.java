package com.hk.mysql.example.test;

import com.hk.commons.poi.excel.annotations.ReadExcelField;
import com.hk.commons.poi.excel.annotations.WriteExcelField;
import com.hk.commons.poi.excel.model.ReadParam;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.model.WriteParam;
import com.hk.commons.poi.excel.read.DomReadExcel;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.poi.excel.write.WriteableExcel;
import com.hk.commons.poi.excel.write.XSSFWriteableExcel;
import com.hk.commons.util.JsonUtils;
import com.hk.commons.util.ListResult;
import com.hk.commons.util.StringUtils;
import com.hk.core.jdbc.JdbcSession;
import com.hk.core.jdbc.SelectArguments;
import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.*;

/**
 * @author sjq-278
 * @date 2018-12-12 10:32
 */

@SpringBootTest(classes = {MysqlExampleApplication.class})
public class SchoolTest extends BaseTest {


    @Autowired
    private JdbcSession jdbcSession;


    public static class SchoolExcel {

        @WriteExcelField(index = 0, value = "2018届学校名称", width = 50)
        @ReadExcelField(start = 0)
        private String orgName;

        @WriteExcelField(index = 1, value = "2019届学校名称", width = 100)
        @ReadExcelField(start = 1)
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

    public static void main(String[] args) throws IOException {
//        SchoolExcel schoolExcel = new SchoolExcel();
//        BeanWrapper beanWrapper = BeanWrapperUtils.createBeanWrapper(schoolExcel);
//        for (PropertyDescriptor propertyDescriptor : beanWrapper.getPropertyDescriptors()) {
//            if (propertyDescriptor.getWriteMethod() != null) {
//                System.out.println(propertyDescriptor.getName());
//
//            }
//        }

        test5();
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private class SeOrgParams {

        private String id;

        private String orgId;

        private String level;

        private String cvType;

        private String dataSetType;
    }

    @Test
    public void testParamTable() {
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("scm_school");
        List<School> result = jdbcSession.queryForList(arguments, false, School.class).getResult();
        List<SeOrgParams> list = new ArrayList<>();
        list.add(new SeOrgParams(UUID.randomUUID().toString(), "489f5a60-556a-11e7-a16d-408d5cf00021", "1", null, null));
        list.add(new SeOrgParams(UUID.randomUUID().toString(), "2758cadb-40c8-45a8-93dd-bb0988bc0673", "2", null, null));
        list.add(new SeOrgParams(UUID.randomUUID().toString(), "a021336c-6d8b-4da1-ab1e-2b42409a9bd0", "2", null, null));
        for (School school : result) {
            list.add(new SeOrgParams(UUID.randomUUID().toString(), school.getSchoolId(), "3", "1", null));
        }
        jdbcSession.batchUpdate("INSERT INTO `se_org_params`(`param_id`, `org_id`, `level`, `cvtype`, `datasettype`) " +
                "VALUES (:id, :orgId, :level, :cvType, :dataSetType)", list);
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
        writeableExcel.write(WriteParam.<SchoolExcel>builder().beanClazz(SchoolExcel.class).data(newExcelData).build(),
                new FileOutputStream(new File("C:/Users/sjq-278/Desktop/excel2.xlsx")));

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
    }


    /*                   */

    @Data
    public static class School {

        private String schoolId;

        @ReadExcelField(start = 0)
        private String area;

        @ReadExcelField(start = 1)
        private String schoolName;

        @ReadExcelField(start = 2)
        private String schoolCode;

        @ReadExcelField(start = 3)
        private String period;

    }


    public static void test5() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/Users/sjq-278/Desktop/1.txt"));
        List<String> dataList = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            dataList.add(line);
        }
        bufferedReader.close();

        bufferedReader = new BufferedReader(new FileReader("C:/Users/sjq-278/Desktop/2.txt"));
        List<String> dataList2 = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            dataList2.add(line);
        }
        bufferedReader.close();

        for (String item : dataList) {
            if (!dataList2.contains(item)) {
                System.out.println(item + "=========");
            }
        }
    }

    @Test
    public void test4() throws IOException {
//        ReadParam<School> readParam = ReadParam.<School>builder()
//                .beanClazz(School.class)
//                .build();
//        ReadableExcel<School> readableExcel = new DomReadExcel<>(readParam);
//        ReadResult<School> result = readableExcel.read(new File("C:/Users/sjq-278/Desktop/ygpj_importorglist.xlsx"));
//        List<School> allSheetData = result.getAllSheetData();

        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/Users/sjq-278/Desktop/2.txt"));
        List<String> dataList = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            dataList.add(line);
        }
        bufferedReader.close();
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("scm_school");
        arguments.fields("school_id", "sch_code", "period", "sch_name");
        List<Map<String, Object>> listResult = jdbcSession.queryForList(arguments, false).getResult();
        for (String schoolName : dataList) {
            if (listResult.stream().noneMatch(item -> StringUtils.equals(item.get("schName").toString(), schoolName))) {
                System.out.println(schoolName);
            }
        }


    }

    @Test
    public void test3() {
        ReadParam<School> readParam = ReadParam.<School>builder()
                .beanClazz(School.class)
                .build();
        ReadableExcel<School> readableExcel = new DomReadExcel<>(readParam);
        ReadResult<School> result = readableExcel.read(new File("C:/Users/sjq-278/Desktop/ygpj_importorglist.xlsx"));
        List<School> allSheetData = result.getAllSheetData();
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("scm_school");
        arguments.fields("school_id", "sch_code", "period", "sch_name");
        List<Object[]> insertList = new ArrayList<>();
        List<Map<String, Object>> updateList = new ArrayList<>();
        List<Map<String, Object>> listResult = jdbcSession.queryForList(arguments, false).getResult();
        for (School school : allSheetData) {
            Optional<Map<String, Object>> first = listResult.stream()
                    .filter(item -> StringUtils.equals(school.getSchoolCode(), item.get("schCode").toString())
                            && StringUtils.equals(item.get("schName").toString(), school.getSchoolName()))
                    .findFirst();
            if (first.isPresent()) {
                Map<String, Object> map = first.get();
                String period = (String) map.get("period");
                String schoolId = (String) map.get("schoolId");
                String period_ = null;
                switch (school.period) {
                    case "小学":
                        period_ = "2";
                        break;
                    case "普通高中":
                        period_ = "4";
                        break;
                    case "普通初中":
                        period_ = "3";
                        break;
                    default:
                        break;
                }
                if (StringUtils.notEquals(period, period_)) {
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("period", period_);
                    map1.put("schoolId", schoolId);
                    updateList.add(map1);
                }
            } else {
                insertList.add(new Object[]{school.schoolName, school.schoolCode});
            }

        }
//        for (Map<String, Object> objects : updateList) {
//            jdbcSession.update("update scm_school set period = :period where school_id = :schoolId", objects);
//        }
        System.out.println(JsonUtils.serialize(updateList));
        System.out.println(updateList.size());
        System.out.println(JsonUtils.serialize(insertList));
        System.out.println(insertList.size());
    }


}
