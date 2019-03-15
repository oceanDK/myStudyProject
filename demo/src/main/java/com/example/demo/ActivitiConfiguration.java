package com.example.demo;

import org.activiti.engine.*;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author ocean
 * @Title: ActivitiConfiguration
 * @ProjectName demo
 * @Description: TODO
 * @date 19/3/13 09:09
 */
@Configuration
public class ActivitiConfiguration {
    private static final Logger logger = LogManager.getLogger(ActivitiConfiguration.class);

    @Bean
    public ProcessEngine processEngine(DataSourceTransactionManager transactionManager, DataSource dataSource) throws IOException{
        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        // 自动部署已有的流程文件
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(ResourceLoader.CLASSPATH_URL_PREFIX + "processes/*.bpmn");
        processEngineConfiguration.setTransactionManager(transactionManager);
        processEngineConfiguration.setDataSource(dataSource);
        processEngineConfiguration.setDatabaseSchema("ACT");
        processEngineConfiguration.setDatabaseSchemaUpdate("true");
        processEngineConfiguration.setDeploymentResources(resources);
        processEngineConfiguration.setDbIdentityUsed(false);
        return processEngineConfiguration.buildProcessEngine();
    }


    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine){
        return processEngine.getRepositoryService();
    }
    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine){
        return processEngine.getRuntimeService();
    }
    @Bean
    public TaskService taskService(ProcessEngine processEngine){
        return processEngine.getTaskService();
    }
    @Bean
    public HistoryService historyService(ProcessEngine processEngine){
        return processEngine.getHistoryService();
    }
    @Bean
    public ManagementService managementService(ProcessEngine processEngine){
        return processEngine.getManagementService();
    }
    @Bean
    public IdentityService identityService(ProcessEngine processEngine){
        return processEngine.getIdentityService();
    }
}
