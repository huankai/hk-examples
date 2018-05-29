package com.hk.core;

import com.hk.commons.util.CollectionUtils;
import com.hk.template.AbstractTemplate;
import com.hk.template.one.*;
import com.hk.util.FileQueue;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Set;

/**
 * @author: huangkai
 * @date 2018-05-29 14:42
 */
public class TemplateBuilder {

    @Getter
    @Setter
    private EntityBaseTemplate entityBaseTemplate;

    @Getter
    @Setter
    private Set<EntityTemplate> entityTemplates;

    @Setter
    @Getter
    private Set<RepositoryTemplate> repositoryTemplates;

    @Setter
    @Getter
    private Set<ServiceTemplate> serviceTemplates;

    @Setter
    @Getter
    private Set<ControllerTemplate> controllerTemplates;

    public void buildControllerTemplates() {
        if (CollectionUtils.isNotEmpty(controllerTemplates)) {
            controllerTemplates.forEach(item -> parseAndaddFileQueue(item, item.forceCover()));
        }
    }

    public void buildServiceTemplates() {
        if (CollectionUtils.isNotEmpty(serviceTemplates)) {
            serviceTemplates.forEach(item -> parseAndaddFileQueue(item, item.forceCover()));
        }
    }


    public void buildRepositoryTemplates() {
        if (CollectionUtils.isNotEmpty(repositoryTemplates)) {
            repositoryTemplates.forEach(item -> parseAndaddFileQueue(item, item.forceCover()));
        }
    }

    public void buildBaseTemplate() {
        parseAndaddFileQueue(entityBaseTemplate, entityBaseTemplate.forceCover());
    }

    public void buildEntityTemplate() {
        if (CollectionUtils.isNotEmpty(entityTemplates)) {
            entityTemplates.forEach(item -> parseAndaddFileQueue(item, item.forceCover()));
        }
    }

    private void parseAndaddFileQueue(AbstractTemplate template, boolean cover) {
        File outputFile = template.getOutputFile();
        if (cover || !outputFile.exists()) {
            FileQueue.add(new FileQueue.Entry(outputFile, TemplateEngine.parseTemplate(template)));
        }

    }
}
