package com.hk.util;

import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: huangkai
 * @date 2018-05-29 14:18
 */
public class TableAssistant {

    /**
     * @return
     */
    public static List<String> ingoreTables() {
        List<String> ingoreTableList = new ArrayList<>();
        String ingoreTables = PropertyUtils.get("ingore.tables");
        String[] ingoreTableArr = StringUtils.splitByComma(ingoreTables);
        if (ArrayUtils.isNotEmpty(ingoreTableArr)) {
            ingoreTableList.addAll(Arrays.asList(ingoreTableArr));
        }
        return ingoreTableList;
    }
}
