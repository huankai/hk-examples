package com.hk.template;

/**
 * @author: kevin
 * @date: 2018-5-30 21:35
 */
public class SimpleRepositoryTemplate extends AbstractTemplate implements RepositoryTemplate {

    private EntityTemplate entityTemplate;

    private CustomRepositoryTemplate customRepositoryTemplate;

    @Override
    public EntityTemplate getEntityTemplate() {
        return entityTemplate;
    }

    @Override
    public void setEntityTemplate(EntityTemplate entityTemplate) {
        this.entityTemplate = entityTemplate;
    }

    @Override
    public CustomRepositoryTemplate getCustomRepositoryTemplate() {
        return customRepositoryTemplate;
    }

    @Override
    public void setCustomRepositoryTemplate(CustomRepositoryTemplate customRepositoryTemplate) {
        this.customRepositoryTemplate = customRepositoryTemplate;
    }
}
