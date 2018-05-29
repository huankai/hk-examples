package com.hk.config;

import lombok.Data;

import java.util.List;

/**
 * @author: huangkai
 * @date 2018-05-29 11:50
 */
@Data
public class Configuration {

    /**
     * JDBC URL
     */
    private String jdbcUrl;

    /**
     * JDBC password
     */

    private String password;

    /**
     * JDBC username
     */
    private String username;

    /**
     * JDBC DRIVER NAME
     */
    private String driverName;

    /**
     * <p>
     * <p>
     * 使用指定模板的前缀
     * </p>
     * <p>
     * <p>
     * classpath下的目录名，如 one
     * </p>
     */
    private String templateTypePathPrefix;

    /**
     * 是否使用 lombok自动生成get | set 方法
     */
    private boolean useLombok = true;

    /**
     * 是否添加 @Column注解，如果使用驼峰全命名，可以省略此注解.
     */
    private boolean writeColumnAnnotation = false;

    /**
     * 注释中的作者名
     */
    private String author;

    /**
     * 根目录
     */
    private String rootPath = "/";

    /**
     * BaseEntity的包名
     */
    private String baseEntityPackage;

    /**
     * BaseEntity 类名
     */
    private String baseEntityName;

    /* **********************************  */

    /**
     * entity 包名
     */
    private String entityPackage;

    /**
     * 实体要忽略的字段，直接从 BaseEntity继承
     */
    private List<String> entityIngoreFields;

     /* *****************Repository*****************  */

    /**
     * Dao包名
     */
    private String repositoryPackage;

    /**
     * Dao 后缀，由表名的驼峰式加此后缀组成Repository名
     */
    private String repositoryClassnameSuffix = "Repository";

    /**
     * 自定义Repository 包的后缀
     */
    private String customRepositoryPackageSuffix = "custom";

    /**
     * 自定义Repository 类名前缀
     */
    private String customRepositoryClassnamePrefix = "Custom";

    /**
     * 自定义Repository 实现包的后缀
     */
    private String customRepositoryImplPackageSuffix = "impl";

    /**
     * 自定义Repository 实现类名后缀
     */
    private String customRepositoryImplClassnameSuffix = "Impl";


    /* ***************** Service *****************  */

    /**
     * Service包名
     */
    private String servicePackage;

    /**
     * Service 后缀，由表名的驼峰式加此后缀组成Service名
     */
    private String serviceClassnameSuffix = "Service";

    /**
     * ServiceImpl包名
     */
    private String serviceImplPackage;

    /**
     * ServiceImpl 后缀，由表名的驼峰式加此后缀组成Service名
     */
    private String serviceImplClassnameSuffix = "Impl";


    /* ***************** Controller *****************  */

    /**
     * controller 包名
     */
    private String controllerPackage;

    /**
     * Service 后缀，由表名的驼峰式加此后缀组成Service名
     */
    private String controllerClassnameSuffix = "Controller";

    /**
     * 要忽略的表名
     */
    private List<String> ingoreTables;

    /**
     * <p>
     * 指定的表名，如果指定了，只生成指定的表名
     * </p>
     */
    private List<String> includeTables;


}
