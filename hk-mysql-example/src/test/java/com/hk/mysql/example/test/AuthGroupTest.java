package com.hk.mysql.example.test;

import com.hk.core.jdbc.JdbcSession;
import com.hk.core.jdbc.SelectArguments;
import com.hk.core.jdbc.query.SimpleCondition;
import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author huangkai
 * @date 2019-01-08 09:56
 */
@SpringBootTest(classes = {MysqlExampleApplication.class})
public class AuthGroupTest extends BaseTest {

    @Autowired
    private JdbcSession jdbcSession;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Vo {

        private String roleAuthId;

        private String roleId;

        private String authId;

        private LocalDateTime lastUpTime;

        private LocalDateTime createTime;

    }

    @Test
    public void test() {
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("sp_role");
        arguments.getConditions().addCondition(new SimpleCondition("role_name", "教管人员"));
        List<Map<String, Object>> result = jdbcSession.queryForList(arguments, false).getResult();
        arguments = new SelectArguments();
        arguments.setFrom("sp_role_auth");
        arguments.getConditions().addCondition(new SimpleCondition("auth_id", "91776635-6e8e-4e75-a80a-2e283dccb9e9"));
        List<Vo> voList = jdbcSession.queryForList(arguments, false, Vo.class).getResult();
        List<String> roleIds = voList.stream().map(Vo::getRoleId).collect(Collectors.toList());
        List<Vo> insertList = new ArrayList<>();
        for (Map<String, Object> map : result) {
            String roleId = (String) map.get("roleId");
            if (!roleIds.contains(roleId)) {
                insertList.add(Vo.builder().roleAuthId(UUID.randomUUID().toString()).roleId(roleId).authId("91776635-6e8e-4e75-a80a-2e283dccb9e9")
                        .lastUpTime(LocalDateTime.now()).createTime(LocalDateTime.now()).build());
            }
        }
//        System.out.println(JsonUtils.serialize(insertList, true));
//        System.out.println(insertList.size());
        jdbcSession.batchUpdate("INSERT INTO `sp_role_auth`(`role_auth_id`, `role_id`, `auth_id`, `last_up_time`, `create_time`) " +
                "VALUES (:roleAuthId,:roleId,:authId,:lastUpTime,:createTime)", insertList);
    }
}
