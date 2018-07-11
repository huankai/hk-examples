package com.hk.template;


/**
 * @author: kevin
 * @date 2018-5-30 21:23
 */
public class SimpleControllerTemplate extends AbstractTemplate implements ControllerTemplate {

    private ServiceTemplate serviceTemplate;

    @Override
    public void setServiceTemplate(ServiceTemplate serviceTemplate) {
        this.serviceTemplate = serviceTemplate;
    }

    @Override
    public ServiceTemplate getServiceTemplate() {
        return serviceTemplate;
    }
}
