package com.hk.util;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: huangkai
 * @date 2018-05-29 14:07
 */
public class ImportVar {

    private static Set<String> vars = new HashSet<>();

    public static void put(String value){
        vars.add(value);
    }

    public static Set<String> getVars() {
        return vars;
    }
}
