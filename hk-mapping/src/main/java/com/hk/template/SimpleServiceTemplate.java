package com.hk.template;

/**
 * @author: kevin
 * @date 2018-5-30 21:23
 */
public class SimpleServiceTemplate extends AbstractTemplate implements ServiceTemplate {

    private EntityTemplate entityTemplate;


    @Override
    public EntityTemplate getEntityTemplate() {
        return entityTemplate;
    }

    @Override
    public void setEntityTemplate(EntityTemplate entityTemplate) {
        this.entityTemplate = entityTemplate;
    }
}
