package com.hk.template;

import java.io.File;

/**
 * @author huangkai
 * @date 2018-5-30 21:38
 */
public class SimpleCustomImplReopsitoryTemplate extends AbstractTemplate implements CustomImplRepositoryTemplate {

    private CustomRepositoryTemplate customRepositoryTemplate;

    public SimpleCustomImplReopsitoryTemplate(CustomRepositoryTemplate customRepositoryTemplate,
                                              File outputFile, String className, String templatePath) {
        super(outputFile, className, templatePath);
        this.customRepositoryTemplate = customRepositoryTemplate;
    }

    public SimpleCustomImplReopsitoryTemplate(CustomRepositoryTemplate customRepositoryTemplate,
                                              File outputFile, String className, String templatePath,
                                              String comment, String author, String version) {
        super(outputFile, className, templatePath, comment, author, version);
        this.customRepositoryTemplate = customRepositoryTemplate;
    }

    public void setCustomRepositoryTemplate(CustomRepositoryTemplate customRepositoryTemplate) {
        this.customRepositoryTemplate = customRepositoryTemplate;
    }

    @Override
    public CustomRepositoryTemplate getCustomRepositoryTemplate() {
        return customRepositoryTemplate;
    }
}
