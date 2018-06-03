package com.hk.core;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author huangkai
 * @date 2018-6-3 9:55
 */
public abstract class AbstractColumnConverter implements ColumnConverter<String, String> {

    protected Set<String> importPcakages = Sets.newHashSet();


    @Override
    public Set<String> getImportPackages() {
        return importPcakages;
    }

    protected void addPackage(String clasName) {
        importPcakages.add(clasName);
    }
}
