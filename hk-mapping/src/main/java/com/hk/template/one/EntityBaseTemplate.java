package com.hk.template.one;

import com.hk.template.AbstractTemplate;

import java.io.File;

/**
 * @author: huangkai
 * @date 2018-05-29 13:14
 */

public class EntityBaseTemplate extends AbstractTemplate {

    public EntityBaseTemplate(File outputFile, String packageName, String className,String baseEntityClassName, String comment, String author) {
        setOutputFile(outputFile);
        setPackageName(packageName);
        setBaseEntityClassName(className);
        setBaseEntityClassName(baseEntityClassName);
        setComment(comment);
        setAuthor(author);
    }

}
