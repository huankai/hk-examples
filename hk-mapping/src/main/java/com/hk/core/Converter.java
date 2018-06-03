package com.hk.core;

/**
 * @author huangkai
 * @date 2018-6-3 9:53
 */
public interface Converter<R, T> {

    R convert(T t);
}
