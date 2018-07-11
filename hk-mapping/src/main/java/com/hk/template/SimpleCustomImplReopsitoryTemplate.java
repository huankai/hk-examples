package com.hk.template;

/**
 * @author: kevin
 * @date 2018-5-30 21:38
 */
public class SimpleCustomImplReopsitoryTemplate extends AbstractTemplate implements CustomImplRepositoryTemplate {

    private CustomRepositoryTemplate customRepositoryTemplate;

    @Override
    public CustomRepositoryTemplate getCustomRepositoryTemplate() {
        return customRepositoryTemplate;
    }

    @Override
    public void setCustomRepositoryTemplate(CustomRepositoryTemplate customRepositoryTemplate) {
        this.customRepositoryTemplate = customRepositoryTemplate;
    }
}
