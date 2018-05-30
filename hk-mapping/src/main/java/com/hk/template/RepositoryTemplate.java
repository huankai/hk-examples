package com.hk.template;

/**
 * @author huangkai
 * @date 2018-5-30 20:52
 */
public interface RepositoryTemplate extends Template {

    EntityTemplate getEntityTemplate();

    CustomRepositoryTemplate getCustomRepositoryTemplate();
}
