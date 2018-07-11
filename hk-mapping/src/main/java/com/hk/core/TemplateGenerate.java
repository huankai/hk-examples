package com.hk.core;

import com.hk.commons.util.StringUtils;
import com.hk.entity.Table;
import com.hk.entity.TemplateParam;
import com.hk.template.*;

import java.io.File;
import java.util.List;

/**
 * @author: kevin
 * @date 2018-5-30 23:58
 */
public class TemplateGenerate {

    private static final String JAVA_DIR = "./src/main/java/";

    /**
     * 生成所有
     *
     * @param tableList
     * @param param
     */
    public static void generate(List<Table> tableList, TemplateParam param) {
        BaseEntityTemplate baseEntityTemplate = generateBaseEntityTemplate(param);
        tableList.forEach(table -> {
            EntityTemplate entityTemplate = generateEntityTemplate(table, param, baseEntityTemplate);
            CustomRepositoryTemplate customRrepositoryTemplate = generateCustomRepositoryTemplate(table, param);
            CustomImplRepositoryTemplate customImplRepositoryTemplate = generateCustomRepositoryImplTemplate(table,
                    customRrepositoryTemplate, param);
            RepositoryTemplate repositoryTemplate = generateRepositoryTemplate(table, entityTemplate, customRrepositoryTemplate, param);
            ServiceTemplate serviceTemplate = generateServiceTemplate(table, entityTemplate, param);
            generateServiceImplTemplate(table, repositoryTemplate, serviceTemplate, param);
            generateControllerTemplate(table, serviceTemplate, param);
        });
    }

    /**
     * 生成Controller
     *
     * @param table
     * @param serviceTemplate
     * @param param
     */
    public static void generateControllerTemplate(Table table,
                                                  ServiceTemplate serviceTemplate,
                                                  TemplateParam param) {
        ControllerTemplate controllerTemplate = new SimpleControllerTemplate();
        controllerTemplate.setServiceTemplate(serviceTemplate);
        File file = new File(getOutputFile(param.getRootPath(),
                param.getControllerPackage()),
                formatJavaFileName(param.formatControllerSimpleNameByEntitySimpleName(table.getClassName())));
        controllerTemplate.setOutputFile(file);
        controllerTemplate.setClassName(param.formatControllerNameByTableName(table.getClassName()));
        controllerTemplate.setComment(table.getComment());
        controllerTemplate.setAuthor(param.getAuthor());
        controllerTemplate.setVersion(param.getVersion());
        controllerTemplate.setTemplatePath(param.getControllerPath());
        controllerTemplate.genreate();
    }


    /**
     * 生成ServiceImpl
     *
     * @param table
     * @param repositoryTemplate
     * @param serviceTemplate
     * @param param
     */
    public static void generateServiceImplTemplate(Table table,
                                                   RepositoryTemplate repositoryTemplate,
                                                   ServiceTemplate serviceTemplate,
                                                   TemplateParam param) {
        ServiceImplTemplate serviceImplTemplate = new SimpleServiceImplTemplate();
        serviceImplTemplate.setRepositoryTemplate(repositoryTemplate);
        serviceImplTemplate.setServiceTemplate(serviceTemplate);
        File file = new File(getOutputFile(param.getRootPath(), param.formatServiceImplPackage()),
                formatJavaFileName(param.formatServiceImplSimpleNameByEntitySimpleName(table.getClassName())));
        serviceImplTemplate.setOutputFile(file);
        serviceImplTemplate.setClassName(param.formatServiceImplNameByTableName(table.getClassName()));
        serviceImplTemplate.setComment(table.getComment());
        serviceImplTemplate.setAuthor(param.getAuthor());
        serviceImplTemplate.setVersion(param.getVersion());
        serviceImplTemplate.setTemplatePath(param.getServiceImplPath());
        serviceImplTemplate.genreate();
    }

    public static ServiceTemplate generateServiceTemplate(Table table, EntityTemplate entityTemplate, TemplateParam param) {
        ServiceTemplate serviceTemplate = new SimpleServiceTemplate();
        File file = new File(getOutputFile(param.getRootPath(), param.getServicePackage()),
                formatJavaFileName(param.formatServiceSimpleNameByEntitySimpleName(table.getClassName())));
        serviceTemplate.setOutputFile(file);
        serviceTemplate.setEntityTemplate(entityTemplate);
        serviceTemplate.setClassName(param.formatServiceNameByTableName(table.getClassName()));
        serviceTemplate.setComment(table.getComment());
        serviceTemplate.setAuthor(param.getAuthor());
        serviceTemplate.setVersion(param.getVersion());
        serviceTemplate.setTemplatePath(param.getServicePath());
        serviceTemplate.genreate();
        return serviceTemplate;
    }

    public static RepositoryTemplate generateRepositoryTemplate(Table table,
                                                                EntityTemplate entityTemplate,
                                                                CustomRepositoryTemplate customRepositoryTemplate,
                                                                TemplateParam param) {
        RepositoryTemplate repositoryTemplate = new SimpleRepositoryTemplate();
        repositoryTemplate.setEntityTemplate(entityTemplate);
        repositoryTemplate.setCustomRepositoryTemplate(customRepositoryTemplate);
        File file = new File(getOutputFile(param.getRootPath(), param.getRepositoryPackageName()),
                formatJavaFileName(param.formatRepositorySimpleNameByEntitySimpleName(table.getClassName())));
        repositoryTemplate.setOutputFile(file);
        repositoryTemplate.setClassName(param.formatRepositoryNameByTableName(table.getClassName()));
        repositoryTemplate.setComment(table.getComment());
        repositoryTemplate.setAuthor(param.getAuthor());
        repositoryTemplate.setVersion(param.getVersion());
        repositoryTemplate.setTemplatePath(param.getRepositoryPath());
        repositoryTemplate.genreate();
        return repositoryTemplate;
    }

    public static CustomImplRepositoryTemplate generateCustomRepositoryImplTemplate(Table table,
                                                                                    CustomRepositoryTemplate repositoryTemplate, TemplateParam param) {

        CustomImplRepositoryTemplate template = new SimpleCustomImplReopsitoryTemplate();
        template.setCustomRepositoryTemplate(repositoryTemplate);

        File file = new File(getOutputFile(param.getRootPath(), param.formatCustomRepositoryImplPackageName()),
                formatJavaFileName(param.formatCustomRepositoryImplClassName(table.getClassName())));
        template.setOutputFile(file);
        template.setClassName(param.formatCustomRepositoryImplNameByTableName(table.getTableName()));
        template.setComment(table.getComment());
        template.setAuthor(param.getAuthor());
        template.setVersion(param.getVersion());
        template.setTemplatePath(param.getCustomImplResositoryPath());
        template.genreate();
        return template;
    }

    public static CustomRepositoryTemplate generateCustomRepositoryTemplate(Table table, TemplateParam param) {
        String className = param.formatCustomRepositorySimpleClassNameByEntitySimpleName(table.getClassName());

        CustomRepositoryTemplate customRepositoryTemplate = new SimpleCustomRepositoryTemplate();
        File file = new File(getOutputFile(param.getRootPath(), param.formatCustomRepositoryPackageName()),
                formatJavaFileName(className));
        customRepositoryTemplate.setOutputFile(file);
        customRepositoryTemplate.setClassName(param.formatCustomRepositoryNameByTableName(table.getTableName()));
        customRepositoryTemplate.setComment(table.getComment());
        customRepositoryTemplate.setAuthor(param.getAuthor());
        customRepositoryTemplate.setVersion(param.getVersion());
        customRepositoryTemplate.setTemplatePath(param.getCustomRepositoryPath());
        customRepositoryTemplate.genreate();
        return customRepositoryTemplate;
    }

    /**
     * @param table
     * @param baseEntityTemplate
     * @return
     */
    public static EntityTemplate generateEntityTemplate(Table table, TemplateParam param,
                                                        BaseEntityTemplate baseEntityTemplate) {
        File file = new File(getOutputFile(param.getRootPath(), param.getEntityPackageName()), formatJavaFileName(table.getClassName()));
        EntityTemplate entityTemplate = new SimpleEntityTemplate();
        entityTemplate.setIngoreColumns(param.getIngoreColumns());
        entityTemplate.setTable(table);
        entityTemplate.setOutputFile(file);
        entityTemplate.setBaseEntityTemplate(baseEntityTemplate);
        entityTemplate.setClassName(param.formatEntityNameByTableName(table.getClassName()));
        entityTemplate.setComment(table.getComment());
        entityTemplate.setAuthor(param.getAuthor());
        entityTemplate.setVersion(param.getVersion());
        entityTemplate.setUseLombokFramework(param.isUseLombok());
        entityTemplate.setTemplatePath(param.getEntityPath());
        entityTemplate.genreate();
        return entityTemplate;
    }

    public static BaseEntityTemplate generateBaseEntityTemplate(TemplateParam param) {
        BaseEntityTemplate entityTemplate = new SpringDataJpaBaseEntityTemplate();
        entityTemplate.setAuthor(param.getAuthor());
        entityTemplate.setClassName(param.getBaseEntityClassName());
        entityTemplate.setVersion(param.getVersion());
        entityTemplate.setOutputFile(new File(getOutputFile(param.getRootPath(), param.getBaseEntityPackage()), formatJavaFileName(param.getBaseEntitySimpleName())));
        entityTemplate.setTemplatePath(param.getBaseEntityPath());
        entityTemplate.genreate();

        return entityTemplate;
    }

    private static String formatJavaFileName(String fileName) {
        return fileName + ".java";
    }

    private static String getOutputFile(String rootPath, String packageName) {
        String classNamePath = StringUtils.replace(packageName, ".", "/");
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
