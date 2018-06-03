package com.hk;

import com.google.common.collect.Sets;
import com.hk.core.MetaData;
import com.hk.core.TemplateGenerate;
import com.hk.entity.ConnectionModel;
import com.hk.entity.Table;
import com.hk.entity.TemplateParam;

import java.util.List;

/**
 * @author: huangkai
 * @date 2018-05-29 10:55
 */
public class Main {

    public static void main(String[] args) {
        TemplateParam param = new TemplateParam();
        param.setBaseEntityClassName("com.hk.core.BaseEntity");
        param.setEntityPackageName("com.hk.core.emi.entity");
        param.setIngoreColumns(Sets.newHashSet("id","createdBy","createdDate","lastModifiedBy","lastModifiedDate"));

        param.setRepositoryPackageName("com.hk.core.emi.repository");
        param.setRepositoryClassNameSuffix("Repository");
        param.setCustomRepositoryPackageSuffix("custom");
        param.setCustomRepositoryClassNamePrefix("Custom");

        ConnectionModel connectionModel = new ConnectionModel();
        connectionModel.setDriverName("com.mysql.jdbc.Driver");
        connectionModel.setJdbcUrl("jdbc:mysql://localhost:3306/hk_emi?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true");
        connectionModel.setUsername("root");
        connectionModel.setPassword("root");
        List<Table> tables = new MetaData(connectionModel).getTables("sys_app");
        TemplateGenerate.generate(tables, param);
    }
}
