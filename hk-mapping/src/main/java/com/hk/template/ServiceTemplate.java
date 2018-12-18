package com.hk.template;

/**
 * @author kevin
 * @date 2018-5-30 20:54
 */
public interface ServiceTemplate extends Template {


    EntityTemplate getEntityTemplate();

    void setEntityTemplate(EntityTemplate entityTemplate);
}
