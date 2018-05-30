package com.hk.core;

import com.hk.commons.util.Contants;
import com.hk.config.Configuration;
import com.hk.template.Template;
import com.hk.util.ImportVar;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import java.beans.PropertyDescriptor;
import java.io.StringWriter;
import java.util.Properties;

/**
 * @author: huangkai
 * @date 2018-05-29 14:43
 */
public class TemplateEngine {

    private static final String CLASS_PATH = "classpath";

    private static final String CLASSPATH_RESOURCE_LOADER = "classpath.resource.loader.class";

    private Configuration configuration;

    public TemplateEngine(Configuration configuration) {
        this.configuration = configuration;
    }

    public String parseTemplate(Template template) {
        StringWriter writer = new StringWriter();
        String templatePath = getTemplatePath(template);
        try {
            VelocityEngine engine = getVelocityEngine();
            engine.getTemplate(templatePath, Contants.UTF_8).merge(buildContext(template), writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Context buildContext(Template template) {
        VelocityContext context = new VelocityContext();
        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(template);
        for (PropertyDescriptor descriptor : beanWrapper.getPropertyDescriptors()) {
            context.put(descriptor.getName(), beanWrapper.getPropertyValue(descriptor.getName()));
        }
        context.put("imports", ImportVar.getVars());
        context.put("useLombok", configuration.isUseLombok());
        context.put("writeColumnAnnotation", configuration.isWriteColumnAnnotation());
        return context;
    }

    private VelocityEngine getVelocityEngine() throws Exception {
        Properties prop = new Properties();
        prop.put(RuntimeConstants.RESOURCE_LOADER, CLASS_PATH);
        prop.put(CLASSPATH_RESOURCE_LOADER, ClasspathResourceLoader.class.getName());
        VelocityEngine engine = new VelocityEngine();
        engine.init(prop);
        return engine;
    }

    private String getTemplatePath(Template template) {
        String templateClass = template.getClass().getSimpleName();
        String templateName = templateClass.substring(0, templateClass.indexOf("Template"));
        return String.format("%s/%s.vm",configuration.getTemplateTypePathPrefix(),templateName);
    }
}
