package com.hk.template;

import java.io.File;

/**
 * @author huangkai
 * @date 2018-5-30 21:35
 */
public class SimpleRepositoryTemplate extends AbstractTemplate implements RepositoryTemplate {

    private EntityTemplate entityTemplate;

    private CustomRepositoryTemplate customRepositoryTemplate;

    public SimpleRepositoryTemplate(EntityTemplate entityTemplate, File outputFile, String className, String templatePath) {
        super(outputFile, className, templatePath);
        this.entityTemplate = entityTemplate;
    }

    public SimpleRepositoryTemplate(EntityTemplate entityTemplate,
                                       File outputFile, String className, String templatePath,
                                       String comment, String author, String version) {
        super(outputFile, className, templatePath, comment, author, version);
        this.entityTemplate = entityTemplate;
    }

    @Override
    public EntityTemplate getEntityTemplate() {
        return entityTemplate;
    }

    @Override
    public CustomRepositoryTemplate getCustomRepositoryTemplate() {
        return customRepositoryTemplate;
    }
}
