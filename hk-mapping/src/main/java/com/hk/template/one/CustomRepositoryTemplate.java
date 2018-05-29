package com.hk.template.one;

import java.io.File;

/**
 * @author huangkai
 * @date 2018-5-29 22:26
 */
public class CustomRepositoryTemplate extends RepositoryTemplate {

    public CustomRepositoryTemplate(File outputFile, String packageName, String className, String entityClassName, String baseEntityClassName, String comment, String author) {
        super(outputFile, packageName, className, entityClassName, baseEntityClassName, comment, author);
    }
}
