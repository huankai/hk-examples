package com.hk.core;

import com.hk.commons.util.BooleanUtils;
import com.hk.commons.util.StringUtils;
import com.hk.config.Configuration;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author: huangkai
 * @date 2018-05-30 15:29
 */
public class DefaultConfigurationStorage implements ConfigurationStorage {

    private static final String CONFIG_PROPERTIES = "config.properties";

    private Configuration configuration;

    private static Configuration DEFAULT_CONFIGURATION;

    static {
        init();
    }

    public DefaultConfigurationStorage() {
        this.configuration = DEFAULT_CONFIGURATION;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void clear() {
    }

    @Override
    public List<String> getAllConfigurationKeyList() {
        return null;
    }

    private static void init() {
        Properties properties = new Properties();
        try (InputStream in = ClassLoader.getSystemResourceAsStream(CONFIG_PROPERTIES)) {
            if (null != in) {
                properties.load(in);
                DEFAULT_CONFIGURATION = new Configuration();
                DEFAULT_CONFIGURATION.setJdbcUrl(properties.getProperty("jdbc.url"));
                DEFAULT_CONFIGURATION.setUsername(properties.getProperty("jdbc.username"));
                DEFAULT_CONFIGURATION.setPassword(properties.getProperty("jdbc.password"));
                DEFAULT_CONFIGURATION.setDriverName(properties.getProperty("jdbc.driver"));

                DEFAULT_CONFIGURATION.setTemplateTypePathPrefix(properties.getProperty("template.type"));
                DEFAULT_CONFIGURATION.setUseLombok(BooleanUtils.toBoolean(properties.getProperty("useLombok")));
                DEFAULT_CONFIGURATION.setWriteColumnAnnotation(BooleanUtils.toBoolean(properties.getProperty("writeColumnAnnotation", "true")));

                DEFAULT_CONFIGURATION.setAuthor(properties.getProperty("author", System.getProperty("user.name")));
                DEFAULT_CONFIGURATION.setRootPath(properties.getProperty("root.path", System.getProperty("user.home")));
                DEFAULT_CONFIGURATION.setBaseEntityPackage(properties.getProperty("base.entity.package"));
                DEFAULT_CONFIGURATION.setBaseEntityName(properties.getProperty("base.entity.name"));
                DEFAULT_CONFIGURATION.setEntityPackage(properties.getProperty("entity.package"));
                String ingoreColumns = properties.getProperty("entity.ingore.columns");
                if (StringUtils.isNotEmpty(ingoreColumns)) {
                    DEFAULT_CONFIGURATION.setEntityIngoreFields(Arrays.asList(StringUtils.splitByComma(ingoreColumns)));
                }

                DEFAULT_CONFIGURATION.setRepositoryPackage(properties.getProperty("repository.package"));
                DEFAULT_CONFIGURATION.setRepositoryClassnameSuffix(properties.getProperty("repository.classname.suffix"));
                DEFAULT_CONFIGURATION.setCustomRepositoryPackageSuffix(properties.getProperty("custom.repository.package.suffix"));
                DEFAULT_CONFIGURATION.setCustomRepositoryClassnamePrefix(properties.getProperty("custom.repository.classname.prefix"));
                DEFAULT_CONFIGURATION.setCustomRepositoryImplPackageSuffix(properties.getProperty("custom.repository.impl.package.suffix"));
                DEFAULT_CONFIGURATION.setCustomRepositoryImplClassnameSuffix(properties.getProperty("custom.repository.impl.classname.suffix"));

                DEFAULT_CONFIGURATION.setServicePackage(properties.getProperty("service.package"));
                DEFAULT_CONFIGURATION.setServiceClassnameSuffix(properties.getProperty("service.classname.suffix"));
                DEFAULT_CONFIGURATION.setServiceImplPackage(properties.getProperty("service.impl.package"));
                DEFAULT_CONFIGURATION.setServiceImplClassnameSuffix(properties.getProperty("service.impl.classname.suffix"));

                DEFAULT_CONFIGURATION.setControllerPackage(properties.getProperty("controller.package"));
                DEFAULT_CONFIGURATION.setControllerClassnameSuffix(properties.getProperty("controller.classname.suffix"));

                String ingoreTables = properties.getProperty("ingore.tables");
                if (StringUtils.isNotEmpty(ingoreTables)) {
                    DEFAULT_CONFIGURATION.setIngoreTables(Arrays.asList(StringUtils.splitByComma(ingoreTables)));
                }

                String includeTables = properties.getProperty("include.tables");
                if (StringUtils.isNotEmpty(includeTables)) {
                    DEFAULT_CONFIGURATION.setIncludeTables(Arrays.asList(StringUtils.splitByComma(includeTables)));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
