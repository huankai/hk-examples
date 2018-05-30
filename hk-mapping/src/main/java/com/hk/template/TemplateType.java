package com.hk.template;

/**
 * @author: huangkai
 * @date 2018-05-30 17:52
 */
public enum TemplateType {

    ONE("one"),

    TWO("two");

    private String path;

    TemplateType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
