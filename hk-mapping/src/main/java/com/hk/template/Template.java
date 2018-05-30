package com.hk.template;

import com.hk.commons.util.StringUtils;
import com.hk.commons.util.date.DatePattern;
import com.hk.core.TemplateEngine;
import com.hk.util.FileAssistant;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 模板标记接口
 *
 * @author: huangkai
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
     * SimpleClassName
     *
     * @return
     */
    default String getSimpleClassName() {
        return StringUtils.substringAfterLast(getClassName(), ".");
    }

    /**
     * package Name
     *
     * @return
     */
    default String getPackageName() {
        return StringUtils.substringBeforeLast(getClassName(), ".");
    }

    /**
     * @return
     */
    File getOutputFile();

    /**
     * 获取注释
     *
     * @return
     */
    String getComment();

    /**
     * 日期
     *
     * @return
     */
    default String getDateAsString() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DatePattern.YYYY_MM_DD_HH_MM_SS.getPattern()));
    }

    /**
     * 作者
     *
     * @return
     */
    default String getAuthor() {
        return System.getProperty("user.name");
    }

    String getTemplatePath();

    /**
     * 版本号
     *
     * @return
     */
    String getVersion();

    /**
     * 如果文件存在，是否强制覆盖
     *
     * @return
     */
    default boolean forceCover() {
        return true;
    }

    default void genreate() {
        String template = TemplateEngine.parseTemplate(this);
        FileAssistant.write(template,getOutputFile());
    }


}
