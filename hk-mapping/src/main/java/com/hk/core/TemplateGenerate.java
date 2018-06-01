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
            ServiceTemplate serviceTemplate = generateServiceTemplate(table, param);
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
                param.formatControllerNameByEntitySimpleName(table.getClassName())),
                formatJavaFileName(table.getClassName()));
        controllerTemplate.setOutputFile(file);
        controllerTemplate.setClassName(table.getClassName());
        controllerTemplate.setComment(table.getComment());
        controllerTemplate.setAuthor(param.getAuthor());
        controllerTemplate.setVersion(param.getVersion());
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
        File file = new File(getOutputFile(param.getRootPath(), param.formatServiceImplNameByEntitySimpleName(table.getClassName())),
                formatJavaFileName(table.getClassName()));
        serviceImplTemplate.setOutputFile(file);
        serviceImplTemplate.setClassName(table.getClassName());
        serviceImplTemplate.setComment(table.getComment());
        serviceImplTemplate.setAuthor(param.getAuthor());
        serviceImplTemplate.setVersion(param.getVersion());
        serviceImplTemplate.genreate();
    }

    public static ServiceTemplate generateServiceTemplate(Table table, TemplateParam param) {
        ServiceTemplate serviceTemplate = new SimpleServiceTemplate();
        File file = new File(getOutputFile(param.getRootPath(), param.formatServiceNameByEntitySimpleName(table.getClassName())),
                formatJavaFileName(table.getClassName()));
        serviceTemplate.setOutputFile(file);
        serviceTemplate.setClassName(table.getClassName());
        serviceTemplate.setComment(table.getComment());
        serviceTemplate.setAuthor(param.getAuthor());
        serviceTemplate.setVersion(param.getVersion());
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
        File file = new File(getOutputFile(param.getRootPath(), param.formatRepositoryNameByEntitySimpleName(table.getClassName())),
                formatJavaFileName(table.getClassName()));
        repositoryTemplate.setOutputFile(file);
        repositoryTemplate.setClassName(table.getClassName());
        repositoryTemplate.setComment(table.getComment());
        repositoryTemplate.setAuthor(param.getAuthor());
        repositoryTemplate.setVersion(param.getVersion());
        repositoryTemplate.genreate();
        return repositoryTemplate;
    }

    public static CustomImplRepositoryTemplate generateCustomRepositoryImplTemplate(Table table,
                                                                                    CustomRepositoryTemplate repositoryTemplate, TemplateParam param) {

        CustomImplRepositoryTemplate template = new SimpleCustomImplReopsitoryTemplate();
        template.setCustomRepositoryTemplate(repositoryTemplate);

        File file = new File(getOutputFile(param.getRootPath(), param.formatCustomRepositoryImplNameByEntitySimpleName(table.getClassName())),
                formatJavaFileName(table.getClassName()));
        template.setOutputFile(file);
        template.setClassName(table.getClassName());
        template.setComment(table.getComment());
        template.setAuthor(param.getAuthor());
        template.setVersion(param.getVersion());
        template.genreate();
        return template;
    }

    public static CustomRepositoryTemplate generateCustomRepositoryTemplate(Table table, TemplateParam param) {
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
     * @return
     */
    public static EntityTemplate generateEntityTemplate(Table table, TemplateParam param,
                                                        BaseEntityTemplate baseEntityTemplate) {
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

    public static BaseEntityTemplate generateBaseEntityTemplate(TemplateParam param) {
        BaseEntityTemplate entityTemplate = new SpringDataJpaBaseEntityTemplate();
        entityTemplate.setAuthor(param.getAuthor());
        entityTemplate.setClassName(param.getBaseEntityClassName());
        entityTemplate.setVersion(param.getVersion());
        entityTemplate.setOutputFile(new File(String.format("%s/%s",
                getOutputFile(param.getRootPath(), param.getBaseEntityPackage()), formatJavaFileName(param.getBaseEntitySimpleName()))));
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
