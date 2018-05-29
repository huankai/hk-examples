package com.hk;

import com.hk.core.MetaData;
import com.hk.core.TemplateGenerator;
import com.hk.entity.Table;
import com.hk.util.ConnectionUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author: huangkai
 * @date 2018-05-29 10:55
 */
public class Main {

    public static void main(String[] args) {
        Connection connection = ConnectionUtils.getConnection();
        List<Table> tables = new MetaData(connection).getTables(); // 生成所有表
//        List<Table> tables = new MetaData(connection).getTables("scm_semester","scm_schoolyear"); //生成指定的表名
        TemplateGenerator.generate(tables);
        ConnectionUtils.close(connection);
    }
}
