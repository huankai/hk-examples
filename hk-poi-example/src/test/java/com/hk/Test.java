package com.hk;

import com.hk.commons.poi.excel.annotations.WriteExcelField;
import com.hk.commons.poi.excel.model.WriteParam;
import com.hk.commons.poi.excel.write.SXSSFWriteableExcel;
import com.hk.commons.poi.excel.write.WriteableExcel;
import com.hk.commons.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author sjq-278
 * @date 2018-12-07 14:45
 */
public class Test {

    private static class Model {

        @WriteExcelField(index = 0, value = "2018届学校名称", width = 50)
        private String orgName;

        private String newName;

        @WriteExcelField(index = 1, value = "2019届学校名称", width = 100)
        private String exportNewNames;

        public Model(String orgName, String newName) {
            this.orgName = orgName;
            this.newName = newName;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getNewName() {
            return newName;
        }

        public void setNewName(String newName) {
            this.newName = newName;
        }

        public String getExportNewNames() {
            return exportNewNames;
        }

        public void setExportNewNames(String exportNewNames) {
            this.exportNewNames = exportNewNames;
        }
    }

    private static List<Model> getAllDataList() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("C:/Users/sjq-278/Desktop/2.txt")));
        String row;
        List<Model> list = new ArrayList<>();
        while ((row = br.readLine()) != null) {
            String orgName = row;
            int cityIndex = StringUtils.indexOf(row, "市");
            if (cityIndex != -1) {
                row = StringUtils.substring(row, cityIndex + 1);
            }
            int subfix = StringUtils.indexOf(row, "(");
            if (subfix != -1) {
                row = StringUtils.substringBefore(row, "(");
            }
            subfix = StringUtils.indexOf(row, "（");
            if (subfix != -1) {
                row = StringUtils.substringBefore(row, "（");
            }
            int areaIndex = StringUtils.indexOf(row, "区");
            if (areaIndex != -1) {
                row = StringUtils.substring(row, areaIndex + 1);
            }
            list.add(new Model(orgName, row));
        }
//        System.out.println(JsonUtils.serialize(list, true));
        br.close();
        return list;
    }


    private static List<String> getTanheDataList() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("C:/Users/sjq-278/Desktop/1.txt")));
        String row;
        List<String> list = new ArrayList<>();
        while ((row = br.readLine()) != null) {
            list.add(row);
        }
//        System.out.println("tainhe" + list);
        br.close();
        return list;
    }

    public static void main(String[] args) throws IOException {
        List<Model> allDataList = getAllDataList();
        List<String> tanheDataList = getTanheDataList();
        for (Model model : allDataList) {
            List<String> names = new ArrayList<>();
            for (String thItem : tanheDataList) {
                if (StringUtils.contains(thItem, model.newName)) {
                    names.add(thItem);
                }
            }
            if (names.size() > 0) {
                model.setExportNewNames(StringUtils.collectionToDelimitedString(names, ","));
                System.out.println("----------------------------- start" + model.orgName);
//                for (String name : names) {
//                    System.out.println(name);
//                }
//                System.out.println("----------------------------- end");

            }
        }
        ListIterator<Model> listIterator = allDataList.listIterator();
        List<Model> data = new ArrayList<>();
        List<String> schoolNames = new ArrayList<>();
        while (listIterator.hasNext()) {
            Model next = listIterator.next();
            String row = next.orgName;
            int cityIndex = StringUtils.indexOf(row, "市");
            if (cityIndex != -1) {
                row = StringUtils.substring(row, cityIndex + 1);
            }
            int subfix = StringUtils.indexOf(row, "(");
            if (subfix != -1) {
                row = StringUtils.substringBefore(row, "(");
            }
            subfix = StringUtils.indexOf(row, "（");
            if (subfix != -1) {
                row = StringUtils.substringBefore(row, "（");
            }
            int areaIndex = StringUtils.indexOf(row, "区");
            if (areaIndex != -1) {
                row = StringUtils.substring(row, areaIndex + 1);
            }
            if (schoolNames.contains(row)) {
                for (Model model : data) {
                    if (StringUtils.contains(model.getOrgName(), row)) {
                        model.setOrgName(model.getOrgName() +  "," + next.orgName);
                    }
                }
            } else {
                schoolNames.add(row);
                data.add(next);
            }

        }


        WriteParam<Model> writeParam = WriteParam.<Model>builder()
                .beanClazz(Model.class)
                .data(data)
                .build();
        WriteableExcel<Model> writeableExcel = new SXSSFWriteableExcel<>();
        writeableExcel.write(writeParam, new FileOutputStream(new File("C:/Users/sjq-278/Desktop/excel.xlsx")));
    }
}
