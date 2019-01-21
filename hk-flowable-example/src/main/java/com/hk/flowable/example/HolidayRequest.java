package com.hk.flowable.example;


import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;

/**
 * 请假
 */
public class HolidayRequest {

    public static void main(String[] args) {
        ProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = configuration.buildProcessEngine();


        RepositoryService repositoryService = processEngine.getRepositoryService();
        //流程部署
        repositoryService.createDeployment().addClasspathResource("holiday-request.bpmn20.xml").deploy();


    }
}
