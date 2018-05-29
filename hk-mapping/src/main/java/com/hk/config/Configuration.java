package com.hk.config;

import com.hk.commons.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: huangkai
 * @date 2018-05-29 11:50
 */
public class Configuration {

    private static final String CUSTOM_REPOSITORY_CLASSNAME_PREFIX = "Custom";

    /**
     * 根目录
     */
    @Setter
    @Getter
    private String rootPath = "/";

    /**
     * entity 包名
     */
    @Setter
    private String entityPackage;

    public String getEntityPackage() {
        if (!StringUtils.startsWith(entityPackage, "/")) {
            entityPackage = "/" + entityPackage;
        }
        return rootPath + entityPackage;
    }

    /**
     * repository 包名
     */
    @Setter
    private String repositoryPackage;

    public String getRepositoryPackage() {
        if (!StringUtils.startsWith(repositoryPackage, "/")) {
            repositoryPackage = "/" + repositoryPackage;
        }
        return rootPath + repositoryPackage;
    }

    /**
     * 自定义的Custom 包名
     */
    @Setter
    @Getter
    private String customRepositoryPackageSuffix = "custom";

    public String getCustomRepositoryPackage() {
        return getRepositoryPackage() + "/" + customRepositoryPackageSuffix;
    }

    /**
     *
     */
    @Setter
    @Getter
    private String repositoryImplPackageSuffix = "impl";

    public String getRepositoryImplPackage() {
        return getRepositoryPackage() + "/" + repositoryImplPackageSuffix;
    }

    /**
     *
     */
    @Setter
    @Getter
    private String repositoryImplClassNameSuffix = "Impl";

    /**
     * Service 包名
     */
    @Setter
    @Getter
    private String servicePackage;

    /**
     * ServiceImpl 包名
     */
    @Setter
    @Getter
    private String serviceImplPackageSuffix = "impl";

    public String getServiceImplPackage() {
        return servicePackage + "/" + serviceImplPackageSuffix;
    }

    /**
     *
     */
    @Setter
    @Getter
    private String seviceImplClassNameSuffix = "Impl";

    /**
     * Controller包名
     */
    @Setter
    @Getter
    private String controllerPackage;

    /**
     * 指定需要忽略的表名
     */
    @Setter
    @Getter
    private String ingoreTables;

    /**
     * <p>
     * 如果此参数有设置，只会构建这些表，ingoreTables 属性不生效
     * 否则，会生成全部并过滤 ingoreTables 这些表
     * </p>
     */
    @Setter
    @Getter
    private String includeTables;

    /**
     * 是否使用 lombok自动生成get | set 方法
     */
    @Setter
    @Getter
    private boolean useLombok;

    /**
     * 是否添加 @Column注解，如果使用驼峰全命名，可以省略此注解.
     */
    @Setter
    @Getter
    private boolean writeColumnAnnotation = false;


}
