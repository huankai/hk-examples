package com.hk.template.one;

import java.io.File;

/**
 * @author huangkai
 * @date 2018-5-29 22:26
 */
public class RepositoryImplTemplate extends RepositoryTemplate {
    
    public RepositoryImplTemplate(File outputFile, String packageName, String customReposirotyClassName, String className, String entityClassName, String baseEntityClassName, String comment, String author) {
        super(outputFile, packageName, customReposirotyClassName, className, entityClassName, baseEntityClassName, comment, author);

    }
}
