package com.hk.template;

/**
 * @author: kevin
 * @date: 2018-5-30 20:54
 */
public interface ServiceImplTemplate extends Template {

    RepositoryTemplate getRepositoryTemplate();

    void setRepositoryTemplate(RepositoryTemplate repositoryTemplate);

    ServiceTemplate getServiceTemplate();

    void setServiceTemplate(ServiceTemplate serviceTemplate);
}
