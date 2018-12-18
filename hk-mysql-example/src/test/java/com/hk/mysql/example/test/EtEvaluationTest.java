package com.hk.mysql.example.test;

import com.hk.commons.util.StringUtils;
import com.hk.commons.util.date.DateTimeUtils;
import com.hk.core.data.jdbc.JdbcSession;
import com.hk.core.data.jdbc.SelectArguments;
import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author sjq-278
 * @date 2018-12-17 16:08
 */
@SpringBootTest(classes = {MysqlExampleApplication.class})
public class EtEvaluationTest extends BaseTest {

    @Autowired
    private JdbcSession jdbcSession;

    @Test
    public void test3() {
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("scm_school t1 JOIN et_evaluation t2 ON t1.school_id = t2.org_id");
        arguments.fields("t1.period,t1.school_id,t2.evaluation_id");
        List<Map<String, Object>> result = jdbcSession.queryForList(arguments, false).getResult();
        for (Map<String, Object> map : result) {
            String period = (String) map.get("period");
            String evaluationId = (String) map.get("evaluationId");
            switch (period) {
                case "2"://小学
                    Map<String, Object> param = new HashMap<>();
                    param.put("evaluationId", evaluationId);
                    param.put("templateAccessoryId", "dc11862e-5898-4521-b6dd-8e1f165dd84e");
                    jdbcSession.update("delete from et_plan_accessory where evaluation_id=:evaluationId and template_accessory_id=:templateAccessoryId", param);
                    break;
                case "3":// 普通初中
                case "4":// 普通高中
                    param = new HashMap<>();
                    param.put("evaluationId", evaluationId);
                    param.put("templateAccessoryId", "dedabc9c-f9fa-4d15-ba16-392d0c7b9f0a");
                    jdbcSession.update("delete from et_plan_accessory where evaluation_id=:evaluationId and template_accessory_id=:templateAccessoryId", param);
                    break;
                default:
                    break;
            }
        }
    }

    @Test
    public void test2() {
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("et_evaluation");
        List<Map<String, Object>> result = jdbcSession.queryForList(arguments, false).getResult();
        arguments = new SelectArguments();
        arguments.setFrom("et_template_accessory t1 join et_plan_accessory t2 on t1.template_accessory_id = t2.template_accessory_id");
        List<Map<String, Object>> templateAccessoryList = jdbcSession.queryForList(arguments, false).getResult();

        List<Object[]> list = new ArrayList<>();
        int index = 0;
        for (Map<String, Object> item : result) {
            String evaluationId = (String) item.get("evaluationId");
            String schoolId = (String) item.get("orgId");
            if (null == schoolId) {
                throw new RuntimeException(String.valueOf(index));
            }
            index++;
            List<Map<String, Object>> collect = templateAccessoryList.stream().filter(item_ -> StringUtils.equals(item_.get("evaluationId").toString(), evaluationId)).collect(Collectors.toList());
            for (Map<String, Object> templateAccessory : collect) {
                String templateAccessoryId = (String) templateAccessory.get("templateAccessoryId");
                String planAccessoryId = (String) templateAccessory.get("planAccessoryId");
                String fileName = (String) templateAccessory.get("fileName");
                String fileUrl = (String) templateAccessory.get("fileUrl");
                String fileType = (String) templateAccessory.get("fileType");
                list.add(new Object[]{UUID.randomUUID().toString(), planAccessoryId, fileName, fileUrl, fileType, new Date(), new Date(), schoolId, evaluationId, templateAccessoryId});
            }
//            if (StringUtils.notEquals(evaluationId, "774f2c9d-06fc-46a6-b525-7b17e7ee2240")) {
//                list.add(new Object[]{UUID.randomUUID().toString(), evaluationId, new Date(), new Date(), "3de37607-49c4-48ad-8dda-e75ba36a7753"});
//                list.add(new Object[]{UUID.randomUUID().toString(), evaluationId, new Date(), new Date(), "93ffe7bf-9861-4c5d-81d2-aef7f3e18e5f"});
//                list.add(new Object[]{UUID.randomUUID().toString(), evaluationId, new Date(), new Date(), "95f9fcce-7f46-4a63-a813-a6cc67922251"});
//                list.add(new Object[]{UUID.randomUUID().toString(), evaluationId, new Date(), new Date(), "dc11862e-5898-4521-b6dd-8e1f165dd84e"});
//                list.add(new Object[]{UUID.randomUUID().toString(), evaluationId, new Date(), new Date(), "dedabc9c-f9fa-4d15-ba16-392d0c7b9f0a"});
//            }
        }
//        jdbcSession.batchUpdate("INSERT INTO `et_plan_accessory` (`plan_accessory_id`, `evaluation_id`, `create_time`, `last_up_time`, `template_accessory_id`) " +
//                "VALUES (?,?,?,?,?)", list);
//        System.out.println(JsonUtils.serialize(list, true));
        jdbcSession.batchUpdate("INSERT INTO `et_file_info` (`file_info_id`, `plan_accessory_id`, `file_name`, `file_url`, `file_type`,create_time,last_up_time,school_id,evaluation_id,template_accessory_id) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)", list);
    }

    @Test
    public void test() {
        SelectArguments arguments = new SelectArguments();
        arguments.fields("semester_id", "school_id", "semester_code");
        arguments.setFrom("scm_semester");
        List<Object[]> list = new ArrayList<>();
        List<Map<String, Object>> result = jdbcSession.queryForList(arguments, false).getResult();
        for (Map<String, Object> item : result) {
            String semesterId = (String) item.get("semesterId");
            String schoolId = (String) item.get("schoolId");
            String semesterCode = (String) item.get("semesterCode");
            if (StringUtils.notEquals(schoolId, "489f5a60-556a-11e7-a16d-408d5cf00021")) {
                list.add(new Object[]{UUID.randomUUID().toString(), "001", "2018~2019测评", null, "0",
                        schoolId, semesterId, semesterCode, DateTimeUtils.stringToDate("2018-12-18"), DateTimeUtils.stringToDate("2019-04-30"),
                        "", new Date(), new Date()});
            }
        }
        jdbcSession.batchUpdate("INSERT INTO `et_evaluation` (`evaluation_id`, `evaluation_code`, `evaluation_name`, `evaluation_desc`, `evaluation_status`, `org_id`, `semester_id`, `semester_code`, " +
                "`start_time`, `end_time`, `quota_id`, `last_up_time`, `create_time`) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)", list);
    }
}
