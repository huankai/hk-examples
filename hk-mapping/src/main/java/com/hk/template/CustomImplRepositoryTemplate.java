package com.hk.template;

/**
 * @author: kevin
 * @date: 2018-5-30 20:53
 */
public interface CustomImplRepositoryTemplate extends Template {

    CustomRepositoryTemplate getCustomRepositoryTemplate();

    void setCustomRepositoryTemplate(CustomRepositoryTemplate customRepositoryTemplate);
}
