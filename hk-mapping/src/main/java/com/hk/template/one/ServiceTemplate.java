package com.hk.template.one;

import java.io.File;

/**
 * @author: huangkai
 * @date 2018-05-29 18:07
 */
public class ServiceTemplate extends RepositoryTemplate {


    public ServiceTemplate(File outputFile, String packageName, String className, String entityClassName, String baseEntityClassName, String comment, String author) {
        super(outputFile, packageName, className, entityClassName, baseEntityClassName, comment, author);
    }
}
