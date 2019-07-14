package com.hk.mysql.example.test;

import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.JsonUtils;
import com.hk.commons.util.date.DateTimeUtils;
import com.hk.core.data.commons.query.Operator;
import com.hk.core.jdbc.JdbcSession;
import com.hk.core.jdbc.SelectArguments;
import com.hk.core.jdbc.query.SimpleCondition;
import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * @author huangkai
 * @date 2019-02-27 16:18
 */
@SpringBootTest(classes = {MysqlExampleApplication.class})
public class SemesterTest extends BaseTest {

    @Autowired
    private JdbcSession jdbcSession;

    @Test
    public void test() {
        Set<String> schoolNames = ArrayUtils.asHashSet("执信中学琶洲实验学校（小学部）", "绿翠现代实验学校（小学部）", "海珠实验中学附属小学", "六中长风中学", "执信中学琶洲实验学校");
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("scm_school");
        arguments.getConditions().addCondition(new SimpleCondition("sch_name", Operator.IN, schoolNames));
        List<Map<String, Object>> result = jdbcSession.queryForList(arguments, false).getResult();
        List<Object[]> schoolYearList = new ArrayList<>();
        List<Object[]> semesterList = new ArrayList<>();
        for (Map<String, Object> item : result) {
            String schoolId = (String) item.get("schoolId");
            String id = UUID.randomUUID().toString();
            schoolYearList.add(new Object[]{id, schoolId, "2018~2019学年", DateTimeUtils.stringToDate("2018-09-01"), DateTimeUtils.stringToDate("2019-07-01"), "1", new Date(), new Date()});

            semesterList.add(new Object[]{UUID.randomUUID().toString(), schoolId, "2018-~2019下学期", DateTimeUtils.stringToDate("2019-02-01"),
                    DateTimeUtils.stringToDate("2019-07-01"), 20, 20, id, "1", "SEM2019", new Date(), new Date()});
        }
        System.out.println(JsonUtils.serialize(schoolYearList, true));
        System.out.println(schoolYearList.size());
        System.out.println(JsonUtils.serialize(semesterList, true));
        jdbcSession.batchUpdate("INSERT INTO `scm_schoolyear`(`schoolyear_id`, `school_id`, `schoolyear_name`, `begin_date`, `end_date`, `due_status`, `last_up_time`, `create_time`) VALUES " +
                "(?,?,?,?,?,?,?,?)", schoolYearList);
        jdbcSession.batchUpdate("INSERT INTO `scm_semester`(`semester_id`, `school_id`, `semester_name`, `begin_date`, `end_date`, `total_week_no`, `class_week_no`, `schoolyear_id`, `due_status`, `semester_code`, `last_up_time`, `create_time`) VALUES " +
                "(?,?,?,?,?,?,?,?,?,?,?,?)", semesterList);
    }
}
