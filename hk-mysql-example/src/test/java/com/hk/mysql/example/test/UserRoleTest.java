package com.hk.mysql.example.test;

import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.data.commons.query.Operator;
import com.hk.core.data.jdbc.JdbcSession;
import com.hk.core.data.jdbc.SelectArguments;
import com.hk.core.data.jdbc.query.SimpleCondition;
import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

/**
 * 阳关评价学校管理员用户菜单权限初始化
 *
 * @author sjq-278
 * @date 2018-12-17 09:37
 */
@SpringBootTest(classes = {MysqlExampleApplication.class})
public class UserRoleTest extends BaseTest {

    @Autowired
    private JdbcSession jdbcSession;

    public static void main(String[] args) {
        System.out.println(new Date());
    }

    @Test
    public void test3() {
        List<Object[]> list = new ArrayList<>();
//        list.add(new Object[]{"1", "012459dd-b3b8-4214-9a32-e3a548844266", "00aa8a14-db74-4f30-b0a6-15f6f0a2127b", new Date(), new Date()});
//        jdbcSession.batchUpdate("INSERT INTO `sp_user_role` (`user_role_id`, `user_id`, `role_id`, `last_up_time`, `create_time`) " +
//                "VALUES (?,?,?,?,?)", list);
        list.add(new Object[]{"2", new Date(), new Date()});
        jdbcSession.batchUpdate("INSERT INTO `demo` (`demo_id`,`last_up_time`,`create_time`) " +
                "VALUES (?,?,?)", list);
    }

    /**
     * 初始化教官人员角色
     */
    @Test
    public void test() {
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("sp_user t1 JOIN sp_user_info t2 ON t1.user_id = t2.user_id");
        arguments.fields("t1.user_id,t2.org_id");
        arguments.getConditions().addCondition(new SimpleCondition("user_name", Operator.LIKEEND, "gly"));
        List<Map<String, Object>> result = jdbcSession.queryForList(arguments, false).getResult();
        System.out.println(result.size());

        arguments = new SelectArguments();
        arguments.setFrom("sp_role");
        arguments.getConditions().addCondition(new SimpleCondition("role_name", "教管人员"));
        List<Map<String, Object>> roleResult = jdbcSession.queryForList(arguments, false).getResult();
        System.out.println(roleResult.size());

        arguments = new SelectArguments();
        arguments.setFrom("sp_user_role");
        List<Map<String, Object>> userRoleList = jdbcSession.queryForList(arguments, false).getResult();
        System.out.println(userRoleList.size());

        List<Object[]> list = new ArrayList<>();
        for (Map<String, Object> userMap : result) {
            String userId = (String) userMap.get("userId");
            String orgId = (String) userMap.get("orgId");
            Map<String, Object> orgRole = roleResult.stream().filter(item -> StringUtils.equals(item.get("orgId").toString(), orgId))
                    .findFirst().orElseThrow(RuntimeException::new);

            Optional<Map<String, Object>> first = userRoleList.stream()
                    .filter(item -> StringUtils.equals(item.get("userId").toString(), userId)
                            && StringUtils.equals(orgRole.get("roleId").toString(), item.get("roleId").toString()))
                    .findFirst();
            if (!first.isPresent()) {
                list.add(new Object[]{UUID.randomUUID().toString(), userId, orgRole.get("roleId").toString(), new Date(), new Date()});
            }
        }
        jdbcSession.batchUpdate("INSERT INTO `sp_user_role` (`user_role_id`, `user_id`, `role_id`, `last_up_time`, `create_time`) " +
                "VALUES (?,?,?,?,?)", list);

    }

    /**
     * 初始化菜单权限
     */
    @Test
    public void test2() {
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("sp_role");
        arguments.getConditions().addCondition(new SimpleCondition("role_name", "教管人员"));
        List<Map<String, Object>> roleResult = jdbcSession.queryForList(arguments, false).getResult();
        System.out.println(roleResult.size());

        arguments = new SelectArguments();
        arguments.setFrom("sp_auth");
        arguments.getConditions().addCondition(new SimpleCondition("auth_name", Operator.IN, new Object[]{"附件管理", "信息上报", "信息下载"}));
        List<Map<String, Object>> authResult = jdbcSession.queryForList(arguments, false).getResult();
        System.out.println(roleResult.size());


        arguments = new SelectArguments();
        arguments.setFrom("sp_role_auth");
        List<Map<String, Object>> roleAuthResult = jdbcSession.queryForList(arguments, false).getResult();
        System.out.println(roleResult.size());

        List<Object[]> list = new ArrayList<>();
        for (Map<String, Object> roleMap : roleResult) {
            String roleId = (String) roleMap.get("roleId");
            for (Map<String, Object> authMap : authResult) {
                String authId = (String) authMap.get("authId");
                Optional<Map<String, Object>> first = roleAuthResult.stream().filter(item -> StringUtils.equals(CollectionUtils.getStringValue(item, "roleId"), roleId)
                        && StringUtils.equals(CollectionUtils.getStringValue(item, "authId"), authId)).findFirst();
                if (!first.isPresent()) {
                    list.add(new Object[]{UUID.randomUUID().toString(), roleId, authId, new Date(), new Date()});
                }
            }
        }
        jdbcSession.batchUpdate("INSERT INTO `sp_role_auth` (`role_auth_id`, `role_id`, `auth_id`, `last_up_time`, `create_time`) " +
                "VALUES (?,?,?,?,?)", list);

    }


    /**
     * 初始化用户角色权限
     */
    @Test
    public void test6() {
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("sp_role");
        arguments.getConditions().addCondition(new SimpleCondition("role_name", "教管人员"));
        List<Map<String, Object>> roleResult = jdbcSession.queryForList(arguments, false).getResult();
        System.out.println(roleResult.size());
        List<Object[]> list = new ArrayList<>();
        for (Map<String, Object> map : roleResult) {
            String roleId = (String) map.get("roleId");
            if (!StringUtils.equalsAnyIgnoreCase(roleId, "c41bb722-5b14-4f70-847e-eaa537f03aec", "ad2baed2-f819-4d42-bcc6-df2883cf6acc")) {
                list.add(new Object[]{UUID.randomUUID().toString(), roleId, "11663a88-d89c-475a-806d-2f1a4448ebc3", new Date(), new Date()});
            }
        }
        jdbcSession.batchUpdate("INSERT INTO `sp_role_auth` (`role_auth_id`, `role_id`, `auth_id`, `last_up_time`, `create_time`) " +
                "VALUES (?,?,?,?,?)", list);

    }


}
