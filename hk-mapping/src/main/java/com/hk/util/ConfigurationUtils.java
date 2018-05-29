package com.hk.util;

import com.hk.commons.util.BooleanUtils;
import com.hk.commons.util.StringUtils;
import com.hk.config.Configuration;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author: huangkai
 * @date 2018-05-29 11:35
 */
public class ConfigurationUtils {

    private static final String CONFIG_PROPERTIES = "config.properties";

    private static Configuration configuration;

    static {
        init();
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static void setConfiguration(Configuration configuration) {
        ConfigurationUtils.configuration = configuration;
    }

    private static void init() {
        Properties properties = new Properties();
        try (InputStream in = ClassLoader.getSystemResourceAsStream(CONFIG_PROPERTIES)) {
            if (null != in) {
                properties.load(in);
                configuration = new Configuration();
                configuration.setJdbcUrl(properties.getProperty("jdbc.url"));
                configuration.setUsername(properties.getProperty("jdbc.username"));
                configuration.setPassword(properties.getProperty("jdbc.password"));
                configuration.setDriverName(properties.getProperty("jdbc.driver"));

                configuration.setTemplateTypePathPrefix(properties.getProperty("template.type"));
                configuration.setUseLombok(BooleanUtils.toBoolean(properties.getProperty("useLombok")));
                configuration.setWriteColumnAnnotation(BooleanUtils.toBoolean(properties.getProperty("writeColumnAnnotation", "true")));

                configuration.setAuthor(properties.getProperty("author", System.getProperty("user.name")));
                configuration.setRootPath(properties.getProperty("root.path", "/"));
                configuration.setBaseEntityPackage(properties.getProperty("base.entity.package"));
                configuration.setBaseEntityName(properties.getProperty("base.entity.name"));
                configuration.setEntityPackage(properties.getProperty("entity.package"));
                String ingoreColumns = properties.getProperty("entity.ingore.columns");
                if (StringUtils.isNotEmpty(ingoreColumns)) {
                    configuration.setEntityIngoreFields(Arrays.asList(StringUtils.splitByComma(ingoreColumns)));
                }

                configuration.setRepositoryPackage(properties.getProperty("repository.package"));
                configuration.setRepositoryClassnameSuffix(properties.getProperty("repository.classname.suffix"));
                configuration.setCustomRepositoryPackageSuffix(properties.getProperty("custom.repository.package.suffix"));
                configuration.setCustomRepositoryClassnamePrefix(properties.getProperty("custom.repository.classname.prefix"));
                configuration.setCustomRepositoryImplPackageSuffix(properties.getProperty("custom.repository.impl.package.suffix"));
                configuration.setCustomRepositoryImplClassnameSuffix(properties.getProperty("custom.repository.impl.classname.suffix"));

                configuration.setServicePackage(properties.getProperty("service.package"));
                configuration.setServiceClassnameSuffix(properties.getProperty("service.classname.suffix"));
                configuration.setServiceImplPackage(properties.getProperty("service.impl.package"));
                configuration.setServiceImplClassnameSuffix(properties.getProperty("service.impl.classname.suffix"));

                configuration.setControllerPackage(properties.getProperty("controller.package"));
                configuration.setControllerClassnameSuffix(properties.getProperty("controller.classname.suffix"));

                String ingoreTables = properties.getProperty("ingore.tables");
                if (StringUtils.isNotEmpty(ingoreTables)) {
                    configuration.setIngoreTables(Arrays.asList(StringUtils.splitByComma(ingoreTables)));
                }

                String includeTables = properties.getProperty("include.tables");
                if (StringUtils.isNotEmpty(includeTables)) {
                    configuration.setIncludeTables(Arrays.asList(StringUtils.splitByComma(includeTables)));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
