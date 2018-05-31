package com.hk.core;

import com.hk.commons.util.StringUtils;
import com.hk.entity.Table;
import com.hk.entity.TemplateParam;
import com.hk.template.*;

import java.io.File;
import java.util.List;

/**
 * @author huangkai
 * @date 2018-5-30 23:58
 */
public class TemplteGenerate {

    private static final String JAVA_DIR = "./src/main/java/";

    public static void generate(List<Table> tableList, TemplateParam param) {
        BaseEntityTemplate baseEntityTemplate = generateBaseEntityTemplate(param);
        tableList.forEach(table -> {
            EntityTemplate entityTemplate = generateEntityTemplate(table, param, baseEntityTemplate);
            CustomRepositoryTemplate repositoryTemplate = generateCustomRepositoryTemplate(table, param);
        });
    }

    private static CustomRepositoryTemplate generateCustomRepositoryTemplate(Table table, TemplateParam param) {
        CustomRepositoryTemplate customRepositoryTemplate = new SimpleCustomReopsitoryTemplate();
        File file = new File(getOutputFile(param.getRootPath(), param.formatCustomRepositoryNameByEntitySimpleName(table.getClassName())), formatJavaFileName(table.getClassName()));
        customRepositoryTemplate.setOutputFile(file);
        customRepositoryTemplate.setClassName(table.getClassName());
        customRepositoryTemplate.setComment(table.getComment());
        customRepositoryTemplate.setAuthor(param.getAuthor());
        customRepositoryTemplate.setVersion(param.getVersion());
        customRepositoryTemplate.genreate();
        return customRepositoryTemplate;
    }


    /**
     * @param table
     * @param baseEntityTemplate
     * @param templatePath
     * @return
     */
    public static EntityTemplate generateEntityTemplate(Table table, TemplateParam param, BaseEntityTemplate baseEntityTemplate) {
        File file = new File(getOutputFile(param.getRootPath(), param.getEntityPackageName()), formatJavaFileName(table.getClassName()));
        EntityTemplate entityTemplate = new SimpleEntityTemplate();
        entityTemplate.setTable(table);
        entityTemplate.setOutputFile(file);
        entityTemplate.setBaseEntityTemplate(baseEntityTemplate);
        entityTemplate.setClassName(table.getClassName());
        entityTemplate.setComment(table.getComment());
        entityTemplate.setAuthor(param.getAuthor());
        entityTemplate.setVersion(param.getVersion());
        entityTemplate.setIngoreColumns(param.getIngoreColumns());
        entityTemplate.setUseLombokFramework(param.isUseLombok());
        entityTemplate.genreate();
        return entityTemplate;
    }

    private static BaseEntityTemplate generateBaseEntityTemplate(TemplateParam param) {
        BaseEntityTemplate entityTemplate = new SpringDataJpaBaseEntityTemplate();
        entityTemplate.setAuthor(param.getAuthor());
        entityTemplate.setClassName(param.getBaseEntityClassName());
        entityTemplate.setVersion(param.getVersion());
        entityTemplate.setOutputFile(new File(String.format("%s.%s",
                getOutputFile(param.getRootPath(), param.getBaseEntityPackage()), param.getBaseEntityClassName())));
        entityTemplate.genreate();
        return entityTemplate;
    }

    private static String formatJavaFileName(String fileName) {
        return fileName + ".java";
    }

    private static String getOutputFile(String rootPath, String packageName) {
        String classNamePath = StringUtils.replaceAll(packageName, ".", "/");
        String folder = rootPath + JAVA_DIR + classNamePath;
        File file = new File(folder);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            if (!mkdirs) {
                throw new RuntimeException("can not create folder by :" + file.getName());
            }
        }
        return file.getAbsolutePath();

    }


}
