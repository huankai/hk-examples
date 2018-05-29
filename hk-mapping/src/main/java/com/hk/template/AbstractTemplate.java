package com.hk.template;

import lombok.Data;

import java.io.File;

/**
 * @author: huangkai
 * @date 2018-05-29 13:12
 */
@Data
public abstract class AbstractTemplate implements Template {

    /**
     * 输出文件
     */
    private File outputFile;

    /**
     * 输出类名
     */
    private String className;

    /**
     * 包名
     */
    private String packageName;

    /**
     * Base Entity class Name
     */
    private String baseEntityClassName;

    /**
     * 注释
     */
    private String comment;

    /**
     * 作者
     */
    private String author;

    public String getBaseEntityClassSimpleName() {
        return baseEntityClassName.substring(baseEntityClassName.lastIndexOf(".") + 1);
    }
}
