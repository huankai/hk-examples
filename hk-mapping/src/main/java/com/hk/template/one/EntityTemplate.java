package com.hk.template.one;

import com.hk.entity.Table;
import com.hk.template.AbstractTemplate;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: huangkai
 * @date 2018-05-29 13:19
 */
public class EntityTemplate extends AbstractTemplate {

    @Getter
    @Setter
    private Set<String> excludeColumn = new HashSet<>();

    @Getter
    @Setter
    private Table table;

    /**
     * Entity
     *
     * @param outputFile
     * @param packageName
     * @param baseEntityClassName
     * @param author
     */
    public EntityTemplate(Table table, File outputFile, String packageName, String baseEntityClassName, String author) {
        this.table = table;
        setOutputFile(outputFile);
        setClassName(table.getClassName());
        setPackageName(packageName);
        setBaseEntityClassName(baseEntityClassName);
        setComment(table.getComment());
        setAuthor(author);
    }

}
