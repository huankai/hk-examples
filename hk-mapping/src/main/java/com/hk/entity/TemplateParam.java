package com.hk.entity;

import com.hk.commons.util.StringUtils;
import lombok.Data;

import java.util.Set;

/**
 * @author huangkai
 * @date 2018-5-30 22:20
 */
@Data
public class TemplateParam {

    private String author;

    private String version;

    private String rootPath;

    /**
     * 要忽略的字段
     */
    private Set<String> ingoreColumns;

    public String getRootPath() {
        if (StringUtils.isEmpty(rootPath)) {
            rootPath = System.getProperty("user.home");
        }
        if (!StringUtils.endsWith(rootPath, "/")) {
            rootPath += "/";
        }
        return rootPath;
    }

    private String baseEntityClassName;

    public String getBaseEntitySimpleName() {
        return StringUtils.substringAfterLast(baseEntityClassName, ".");
    }

    public String getBaseEntityPackage() {
        return StringUtils.substringBeforeLast(baseEntityClassName, ".");
    }

    /**
     * <p>
     * Entity Package name<br/>
     * example: com.hk.emi.entity
     * </p>
     */
    private String entityPackageName;

    private boolean useLombok = true;

    /**
     * 表名转实体名
     *
     * @param tableName
     * @return
     */
    public String formatEntityNameByTableName(String tableName) {
        return formatEntityNameByEntitySimpleName(StringUtils.lineToBigHump(tableName));
    }

    public String formatEntityNameByEntitySimpleName(String entitySimpleName) {
        return String.format("%s.%s", entityPackageName, entitySimpleName);
    }

    /**
     * <p>
     * repository package name<br/>
     * example : com.hk.emi.repository
     * </p>
     */
    private String repositoryPackageName;

    /**
     * <p>
     * repository class name suffix<br/>
     * example : Repository
     * generate class name is
     * </p>
     */
    private String repositoryClassNameSuffix;

    /**
     * 表名转RepositoryClassName
     *
     * @param tableName
     * @return
     */
    public String formatRepositoryNameByTableName(String tableName) {
        return formatRepositoryNameByEntitySimpleName(StringUtils.lineToBigHump(tableName));
    }

    public String formatRepositoryNameByEntitySimpleName(String entitySimpleName) {
        return String.format("%s.%s%s", repositoryPackageName, entitySimpleName, repositoryClassNameSuffix);
    }

    private String customRepositoryPackageSuffix;

    /**
     * 前缀
     */
    private String customRepositoryClassNamePrefix;

    public String formatCustomRepositoryPackageName(){
        return repositoryPackageName + "." + customRepositoryPackageSuffix;
    }

    public String formatCustomRepositorySimpleClassNameByEntitySimpleName(String entitySimpleName) {
        return String.format("%s%s%s",customRepositoryClassNamePrefix, entitySimpleName, repositoryClassNameSuffix);
    }

    /**
     * 表名转CustomRepositoryClassName
     *
     * @param tableName
     * @return
     */
    public String formatCustomRepositoryNameByTableName(String tableName) {
        return formatCustomRepositoryNameByEntitySimpleName(StringUtils.lineToBigHump(tableName));
    }

    public String formatCustomRepositoryNameByEntitySimpleName(String entitySimpleName) {
        return String.format("%s.%s.%s%s%s", repositoryPackageName,
                customRepositoryPackageSuffix, customRepositoryClassNamePrefix, entitySimpleName, repositoryClassNameSuffix);
    }

    private String customRepositoryImplPackageSuffix;

    /**
     * 后缀
     */
    private String customRepositoryImplClassNameSuffix;

    /**
     * 表名转CustomRepositoryImplClassName
     *
     * @param tableName
     * @return
     */
    public String formatCustomRepositoryImplNameByTableName(String tableName) {
        return formatCustomRepositoryImplNameByEntitySimpleName(StringUtils.lineToBigHump(tableName));
    }

    public String formatCustomRepositoryImplNameByEntitySimpleName(String entitySimpleName) {
        return String.format("%s.%s.%s%s%s", repositoryPackageName,
                customRepositoryImplPackageSuffix, entitySimpleName, repositoryClassNameSuffix, customRepositoryImplClassNameSuffix);
    }

    private String servicePackage;

    private String serviceClassNameSuffix;

    /**
     * 表名转ServiceClassName
     *
     * @param tableName
     * @return
     */
    public String formatServiceNameByTableName(String tableName) {
        return formatServiceNameByEntitySimpleName(StringUtils.lineToBigHump(tableName));
    }

    public String formatServiceNameByEntitySimpleName(String entitySimpleName) {
        return String.format("%s.%s%s", servicePackage,
                entitySimpleName, serviceClassNameSuffix);
    }

    private String serviceImplPackageSuffix;

    private String serviceImplClassNameSuffix;

    /**
     * 表名转ServiceClassName
     *
     * @param tableName
     * @return
     */
    public String formatServiceImplNameByTableName(String tableName) {
        return formatServiceImplNameByEntitySimpleName(StringUtils.lineToBigHump(tableName));
    }

    public String formatServiceImplNameByEntitySimpleName(String entitySimpleName) {
        return String.format("%s.%s.%s%s", servicePackage, serviceImplPackageSuffix,
                entitySimpleName, serviceImplClassNameSuffix);
    }

    private String controllerPackage;

    private String controllerClassNameSuffix;

    /**
     * 表名转ControllerClassName
     *
     * @param tableName
     * @return
     */
    public String formatControllerNameByTableName(String tableName) {
        return formatControllerNameByEntitySimpleName(StringUtils.lineToBigHump(tableName));
    }

    public String formatControllerNameByEntitySimpleName(String entitySimpleName) {
        return String.format("%s.%s%s", controllerPackage, entitySimpleName,
                controllerClassNameSuffix);
    }


//
//    public CustomRepositoryTemplate toCustomRepositoryTemplate(Table table, String templatePath) {
//        File file = new File(getOutputFile(String.format("%s.%s", repositoryPackageName, customRepositoryPackageSuffix)),
//                formatJavaFileName(String.format("%s%s", table.getClassName(), repositoryClassNameSuffix)));
//        return new SimpleCustomReopsitoryTemplate(file, table.getClassName(), templatePath, table.getComment(), author, version);
//    }
//
//    public RepositoryTemplate toRepositoryTemplate(String templatePath) {
//        return null;
//
//    }


}
