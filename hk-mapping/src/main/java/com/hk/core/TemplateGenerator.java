package com.hk.core;

import com.hk.entity.Table;
import com.hk.template.one.*;
import com.hk.util.FileAssistant;
import com.hk.util.FileQueue;
import com.hk.util.PropertyUtils;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: huangkai
 * @date 2018-05-29 15:04
 */
public abstract class TemplateGenerator {

    private static final String JAVA_DIR = "./src/main/java/";

    private static final String author = PropertyUtils.get("author");

    protected static final String ROOT_DIR = get("root.path");

    /**
     * com.hk.core
     */
    protected static final String BASE_ENTITY_PACKAGE_NAME = get("base.entity.package");

    /**
     * BaseEntity
     */
    protected static final String BASE_ENTITY_SIMPLE_NAME = get("base.entity.name");


    protected static final String BASE_ENTITY_OUTPUTP_PATH = getOutputPath(ROOT_DIR, BASE_ENTITY_PACKAGE_NAME);

    protected static final String ENTITY_PACKAGE = get("entity.package");

    protected static final String ENTITY_OUTPUTPPATH = getOutputPath(ROOT_DIR, ENTITY_PACKAGE);

    /* *************** Repository ******************* */
    protected static final String REPOSITORY_PACKAGE = get("repository.package");

    protected static final String REPOSITORY_SUFFIX = get("repository.classname.suffix");

    protected static final String REPOSITORY_OUTPUTPPATH = getOutputPath(ROOT_DIR, REPOSITORY_PACKAGE);

    /* *************** Service ******************* */
    protected static final String SERVICE_PACKAGE = get("service.package");

    protected static final String SERVICE_SUFFIX = get("service.classname.suffix");

    protected static final String SERVICE_OUTPUTPPATH = getOutputPath(ROOT_DIR, SERVICE_PACKAGE);


    /* *************** Controller ******************* */
    protected static final String CONTROLLER_PACKAGE = get("controller.package");

    protected static final String CONTROLLER_SUFFIX = get("controller.classname.suffix");

    protected static final String CONTROLLER_OUTPUTPPATH = getOutputPath(ROOT_DIR, CONTROLLER_PACKAGE);


    public static void generate(List<Table> tables) {
        TemplateBuilder templateBuilder = new TemplateBuilder();
        templateBuilder.setEntityBaseTemplate(getEntityBaseTemplate(tables));
        templateBuilder.buildBaseTemplate();

        templateBuilder.setEntityTemplates(getEntityTemplate(tables));
        templateBuilder.buildEntityTemplate();

        templateBuilder.setRepositoryTemplates(getRepositoryTemplates(tables));
        templateBuilder.buildRepositoryTemplates();

        templateBuilder.setServiceTemplates(getServiceTemplates(tables));
        templateBuilder.buildServiceTemplates();

        templateBuilder.setControllerTemplates(getControllerTemplates(tables));
        templateBuilder.buildControllerTemplates();

        execute();
    }

    private static Set<ControllerTemplate> getControllerTemplates(List<Table> tables) {
        String baseEntityClassName = BASE_ENTITY_PACKAGE_NAME + "." + BASE_ENTITY_SIMPLE_NAME;
        Set<ControllerTemplate> sets = new HashSet<>();
        tables.forEach(table -> sets.add(new ControllerTemplate(buildOutputFile(CONTROLLER_OUTPUTPPATH, table.getClassName() + CONTROLLER_SUFFIX),
                CONTROLLER_PACKAGE, table.getClassName() + CONTROLLER_SUFFIX, SERVICE_PACKAGE + "." + table.getClassName() + SERVICE_SUFFIX, baseEntityClassName, table.getComment(), author)));
        return sets;
    }

    private static Set<ServiceTemplate> getServiceTemplates(List<Table> tables) {
        String baseEntityClassName = BASE_ENTITY_PACKAGE_NAME + "." + BASE_ENTITY_SIMPLE_NAME;
        Set<ServiceTemplate> sets = new HashSet<>();
        tables.forEach(table -> sets.add(new ServiceTemplate(buildOutputFile(SERVICE_OUTPUTPPATH, table.getClassName() + SERVICE_SUFFIX),
                SERVICE_PACKAGE, table.getClassName() + SERVICE_SUFFIX, SERVICE_PACKAGE + "." + table.getClassName(), baseEntityClassName, table.getComment(), author)));
        return sets;
    }

    private static Set<RepositoryTemplate> getRepositoryTemplates(List<Table> tables) {
        String baseEntityClassName = BASE_ENTITY_PACKAGE_NAME + "." + BASE_ENTITY_SIMPLE_NAME;
        Set<RepositoryTemplate> sets = new HashSet<>();
        tables.forEach(table -> sets.add(new RepositoryTemplate(buildOutputFile(REPOSITORY_OUTPUTPPATH, table.getClassName() + REPOSITORY_SUFFIX),
                REPOSITORY_PACKAGE, table.getClassName() + REPOSITORY_SUFFIX, ENTITY_PACKAGE + "." + table.getClassName(), baseEntityClassName, table.getComment(), author)));
        return sets;
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
        String info = String.format("%s %s %s", "[C]", path, "\n");
        System.out.println(info);
    }

    private static Set<EntityTemplate> getEntityTemplate(List<Table> tables) {
        String baseEntityClassName = BASE_ENTITY_PACKAGE_NAME + "." + BASE_ENTITY_SIMPLE_NAME;
        Set<EntityTemplate> sets = new HashSet<>();
        tables.forEach(table -> sets.add(new EntityTemplate(table, buildOutputFile(ENTITY_OUTPUTPPATH, table.getClassName()),
                ENTITY_PACKAGE, baseEntityClassName, author)));
        return sets;
    }

    private static EntityBaseTemplate getEntityBaseTemplate(List<Table> tables) {
        return new EntityBaseTemplate(buildOutputFile(BASE_ENTITY_OUTPUTP_PATH, BASE_ENTITY_SIMPLE_NAME), BASE_ENTITY_PACKAGE_NAME, BASE_ENTITY_SIMPLE_NAME, null, author);
    }

    private static File buildOutputFile(String outputDir, String fileName) {
        return buildOutputFile(outputDir, fileName, "java");
    }

    private static File buildOutputFile(String outputDir, String fileName, String ext) {
        fileName += "." + ext;
        return new File(outputDir, fileName);
    }


    private static String get(String suffix) {
        return PropertyUtils.get(suffix);
    }

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
