package com.hk.template;

/**
 * @author huangkai
 * @date 2018-5-30 20:56
 */
public interface ControllerTemplate extends Template {

    ServiceTemplate getServiceTemplate();

    void setServiceTemplate(ServiceTemplate serviceTemplate);
}
