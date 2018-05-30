package com.hk.template;

import com.hk.commons.util.AssertUtils;
import com.hk.commons.util.StringUtils;

import java.io.File;

/**
 * @author: huangkai
 * @date 2018-05-29 13:12
 */
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
     * 注释
     */
    private String comment;

    /**
     * 作者
     */
    private String author;

    /**
     * 版本号
     */
    private String version;

    /**
     * 模板所在路径
     */
    private String templatePath;

    public AbstractTemplate(File outputFile, String className,String templatePath) {
        this(outputFile, className,templatePath, null, null, null);
    }

    protected AbstractTemplate(File outputFile, String className,String templatePath, String comment, String author, String version) {
        AssertUtils.notNull(outputFile, "outputFile must not be null");
        AssertUtils.notNull(className, "className must not be null");
        this.outputFile = outputFile;
        this.className = className;
        this.templatePath = templatePath;
        this.comment = comment;
        this.author = author;
        this.version = version;
    }

    @Override
    public File getOutputFile() {
        return outputFile;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public String getAuthor() {
        return StringUtils.isEmpty(author) ? Template.super.getAuthor() : author;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getTemplatePath() {
        return templatePath;
    }

    //    public String getBaseEntityClassSimpleName() {
//        return baseEntityClassName.substring(baseEntityClassName.lastIndexOf(".") + 1);
//    }
//
//    public void parseAndaddFileQueue(TemplateEngine engine) {
//        File outputFile = getOutputFile();
//        if (forceCover() || !outputFile.exists()) {
//            FileQueue.add(new FileQueue.Entry(outputFile, engine.parseTemplate(this)));
//        }
//    }
}
