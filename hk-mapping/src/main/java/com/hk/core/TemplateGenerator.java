package com.hk.core;

import com.hk.entity.Table;
import com.hk.template.one.*;
import com.hk.util.ConfigurationUtils;
import com.hk.util.FileAssistant;
import com.hk.util.FileQueue;

import java.io.File;
import java.util.List;

/**
 * @author: huangkai
 * @date 2018-05-29 15:04
 */
public abstract class TemplateGenerator {

    private static final String JAVA_DIR = "./src/main/java/";

    private static final String author = ConfigurationUtils.getConfiguration().getAuthor();

    protected static final String ROOT_DIR = ConfigurationUtils.getConfiguration().getRootPath();

    /* BaseEntity package */
    protected static final String BASE_ENTITY_PACKAGE_NAME = ConfigurationUtils.getConfiguration().getBaseEntityPackage();

    /*  BaseEntity Name  */
    protected static final String BASE_ENTITY_SIMPLE_NAME = ConfigurationUtils.getConfiguration().getBaseEntityName();

    protected static final String BASE_ENTITY_OUTPUTP_PATH = getOutputPath(ROOT_DIR, BASE_ENTITY_PACKAGE_NAME);

    protected static final String ENTITY_PACKAGE = ConfigurationUtils.getConfiguration().getEntityPackage();

    protected static final String ENTITY_OUTPUTPPATH = getOutputPath(ROOT_DIR, ENTITY_PACKAGE);

    /* *************** Repository ******************* */
    protected static final String REPOSITORY_PACKAGE = ConfigurationUtils.getConfiguration().getRepositoryPackage();

    protected static final String REPOSITORY_SUFFIX = ConfigurationUtils.getConfiguration().getRepositoryClassnameSuffix();

    protected static final String REPOSITORY_OUTPUTPPATH = getOutputPath(ROOT_DIR, REPOSITORY_PACKAGE);

    /* *************** Service ******************* */
    protected static final String SERVICE_PACKAGE = ConfigurationUtils.getConfiguration().getServicePackage();

    protected static final String SERVICE_SUFFIX = ConfigurationUtils.getConfiguration().getServiceClassnameSuffix();

    protected static final String SERVICE_OUTPUTPPATH = getOutputPath(ROOT_DIR, SERVICE_PACKAGE);


    /* *************** Controller ******************* */
    protected static final String CONTROLLER_PACKAGE = ConfigurationUtils.getConfiguration().getControllerPackage();

    protected static final String CONTROLLER_SUFFIX = ConfigurationUtils.getConfiguration().getControllerClassnameSuffix();

    protected static final String CONTROLLER_OUTPUTPPATH = getOutputPath(ROOT_DIR, CONTROLLER_PACKAGE);


    public static void generate(List<Table> tables) {
        entityBaseTemplateToQueue(tables);
        entityTemplateToQueue(tables);
        repositoryTemplateToQueue(tables);
        serviceTemplateToQueue(tables);
        controllerTemplateToQueue(tables);
        execute();
    }

    private static void entityBaseTemplateToQueue(List<Table> tables) {
        new EntityBaseTemplate(buildOutputFile(BASE_ENTITY_OUTPUTP_PATH, BASE_ENTITY_SIMPLE_NAME), BASE_ENTITY_PACKAGE_NAME, BASE_ENTITY_SIMPLE_NAME, null, author)
                .parseAndaddFileQueue();
    }

    private static void entityTemplateToQueue(List<Table> tables) {
        String baseEntityClassName = BASE_ENTITY_PACKAGE_NAME + "." + BASE_ENTITY_SIMPLE_NAME;
        tables.forEach(table -> new EntityTemplate(table, buildOutputFile(ENTITY_OUTPUTPPATH, table.getClassName()),
                ENTITY_PACKAGE, baseEntityClassName, author).parseAndaddFileQueue());
    }

    private static void repositoryTemplateToQueue(List<Table> tables) {
        String baseEntityClassName = BASE_ENTITY_PACKAGE_NAME + "." + BASE_ENTITY_SIMPLE_NAME;
        tables.forEach(table -> new RepositoryTemplate(buildOutputFile(REPOSITORY_OUTPUTPPATH, table.getClassName() + REPOSITORY_SUFFIX),
                REPOSITORY_PACKAGE, table.getClassName() + REPOSITORY_SUFFIX, ENTITY_PACKAGE + "." + table.getClassName(), baseEntityClassName, table.getComment(), author)
                .parseAndaddFileQueue());
    }

    private static void serviceTemplateToQueue(List<Table> tables) {
        String baseEntityClassName = BASE_ENTITY_PACKAGE_NAME + "." + BASE_ENTITY_SIMPLE_NAME;
        tables.forEach(table -> new ServiceTemplate(buildOutputFile(SERVICE_OUTPUTPPATH, table.getClassName() + SERVICE_SUFFIX),
                SERVICE_PACKAGE, table.getClassName() + SERVICE_SUFFIX, ENTITY_PACKAGE + "." + table.getClassName(), baseEntityClassName, table.getComment(), author)
                .parseAndaddFileQueue());
    }

    private static void controllerTemplateToQueue(List<Table> tables) {
        String baseEntityClassName = BASE_ENTITY_PACKAGE_NAME + "." + BASE_ENTITY_SIMPLE_NAME;
        tables.forEach(table -> new ControllerTemplate(buildOutputFile(CONTROLLER_OUTPUTPPATH, table.getClassName() + CONTROLLER_SUFFIX),
                CONTROLLER_PACKAGE, table.getClassName() + CONTROLLER_SUFFIX, SERVICE_PACKAGE + "." + table.getClassName() + SERVICE_SUFFIX, baseEntityClassName, table.getComment(), author)
                .parseAndaddFileQueue());
    }

    private static void execute() {
        while (FileQueue.hasNext()) {
            FileQueue.Entry entry = FileQueue.pop();
            FileAssistant.write(entry.getContent(), entry.getFile());
            print(entry);
        }
    }

    private static void print(FileQueue.Entry entry) {
        String path = entry.getFile().getAbsolutePath();
        path = path.replace("\\", "/");
        while (path.indexOf("/..") != -1) {
            int index = path.indexOf("../");
            String lpath = path.substring(0, index - 1);
            String rpath = path.substring(index + 2);
            path = lpath.substring(0, lpath.lastIndexOf("/")) + rpath;
        }
        path = path.replace("/.", "");
        System.out.println(String.format("%s %s %s", "[C]", path, "\n"));
    }

    private static File buildOutputFile(String outputDir, String fileName) {
        return buildOutputFile(outputDir, fileName, "java");
    }

    private static File buildOutputFile(String outputDir, String fileName, String ext) {
        fileName += "." + ext;
        return new File(outputDir, fileName);
    }

//
//    private static String get(String suffix) {
//        return ConfigurationUtils.getConfiguration().get(suffix);
//    }

    private static String getOutputPath(String rootDir, String suffix) {
        String outputDir = JAVA_DIR + suffix.replace(".", "/");
        outputDir = rootDir == null ? outputDir : rootDir + outputDir;
        File file = new File(outputDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }
}
