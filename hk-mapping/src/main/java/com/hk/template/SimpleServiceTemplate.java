package com.hk.template;

import java.io.File;

/**
 * @author huangkai
 * @date 2018-5-30 21:23
 */
public class SimpleServiceTemplate extends AbstractTemplate implements ServiceTemplate {

    public SimpleServiceTemplate(File outputFile, String className, String templatePath) {
        super(outputFile, className, templatePath);
    }

    public SimpleServiceTemplate(File outputFile, String className, String templatePath,
                                 String comment, String author, String version) {
        super(outputFile, className, templatePath, comment, author, version);
    }
}
