package com.hk.util;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: huangkai
 * @date 2018-05-29 14:07
 */
public class ImportVar {

    private Set<String> vars = new HashSet<>();

    public void put(String value){
        vars.add(value);
    }

    public Set<String> getVars() {
        return vars;
    }
}
