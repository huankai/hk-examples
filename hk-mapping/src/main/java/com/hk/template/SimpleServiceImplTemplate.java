package com.hk.template;

/**
 * @author: kevin
 * @date 2018-5-30 21:23
 */
public class SimpleServiceImplTemplate extends AbstractTemplate implements ServiceImplTemplate {

    private RepositoryTemplate repositoryTemplate;

    private ServiceTemplate serviceTemplate;

    @Override
    public void setRepositoryTemplate(RepositoryTemplate repositoryTemplate) {
        this.repositoryTemplate = repositoryTemplate;
    }

    @Override
    public void setServiceTemplate(ServiceTemplate serviceTemplate) {
        this.serviceTemplate = serviceTemplate;
    }

    @Override
    public RepositoryTemplate getRepositoryTemplate() {
        return repositoryTemplate;
    }

    @Override
    public ServiceTemplate getServiceTemplate() {
        return serviceTemplate;
    }
}
