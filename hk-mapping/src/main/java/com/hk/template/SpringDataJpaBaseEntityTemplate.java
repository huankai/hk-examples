package com.hk.template;

import java.io.File;

/**
 * @author huangkai
 * @date 2018-5-30 21:02
 */
public class SpringDataJpaBaseEntityTemplate extends AbstractTemplate implements BaseEntityTemplate {

    public SpringDataJpaBaseEntityTemplate(File outputFile, String className, String templatePath,
                                           String comment, String author, String version) {
        super(outputFile, className, templatePath, comment, author, version);
    }
}
