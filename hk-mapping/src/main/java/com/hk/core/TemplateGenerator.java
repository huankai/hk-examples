package com.hk.core;

import com.hk.commons.util.FileUtils;
import com.hk.config.Configuration;
import com.hk.entity.Table;
import com.hk.template.one.*;
import com.hk.util.FileAssistant;
import com.hk.util.FileQueue;

import java.io.File;
import java.util.List;

/**
 * @author: huangkai
 * @date 2018-05-29 15:04
 */
public class TemplateGenerator {

    public static final String JAVA_DIR = "./src/main/java/";

    /* BaseEntity output path */
    protected final String BASE_ENTITY_OUTPUTP_PATH;

    /*  BaseEntity output path  */
    protected final String ENTITY_OUTPUTPPATH;

    /* *************** Repository ******************* */
    protected final String REPOSITORY_OUTPUTPPATH;

    protected final String CUSTOM_REPOSITORY_OUTPUTPPATH;

    protected final String REPOSITORY_IMPL_OUTPUTPPATH;

    /* *************** Service ******************* */

    protected final String SERVICE_OUTPUTPPATH;

    /* *************** Controller ******************* */

    protected final String CONTROLLER_OUTPUTPPATH;

    private final TemplateEngine engine;

    private final Configuration configuration;

    private String baseEntityClassName;

    public TemplateGenerator(ConfigurationStorage storage) {
        configuration = storage.getConfiguration();
        engine = new TemplateEngine(configuration);
        BASE_ENTITY_OUTPUTP_PATH = getOutputPath(configuration.getRootPath(), configuration.getBaseEntityPackage());
        ENTITY_OUTPUTPPATH = getOutputPath(configuration.getRootPath(), configuration.getEntityPackage());
        REPOSITORY_OUTPUTPPATH = getOutputPath(configuration.getRootPath(), configuration.getRepositoryPackage());
        CUSTOM_REPOSITORY_OUTPUTPPATH = getOutputPath(configuration.getRootPath(), configuration.getRepositoryPackage() + "." + configuration.getCustomRepositoryPackageSuffix());
        REPOSITORY_IMPL_OUTPUTPPATH = getOutputPath(configuration.getRootPath(), configuration.getRepositoryPackage() + "." + configuration.getCustomRepositoryImplPackageSuffix());
        SERVICE_OUTPUTPPATH = getOutputPath(configuration.getRootPath(), configuration.getServicePackage());
        CONTROLLER_OUTPUTPPATH = getOutputPath(configuration.getRootPath(), configuration.getControllerPackage());
        baseEntityClassName = String.format("%s.%s", configuration.getBaseEntityPackage(), configuration.getBaseEntityName());
    }

    public void generate(List<Table> tables) {
        generate(tables, false);
    }

    public void generate(List<Table> tables, boolean deleteFiles) {
        if (deleteFiles) {
            deleteFiles();
        }
        baseEntityTemplateToQueue(tables);
        entityTemplateToQueue(tables);

        repositoryTemplateToQueue(tables);
        customRepositoryTemplateToQueue(tables);
        repositoryImplTemplateToQueue(tables);

        serviceTemplateToQueue(tables);

        controllerTemplateToQueue(tables);

        execute();
    }

    private void baseEntityTemplateToQueue(List<Table> tables) {
        new EntityBaseTemplate(buildOutputFile(BASE_ENTITY_OUTPUTP_PATH, configuration.getBaseEntityName()),
                configuration.getBaseEntityPackage(),
                configuration.getBaseEntityName(),
                baseEntityClassName,
                null,
                configuration.getAuthor()).parseAndaddFileQueue(engine);
    }

    private void entityTemplateToQueue(List<Table> tables) {
        tables.forEach(table -> {
            EntityTemplate entityTemplate = new EntityTemplate(table,
                    buildOutputFile(ENTITY_OUTPUTPPATH, table.getClassName()),
                    configuration.getEntityPackage(),
                    baseEntityClassName,
                    configuration.getAuthor());
            entityTemplate.getExcludeColumn().addAll(configuration.getEntityIngoreFields());
            entityTemplate.parseAndaddFileQueue(engine);
        });
    }

    private void repositoryTemplateToQueue(List<Table> tables) {
        tables.forEach(table -> new RepositoryTemplate(buildOutputFile(REPOSITORY_OUTPUTPPATH, table.getClassName() + configuration.getRepositoryClassnameSuffix()),
                configuration.getRepositoryPackage(),
                configuration.getRepositoryPackage() + "." + configuration.getCustomRepositoryPackageSuffix() + "." + configuration.getCustomRepositoryClassnamePrefix() + table.getClassName() + configuration.getRepositoryClassnameSuffix(),
                table.getClassName() + configuration.getRepositoryClassnameSuffix(),
                configuration.getEntityPackage() + "." + table.getClassName(),
                baseEntityClassName,
                table.getComment(),
                configuration.getAuthor())
                .parseAndaddFileQueue(engine));
    }

    private void repositoryImplTemplateToQueue(List<Table> tables) {
        tables.forEach(table ->
                new RepositoryImplTemplate(buildOutputFile(REPOSITORY_IMPL_OUTPUTPPATH, table.getClassName() + configuration.getRepositoryClassnameSuffix() + configuration.getCustomRepositoryImplClassnameSuffix()),
                        configuration.getRepositoryPackage() + "." + configuration.getCustomRepositoryImplPackageSuffix(),
                        configuration.getRepositoryPackage() + "." + configuration.getCustomRepositoryPackageSuffix() + "." + configuration.getCustomRepositoryClassnamePrefix() + table.getClassName() + configuration.getRepositoryClassnameSuffix(),
                        table.getClassName() + configuration.getRepositoryClassnameSuffix() + configuration.getCustomRepositoryImplClassnameSuffix(),
                        configuration.getEntityPackage() + "." + table.getClassName(),
                        baseEntityClassName, table.getComment(),
                        configuration.getAuthor())
                        .parseAndaddFileQueue(engine));
    }

    private void customRepositoryTemplateToQueue(List<Table> tables) {
        tables.forEach(table -> new CustomRepositoryTemplate(buildOutputFile(CUSTOM_REPOSITORY_OUTPUTPPATH, configuration.getCustomRepositoryClassnamePrefix() + table.getClassName() + configuration.getRepositoryClassnameSuffix()),
                configuration.getRepositoryPackage() + "." + configuration.getCustomRepositoryPackageSuffix(),
                configuration.getCustomRepositoryClassnamePrefix() + table.getClassName() + configuration.getRepositoryClassnameSuffix(),
                configuration.getEntityPackage() + "." + table.getClassName(),
                baseEntityClassName,
                table.getComment(),
                configuration.getAuthor())
                .parseAndaddFileQueue(engine));
    }

    private void serviceTemplateToQueue(List<Table> tables) {
        tables.forEach(table -> new ServiceTemplate(buildOutputFile(SERVICE_OUTPUTPPATH, table.getClassName() + configuration.getServiceClassnameSuffix()),
                configuration.getServicePackage(),
                table.getClassName() + configuration.getServiceClassnameSuffix(),
                configuration.getEntityPackage() + "." + table.getClassName(),
                baseEntityClassName,
                table.getComment(),
                configuration.getAuthor())
                .parseAndaddFileQueue(engine));
    }

    private void controllerTemplateToQueue(List<Table> tables) {
        tables.forEach(table -> new ControllerTemplate(buildOutputFile(CONTROLLER_OUTPUTPPATH, table.getClassName() + configuration.getControllerClassnameSuffix()),
                configuration.getControllerPackage(),
                table.getClassName() + configuration.getControllerClassnameSuffix(),
                configuration.getServicePackage() + "." + table.getClassName() + configuration.getServiceClassnameSuffix(),
                baseEntityClassName,
                table.getComment(),
                configuration.getAuthor())
                .parseAndaddFileQueue(engine));
    }

    private void execute() {
        while (FileQueue.hasNext()) {
            FileQueue.Entry entry = FileQueue.pop();
            FileAssistant.write(entry);
            print(entry);
        }
    }

    private void print(FileQueue.Entry entry) {
        String path = entry.getFile().getAbsolutePath();
        path = path.replace("\\", "/");
        while (path.contains("/..")) {
            int index = path.indexOf("../");
            String lpath = path.substring(0, index - 1);
            String rpath = path.substring(index + 2);
            path = lpath.substring(0, lpath.lastIndexOf("/")) + rpath;
        }
        path = path.replace("/.", "");
        System.out.println(String.format("[C] %s %s", path, "\n"));
    }

    private File buildOutputFile(String outputDir, String fileName) {
        return buildOutputFile(outputDir, fileName, "java");
    }

    private File buildOutputFile(String outputDir, String fileName, String ext) {
        fileName += "." + ext;
        return new File(outputDir, fileName);
    }

    private void deleteFiles() {
        File fileDir = new File(configuration.getRootPath() + JAVA_DIR);
        FileUtils.deleteFile(fileDir, "java");
    }

    private String getOutputPath(String rootDir, String suffix) {
        String outputDir = JAVA_DIR + suffix.replace(".", "/");
        outputDir = rootDir == null ? outputDir : rootDir + outputDir;
        File file = new File(outputDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }
}
