package com.hk;

import com.hk.config.Configuration;
import com.hk.core.ConfigurationStorage;
import com.hk.core.DefaultConfigurationStorage;
import com.hk.core.MetaData;
import com.hk.core.TemplateGenerator;
import com.hk.entity.Table;

import java.util.List;

/**
 * @author: huangkai
 * @date 2018-05-29 10:55
 */
public class Main {

    public static void main(String[] args) {
        ConfigurationStorage storage = new DefaultConfigurationStorage();
        Configuration configuration = storage.getConfiguration();
        MetaData metaData = new MetaData(configuration.getJdbcUrl(), configuration.getUsername(), configuration.getPassword(), configuration.getDriverName());
        List<Table> tables = metaData.getTables();// 生成所有表
//        List<Table> tables = metaData.getTables("scm_semester", "scm_schoolyear"); //生成指定的表名
        new TemplateGenerator(storage).generate(tables, true);
    }
}
