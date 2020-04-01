package com.hk.core;

import com.hk.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import java.beans.PropertyDescriptor;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author kevin
 * @date 2018-05-29 14:43
 */
@Slf4j
public class TemplateEngine {

    private static final String CLASS_PATH = "classpath";

    private static final String CLASSPATH_RESOURCE_LOADER = "classpath.resource.loader.class";

//    public enum ResourceLoaderEnum {
//
//        CLASSPATH,
//
//        STRING,
//
//        URL,
//
//        FILE,
//
//        JAR
//    }

//    public static String parseTemplate(Template template) {
//        return parseTemplate(template, ResourceLoaderEnum.CLASSPATH);
//    }

    public static String parseTemplate(Template template) {
        try (StringWriter writer = new StringWriter()) {
            VelocityEngine engine = getVelocityEngine();
            engine.mergeTemplate(template.getTemplatePath(), StandardCharsets.UTF_8.name(), buildContext(template), writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Context buildContext(Template template) {
        VelocityContext context = new VelocityContext();
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(template);
        for (PropertyDescriptor descriptor : beanWrapper.getPropertyDescriptors()) {
            context.put(descriptor.getName(), beanWrapper.getPropertyValue(descriptor.getName()));
        }
        return context;
    }

    private static VelocityEngine getVelocityEngine() throws Exception {
        Properties prop = new Properties();
        prop.put(RuntimeConstants.RESOURCE_LOADER, "classpath");
        prop.put(CLASSPATH_RESOURCE_LOADER, ClasspathResourceLoader.class.getName());
        VelocityEngine engine = new VelocityEngine();
        engine.init(prop);
        return engine;
    }

}
