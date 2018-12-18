package com.hk.template;

import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.StringUtils;
import com.hk.commons.util.date.DatePattern;
import com.hk.commons.util.date.DateTimeUtils;
import com.hk.core.TemplateEngine;
import com.hk.util.FileAssistant;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

/**
 * 模板标记接口
 *
 * @author kevin
 * @date 2018-05-29 13:11
 */
public interface Template {

    /**
     * 获取ClassName
     *
     * @return
     */
    String getClassName();

    /**
     * The class name is set.
     *
     * @param className className
     */
    void setClassName(String className);

    /**
     * SimpleClassName
     *
     * @return simpleClassName
     */
    default String getSimpleClassName() {
        return StringUtils.substringAfterLast(getClassName(), ".");
    }

    default String getUnSimpleClassName() {
        return StringUtils.uncapitalize(getSimpleClassName());
    }

    /**
     * package Name
     *
     * @return packageName
     */
    default String getPackageName() {
        return StringUtils.substringBeforeLast(getClassName(), ".");
    }

    /**
     * @return outputFile
     */
    File getOutputFile();

    /**
     * @param outputFile
     */
    void setOutputFile(File outputFile);

    /**
     * 获取注释
     *
     * @return
     */
    String getComment();

    /**
     * @param comment
     */
    void setComment(String comment);

    /**
     * 注释日期
     *
     * @return
     */
    default String getDateAsString() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DatePattern.YYYY_MM_DD_HH_MM_SS.getPattern()));
    }

    /**
     * string format date
     *
     * @param date
     */
    void setDateAsString(String date);

    /**
     * @param date
     * @param pattern
     */
    default void setDateAsString(Date date, DatePattern pattern) {
        setDateAsString(DateTimeUtils.dateToString(date, pattern));
    }

    /**
     * @param localDateTime
     * @param pattern
     */
    default void setDateAsString(LocalDateTime localDateTime, DatePattern pattern) {
        setDateAsString(DateTimeUtils.localDateTimeToString(localDateTime, pattern));
    }

    /**
     * <p>
     * 模板需要导入的包
     * </p>
     *
     * @return
     */
    Set<String> getImportClassNames();

    /**
     * import var
     *
     * @param vars
     */
    default void importClassNames(String... vars) {
        CollectionUtils.addAll(getImportClassNames(), Arrays.asList(vars));
    }

    /**
     * 作者
     *
     * @return
     */
    default String getAuthor() {
        return System.getProperty("user.name");
    }

    /**
     * @param author
     */
    void setAuthor(String author);

    /**
     * Template resource
     *
     * @return
     */
    String getTemplatePath();

    /**
     * @param templatePath
     */
    void setTemplatePath(String templatePath);

    /**
     * 版本号
     *
     * @return
     */
    String getVersion();

    /**
     * @param version
     */
    void setVersion(String version);

    /**
     * 如果文件存在，是否强制覆盖
     *
     * @return
     */
    default boolean forceCover() {
        return true;
    }

    /**
     *
     */
    default void genreate() {
        String template = TemplateEngine.parseTemplate(this);
        if (null != template) {
            FileAssistant.write(template, getOutputFile());
        }
    }


}
