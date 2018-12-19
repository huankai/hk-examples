package com.hk.mysql.example.test;

import com.hk.commons.poi.excel.annotations.ReadExcelField;
import com.hk.commons.poi.excel.model.ReadParam;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.read.DomReadExcel;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.util.*;
import com.hk.core.data.jdbc.JdbcSession;
import com.hk.core.data.jdbc.SelectArguments;
import com.hk.core.data.jdbc.query.SimpleCondition;
import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.*;

/**
 * @author sjq-278
 * @date 2018-12-13 15:05
 */
@SpringBootTest(classes = {MysqlExampleApplication.class})
public class ThirdAccountTest extends BaseTest {

    @Autowired
    private JdbcSession jdbcSession;

    public static class ThirdAccount {

        @ReadExcelField(start = 2)
        private String account;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }
    }

    public static class User {

        private String userId;

        private String userName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    @Test
    public void test2() {
        List<String> args = ArrayUtils.asArrayList("2018035",
                "2018039",
                "2018101",
                "2018140",
                "2018168",
                "2018219", "2018232", "2018255", "2018271", "2018273", "2018279", "2018280");
        SelectArguments user = new SelectArguments();
        user.setFrom("sp_user");
        List<User> userList = jdbcSession.queryForList(user, false, User.class).getResult();
        List<Object[]> list = new ArrayList<>();
        for (String item : args) {
            Optional<User> userOptional = userList.stream().filter(userOpt -> StringUtils.equals(userOpt.getUserName(), item))
                    .findFirst();
            list.add(new Object[]{IDGenerator.STRING_UUID.generate(), "e014b237-3814-41e5-84d9-4b395eb84848", userOptional.get().getUserId(),
                    item, item,
                    new Date(), new Date()});
        }
        jdbcSession.batchUpdate("insert into sp_app_third_account" +
                "(`app_third_account_id`, `app_third_id`, `user_id`, `username`, `password`, `last_up_time`, `create_time`) " +
                "VALUES (?,?,?,?,?,?,?)", list);
    }

    @Test
    public void test() {
        ReadParam<ThirdAccount> readParam = ReadParam.<ThirdAccount>builder().beanClazz(ThirdAccount.class).titleRow(1).dataStartRow(2).build();
        ReadableExcel<ThirdAccount> readableExcel = new DomReadExcel<>(readParam);
        ReadResult<ThirdAccount> result = readableExcel.read(new File("C:/Users/sjq-278/Desktop/樟树中学教师账号.xls"));
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("sp_app_third_account");
        arguments.getConditions().addCondition(new SimpleCondition("app_third_id", "e014b237-3814-41e5-84d9-4b395eb84848"));
        List<Map<String, Object>> thirdResult = jdbcSession.queryForList(arguments, false).getResult();
        List<Object[]> list = new ArrayList<>();

        SelectArguments user = new SelectArguments();
        List<String> noExists = new ArrayList<>();
        List<String> matchUser = new ArrayList<>();
        user.setFrom("sp_user");
        List<User> userList = jdbcSession.queryForList(user, false, User.class).getResult();
        for (ThirdAccount thirdAccount : result.getAllSheetData()) {
            if (thirdResult.stream().noneMatch(item -> StringUtils.equals(item.get("username").toString(), thirdAccount.getAccount()))) {
                Optional<User> userOptional = userList.stream().filter(item -> StringUtils.equals(item.getUserName(), thirdAccount.getAccount()))
                        .findFirst();
                if (userOptional.isPresent()) {
                    list.add(new Object[]{IDGenerator.STRING_UUID.generate(), "e014b237-3814-41e5-84d9-4b395eb84848", userOptional.get().getUserId(),
                            thirdAccount.getAccount(), thirdAccount.getAccount(),
                            new Date(), new Date()});

                } else {
                    noExists.add(thirdAccount.getAccount());
                }
            } else {
                matchUser.add(thirdAccount.getAccount());
            }
        }

        System.out.println(JsonUtils.serialize(list, true));
        System.out.println(matchUser);
        if (CollectionUtils.isNotEmpty(noExists)) {
            System.out.println("noExists ------>" + noExists);
        } else {
            jdbcSession.batchUpdate("insert into sp_app_third_account" +
                    "(`app_third_account_id`, `app_third_id`, `user_id`, `username`, `password`, `last_up_time`, `create_time`) " +
                    "VALUES (?,?,?,?,?,?,?)", list);

        }


    }
}

