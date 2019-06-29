package com.hk.util;

import com.hk.commons.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @date 2019-6-29 15:30
 */
public class Test2 {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class NameValue {

        private String name;

        private List<String> values;
    }

    public static void main(String[] args) {
        String text = "颜色:蓝色男款,白色男款;尺码:38,39,40;类型:a,b";
        String[] nameValues = StringUtils.tokenizeToStringArray(text, ";");
        List<NameValue> lists = new ArrayList<>(nameValues.length);
        for (String nameValue : nameValues) {
            String[] keyValue = StringUtils.tokenizeToStringArray(nameValue, ":");
            if (keyValue.length == 2) {
                String name = keyValue[0];
                String[] values = StringUtils.splitByComma(keyValue[1]);
                lists.add(new NameValue(name, ArrayUtils.asArrayList(values)));
            }
        }
        System.out.println(JsonUtils.serialize(lists, true));

        System.out.println("----------------------------------");
        long size = CollectionUtils.size(lists);
        if (size <= 1) {
            System.out.println("---------over---------");
        } else {
            List<List<TextValueItem>> result = build(lists);
            System.out.println(JsonUtils.serialize(result, true));
            System.out.println(result.size());
        }
    }

    private static List<List<TextValueItem>> build(final List<NameValue> lists) {
        long size = lists.size();
        List<List<TextValueItem>> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            NameValue nameValue = lists.get(i);
            for (String value : nameValue.values) {
                for (int j = i + 1; j < size; j++) {
                    NameValue nameValue1 = lists.get(j);
                    for (String value1 : nameValue1.values) {
                        for (int k = j + 1; k < size; k++) {
                            NameValue nameValue2 = lists.get(k);
                            for (String value2 : nameValue2.values) {
                                result.add(ArrayUtils.asArrayList(new TextValueItem(nameValue.name, value),
                                        new TextValueItem(nameValue1.name, value1), new TextValueItem(nameValue2.name, value2)));
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
