package com.hk.template;


import java.io.File;

/**
 * @author huangkai
 * @date 2018-5-30 21:23
 */
public class SimpleControllerTemplate extends AbstractTemplate implements ControllerTemplate {

    private ServiceTemplate serviceTemplate;

    public SimpleControllerTemplate(ServiceTemplate serviceTemplate,
                                    File outputFile, String className, String templatePath) {
        super(outputFile, className, templatePath);
        this.serviceTemplate = serviceTemplate;
    }

    public SimpleControllerTemplate(ServiceTemplate serviceTemplate,
                                    File outputFile, String className, String templatePath,
                                    String comment, String author, String version) {
        super(outputFile, className, templatePath, comment, author, version);
        this.serviceTemplate = serviceTemplate;
    }

    @Override
    public ServiceTemplate getServiceTemplate() {
        return serviceTemplate;
    }
}
