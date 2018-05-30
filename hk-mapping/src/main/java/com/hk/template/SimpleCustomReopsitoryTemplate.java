package com.hk.template;

import java.io.File;

/**
 * @author huangkai
 * @date 2018-5-30 21:38
 */
public class SimpleCustomReopsitoryTemplate extends AbstractTemplate implements CustomRepositoryTemplate {

    public SimpleCustomReopsitoryTemplate(File outputFile, String className, String templatePath) {
        super(outputFile, className, templatePath);
    }

    public SimpleCustomReopsitoryTemplate(File outputFile, String className, String templatePath, String comment, String author, String version) {
        super(outputFile, className, templatePath, comment, author, version);
    }
}
