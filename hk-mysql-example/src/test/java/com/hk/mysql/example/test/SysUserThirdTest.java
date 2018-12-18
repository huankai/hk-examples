package com.hk.mysql.example.test;

import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.IDGenerator;
import com.hk.core.data.jdbc.JdbcSession;
import com.hk.core.data.jdbc.domain.AbstractAuditable;
import com.hk.core.test.BaseTest;
import com.hk.mysql.examples.MysqlExampleApplication;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangkai
 * @date 2018-12-18 17:13
 */
@SpringBootTest(classes = {MysqlExampleApplication.class})
public class SysUserThirdTest extends BaseTest {

    @Autowired
    private JdbcSession jdbcSession;

    @Test
    public void batchUpdateTest() {
        long start = System.currentTimeMillis();
        List<SysUserThird> list = new ArrayList<>();
        SysUserThird userThird;
        for (int i = 0; i < 1000; i++) {
            userThird = new SysUserThird();
            userThird.setId(IDGenerator.STRING_UUID.generate());
            userThird.setUserId(IDGenerator.STRING_UUID.generate());
            userThird.setUserThirdName("Helodef" + i);
            userThird.setOpenId(IDGenerator.STRING_UUID.generate());
            userThird.setAccountType(ByteConstants.ONE);
            userThird.setCreatedBy("1");
            userThird.setCreatedDate(LocalDateTime.now());
            userThird.setLastModifiedBy("1");
            userThird.setLastModifiedDate(LocalDateTime.now());
            list.add(userThird);
        }
        jdbcSession.batchUpdate("INSERT INTO sys_user_third(id,user_id,user_third_name,open_id,icon_url,account_type,created_by," +
                "created_date,last_modified_by,last_modified_date) values(:id,:userId,:userThirdName,:openId,:iconUrl,:accountType," +
                ":createdBy,:createdDate,:lastModifiedBy,:lastModifiedDate)", list);
        System.out.println(System.currentTimeMillis() - start);
    }


    @Data
    @Table(value = "sys_user_third")
    @EqualsAndHashCode(callSuper = true)
    @SuppressWarnings("serial")
    static class SysUserThird extends AbstractAuditable {

        @Column(value = "user_id")
        private String userId;

        @Column(value = "user_third_name")
        private String userThirdName;

        @Column(value = "open_id")
        private String openId;

        @Column(value = "icon_url")
        private String iconUrl;

        @Column(value = "account_type")
        private Byte accountType;
    }
}
