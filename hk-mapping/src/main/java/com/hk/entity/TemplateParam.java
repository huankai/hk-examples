package com.hk.entity;

import com.hk.commons.util.StringUtils;
import lombok.Data;

import java.util.Set;

/**
 * @author kevin
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
    private String repositoryClassNameSuffix = "Repository";

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

    public String formatRepositorySimpleNameByEntitySimpleName(String entitySimpleName) {
        return String.format("%s%s", entitySimpleName, repositoryClassNameSuffix);
    }

    private String customRepositoryPackageSuffix = "custom";

    /**
     * 前缀
     */
    private String customRepositoryClassNamePrefix = "Custom";

    public String formatCustomRepositoryPackageName() {
        return repositoryPackageName + "." + customRepositoryPackageSuffix;
    }

    public String formatCustomRepositorySimpleClassNameByEntitySimpleName(String entitySimpleName) {
        return String.format("%s%s%s", customRepositoryClassNamePrefix, entitySimpleName, repositoryClassNameSuffix);
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

    private String customRepositoryImplPackageSuffix = "impl";

    /**
     * 后缀
     */
    private String customRepositoryImplClassNameSuffix = "Impl";

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

    public String formatCustomRepositoryImplPackageName() {
        return repositoryPackageName + "." + customRepositoryImplPackageSuffix;
    }

    public String formatCustomRepositoryImplClassName(String entitySimpleName) {
        return String.format("%s%s%s", entitySimpleName, repositoryClassNameSuffix, customRepositoryImplClassNameSuffix);
    }

    private String servicePackage;

    private String serviceClassNameSuffix = "Service";

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

    public String formatServiceSimpleNameByEntitySimpleName(String entitySimpleName) {
        return String.format("%s%s", entitySimpleName, serviceClassNameSuffix);
    }

    private String serviceImplPackageSuffix = "impl";

    private String serviceImplClassNameSuffix = "ServiceImpl";

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

    public String formatServiceImplSimpleNameByEntitySimpleName(String entitySimpleName) {
        return String.format("%s%s", entitySimpleName, serviceImplClassNameSuffix);
    }

    public String formatServiceImplPackage() {
        return String.format("%s.%s", servicePackage, serviceImplPackageSuffix);
    }

    private String controllerPackage;

    private String controllerClassNameSuffix = "Controller";

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

    public String formatControllerSimpleNameByEntitySimpleName(String entitySimpleName) {
        return String.format("%s%s", entitySimpleName, controllerClassNameSuffix);
    }


    private String baseEntityPath;

    private String entityPath;

    private String repositoryPath;

    private String customRepositoryPath;

    private String customImplResositoryPath;

    private String servicePath;

    private String serviceImplPath;

    private String controllerPath;

}
