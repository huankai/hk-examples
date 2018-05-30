package com.hk.template;

import java.io.File;

/**
 * @author huangkai
 * @date 2018-5-30 21:23
 */
public class SimpleServiceImplTemplate extends AbstractTemplate implements ServiceImplTemplate {

    private RepositoryTemplate repositoryTemplate;

    private ServiceTemplate serviceTemplate;

    public SimpleServiceImplTemplate(File outputFile, String className, String templatePath) {
        super(outputFile, className, templatePath);
    }

    public SimpleServiceImplTemplate(File outputFile, String className, String templatePath,
                                     String comment, String author, String version) {
        super(outputFile, className, templatePath, comment, author, version);
    }

    @Override
    public RepositoryTemplate getRepositoryTemplate() {
        return repositoryTemplate;
    }

    @Override
    public ServiceTemplate getServiceTemplate() {
        return serviceTemplate;
    }
}
