package com.hk.mysql.example.test;

import com.hk.commons.poi.excel.annotations.WriteExcelField;
import com.hk.commons.poi.excel.model.WriteParam;
import com.hk.commons.poi.excel.write.WriteableExcel;
import com.hk.commons.poi.excel.write.XSSFWriteableExcel;
import com.hk.commons.util.StringUtils;
import com.hk.core.data.commons.query.Operator;
import com.hk.core.jdbc.JdbcSession;
import com.hk.core.jdbc.SelectArguments;
import com.hk.core.jdbc.query.SimpleCondition;
import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: sjq-278
 * @date: 2018-12-19 16:49
 */
@SpringBootTest(classes = {MysqlExampleApplication.class})
public class ExportTest extends BaseTest {

    @Autowired
    private JdbcSession jdbcSession;

    @Data
    public static class Export {

        @WriteExcelField(index = 0, value = "序号")
        private Integer id;

        @WriteExcelField(index = 1, value = "区域")
        private String area;

        @WriteExcelField(index = 2, value = "学校名称")
        private String schoolName;

        private String period;

        @WriteExcelField(index = 3, value = "学段")
        public String getPeriod() {
            if (StringUtils.isNotEmpty(period)) {
                switch (period) {
                    case "2":
                        return "小学";
                    case "3":
                        return "普通初中";
                    case "4":
                        return "普通高中";
                }
            }
            return null;
        }

        @WriteExcelField(index = 4, value = "账号")
        private String userName;

        @WriteExcelField(index = 5, value = "密码")
        private String password;
    }

    @Test
    public void test() throws IOException {
        SelectArguments arguments = new SelectArguments();
        arguments.fields("t1.sch_name as school_name,t1.period,t3.user_name,t3.user_name AS `password`");
        arguments.setFrom("scm_school t1 JOIN sp_user_info t2 ON t1.school_id = t2.org_id JOIN sp_user t3 ON t2.user_id = t3.user_id");
        arguments.getConditions().addCondition(new SimpleCondition("t3.user_name", Operator.LIKEANYWHERE, "gly"));
        List<Export> result = jdbcSession.queryForList(arguments, false, Export.class).getResult();

        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/Users/sjq-278/Desktop/2.txt"));
        List<String> thq = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            thq.add(line);
        }
        bufferedReader.close();

        bufferedReader = new BufferedReader(new FileReader("C:/Users/sjq-278/Desktop/1.txt"));
        List<String> hzq = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            hzq.add(line);
        }
        bufferedReader.close();
        int index = 1;
        for (Export export : result) {
            if (thq.contains(export.schoolName)) {
                export.setArea("天河区");
            } else if (hzq.contains(export.schoolName)) {
                export.setArea("海珠区");
            }
            export.id = index++;
        }

        WriteParam<Export> param = WriteParam.<Export>builder().beanClazz(Export.class).data(result).build();
        WriteableExcel<Export> writeableExcel = new XSSFWriteableExcel<>();
        writeableExcel.write(param, new FileOutputStream(new File("C:/Users/sjq-278/Desktop/user2.xlsx")));
    }


    @Test
    public void updateHrstaffNameTest() {
        SelectArguments arguments = new SelectArguments();
        arguments.fields("t1.user_info_id,t2.sch_name");
        arguments.setFrom("sp_user_info T1 JOIN scm_school t2 ON t1.org_id = t2.school_id");
        arguments.getConditions().addCondition(new SimpleCondition("real_name", "学校管理员"));
        List<Map<String, Object>> result = jdbcSession.queryForList(arguments, false).getResult();
        for (Map<String, Object> map : result) {
            String userInfoId = (String) map.get("userInfoId");
            String schName = (String) map.get("schName");
            Map<String, Object> param = new HashMap<>();
            param.put("userInfoId", userInfoId);
            param.put("schName", schName);
            jdbcSession.update("UPDATE sp_user_info SET real_name = :schName WHERE user_info_id = :userInfoId", param);
        }
    }
}
