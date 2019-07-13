package com.hk.flowable.example;


import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.task.api.Task;

import java.util.List;

/**
 * 请假
 */
public class HolidayRequest {


    public static void main(String[] args) {
        ProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://127.0.0.1/hk_flow?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true")
                .setJdbcUsername("root")
                .setJdbcPassword("root")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = configuration.buildProcessEngine();


        RepositoryService repositoryService = processEngine.getRepositoryService();
        //流程部署
//        Deployment deploy = repositoryService.createDeployment().addClasspathResource("holiday-request.bpmn20.xml").deploy();
//        System.out.println(deploy); // DeploymentEntity[id=1, name=null]

//        流程查看
//        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId("1").singleResult();
//        System.out.println(processDefinition); // ProcessDefinitionEntity[holidayRequest:1:3]

//        启动流程
//        ProcessInstance[2501]
//        System.out.println(processEngine.getRuntimeService().startProcessInstanceByKey("holidayRequest"));

//       查询任务
        List<Task> taskList = processEngine.getTaskService().createTaskQuery().taskCandidateGroup("").list();
    }


}
