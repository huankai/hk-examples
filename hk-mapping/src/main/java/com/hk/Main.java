package com.hk;

import com.hk.core.TemplateGenerate;
import com.hk.entity.TemplateParam;

/**
 * @author: huangkai
 * @date 2018-05-29 10:55
 */
public class Main {

    public static void main(String[] args) {
//        ConfigurationStorage storage = new DefaultConfigurationStorage();
//        Configuration configuration = storage.getConfiguration();
//        MetaData metaData = new MetaData(configuration.getJdbcUrl(), configuration.getUsername(), configuration.getPassword(), configuration.getDriverName());
//        List<Table> tables = metaData.getTables();// 生成所有表
////        List<Table> tables = metaData.getTables("scm_semester", "scm_schoolyear"); //生成指定的表名
//        new TemplateGenerator(storage).generate(tables, true);

//        ConnectionModel connectionModel = new ConnectionModel();
//        MetaData metaData = new MetaData(connectionModel);
//        List<Table> tables = metaData.getTables();
//        TemplateParam prarm = new TemplateParam();
//        BaseEntityTemplate entityTemplate = prarm.toBaseEntityTemplate("", null);
//        entityTemplate.genreate();
//        tables.forEach(table -> prarm.toEntityTemplate(table, entityTemplate, "").genreate());
        TemplateParam param = new TemplateParam();
        param.setBaseEntityClassName("com.hk.core.BaseEntity");
        TemplateGenerate.generateBaseEntityTemplate(param);
    }
}
