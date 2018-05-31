package com.hk.template;

/**
 * @author huangkai
 * @date 2018-5-30 20:52
 */
public interface RepositoryTemplate extends Template {

    /**
     * Entity Template
     *
     * @return
     */
    EntityTemplate getEntityTemplate();

    void setEntityTemplate(EntityTemplate entityTemplate);

    /**
     * CustomRepositoryTemplate
     *
     * @return
     */
    CustomRepositoryTemplate getCustomRepositoryTemplate();

    void setCustomRepositoryTemplate(CustomRepositoryTemplate customRepositoryTemplate);
}
