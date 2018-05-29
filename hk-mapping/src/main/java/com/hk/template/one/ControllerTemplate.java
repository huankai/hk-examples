package com.hk.template.one;

import com.hk.commons.util.StringUtils;
import com.hk.template.AbstractTemplate;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * @author: huangkai
 * @date 2018-05-29 18:17
 */
public class ControllerTemplate extends AbstractTemplate {

    @Getter
    @Setter
    private String serviceClassName;

    public ControllerTemplate(File outputFile, String packageName, String className, String serviceClassName, String baseEntityClassName, String comment, String author) {
        setOutputFile(outputFile);
        setPackageName(packageName);
        setClassName(className);
        this.serviceClassName = serviceClassName;
        setBaseEntityClassName(baseEntityClassName);
        setComment(comment);
        setAuthor(author);
    }

    public String getServiceClassSimpleName() {
        return serviceClassName.substring(serviceClassName.lastIndexOf(".") + 1);
    }

    public String getServiceVar() {
        return StringUtils.uncapitalize(getServiceClassSimpleName());
    }
}
