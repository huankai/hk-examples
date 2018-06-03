package com.hk.core;

import java.util.Set;

/**
 * @author huangkai
 * @date 2018-6-3 9:54
 */
public interface ColumnConverter<R, T> extends Converter<R, T> {

    Set<String> getImportPackages();
}
