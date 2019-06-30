package com.hk.util;

import com.hk.commons.util.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author kevin
 * @date 2019-6-29 15:30
 * @see https://hooray.github.io/posts/8b2bd6f8/
 */
public class Test2 {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class NameValue implements Comparable<NameValue> {

        private String name;

        private List<String> values;

        @Override
        public int compareTo(NameValue o) {
            return name.compareTo(o.name);
        }
    }

//    public static void main(String[] args) {
//        String text = "颜色:蓝色男款,白色男款;尺码:38,39,40;类别:a,b";
//        String[] nameValues = StringUtils.tokenizeToStringArray(text, ";");
//        List<NameValue> lists = new ArrayList<>(nameValues.length);
//        for (String nameValue : nameValues) {
//            String[] keyValue = StringUtils.tokenizeToStringArray(nameValue, ":");
//            if (keyValue.length == 2) {
//                String name = keyValue[0];
//                String[] values = StringUtils.splitByComma(keyValue[1]);
//                lists.add(new NameValue(name, ArrayUtils.asArrayList(values)));
//            }
//        }
//        Combinationable<NameValue> combinationable = new ComparableCombination<>(lists);
//        List<List<NameValue>> combinations = combinationable.findCombinations();
//        System.out.println(JsonUtils.serialize(combinations, true));
//        System.out.println(combinations.size());
//    }




    /*  ***************************** */

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class NameValue1 {

        private String name;

        private String value;
    }


//    public static void main(String[] args) {
//        String text = "颜色:蓝色男款,白色男款;尺码:38,39,40;类别:a,b";
//        String[] nameValues = StringUtils.tokenizeToStringArray(text, ";");
//        List<NameValue1> lists = new ArrayList<>();
//        for (String nameValue : nameValues) {
//            String[] keyValue = StringUtils.tokenizeToStringArray(nameValue, ":");
//            if (keyValue.length == 2) {
//                String name = keyValue[0];
//                String[] values = StringUtils.splitByComma(keyValue[1]);
//                for (String value : values) {
//                    lists.add(new NameValue1(name, value));
//                }
//            }
//        }
//        Map<String, List<NameValue1>> groupMap = lists.stream().collect(Collectors.groupingBy(NameValue1::getName));
//        System.out.println(JsonUtils.serialize(lists, true));
//        System.out.println("============");
//        System.out.println(JsonUtils.serialize(groupMap, true));
//    }


    public static void main(String[] args) {
        String text = "颜色:蓝色男款,白色男款;尺码:38,39,40;类别:a,b";
        String[] nameValues = StringUtils.tokenizeToStringArray(text, ";");
        List<NameValue> lists = new ArrayList<>(nameValues.length);
        List<Position> positions = new ArrayList<>();
        int maxSize = 1;
        for (int index = 0; index < nameValues.length; index++) {
            String nameValue = nameValues[index];
            String[] keyValue = StringUtils.tokenizeToStringArray(nameValue, ":");
            if (keyValue.length == 2) {
                String name = keyValue[0];
                String[] values = StringUtils.splitByComma(keyValue[1]);
                if (ArrayUtils.isNotEmpty(values)) {
                    maxSize = maxSize * values.length;
                    if (index != 0) { // 第一个元素不放进去
                        positions.add(new Position(index, values.length));
                    }
                    lists.add(new NameValue(name, ArrayUtils.asArrayList(values)));
                }
            }
        }
        if (CollectionUtils.size(lists) <= 1) {
            System.out.println("---------over---------");
            return;
        }
        NameValue firstNameValue = lists.get(0);
        List<List<TextValueItem>> result = new ArrayList<>();
        int forSize = maxSize / firstNameValue.values.size(); //每个一级元素需要循环的次数
        for (String firstValue : firstNameValue.values) {
            for (int i = 0; i < forSize; i++) {
                List<TextValueItem> list = new ArrayList<>();
                list.add(new TextValueItem(firstNameValue.name, firstValue));
                CollectionUtils.addAll(list, getList(i, positions, lists));
                result.add(list);
            }
        }
        System.out.println(JsonUtils.serialize(result, true));

    }

    private static List<TextValueItem> getList(int index, List<Position> positions, List<NameValue> nameValues) {
        List<TextValueItem> result = new ArrayList<>();
        int[] posit = new int[]{positions.size()};
        for (Position position : positions) {

        }
        for (int i = 0; i < posit.length; i++) {
            NameValue nameValue = nameValues.get(i);
            result.add(new TextValueItem(nameValue.name, nameValue.values.get(posit[i])));
        }
        return result;
    }
//
//    private static List<TextValueItem> text(String name, String value, List<TextValueItem> list) {
//        list.add(new TextValueItem(name, value));
//        return list;
//    }
//
//    /**
//     * @param lists
//     * @return
//     */
//
//    private static List<List<TextValueItem>> build(final List<NameValue> lists) {
//        long size = lists.size();
//        List<List<TextValueItem>> result = new ArrayList<>();
//        for (int i = 0; i < size; i++) {
//            NameValue nameValue = lists.get(i);
//            for (String value : nameValue.values) {
//
//                for (int j = i + 1; j < size; j++) {
//                    NameValue nameValue1 = lists.get(j);
//                    for (String value1 : nameValue1.values) {
//
//                        for (int k = j + 1; k < size; k++) {
//                            NameValue nameValue2 = lists.get(k);
//                            for (String value2 : nameValue2.values) {
//                                result.add(ArrayUtils.asArrayList(new TextValueItem(nameValue.name, value),
//                                        new TextValueItem(nameValue1.name, value1), new TextValueItem(nameValue2.name, value2)));
//                            }
//                        }
//
//                    }
//                }
//
//
//            }
//        }
//        return result;
//    }
//

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Position {

        private int index;

        private int maxPosition;
    }


    private static List<List<TextValueItem>> build2(final List<NameValue> lists) {
        int size = lists.size();
        List<List<TextValueItem>> result = new ArrayList<>();
        NameValue firstNameValue = lists.get(0);
        int lastIndex = size - 1;
        NameValue lastNameValue = lists.get(lastIndex);
        List<Integer> indexList = IntStream.range(1, lastIndex).boxed().collect(Collectors.toList());
        for (String value : firstNameValue.values) {
            for (String lasValue : lastNameValue.values) {
                List<TextValueItem> textValueItems = ArrayUtils.asArrayList(new TextValueItem(firstNameValue.name, value));
                for (Integer centerIndex : indexList) {
                    NameValue nameValue = lists.get(centerIndex);
                    textValueItems.add(new TextValueItem(nameValue.name, nameValue.values.get(0)));
                }
                textValueItems.add(new TextValueItem(lastNameValue.name, lasValue));
                result.add(textValueItems);
            }
        }
        return result;
    }
}
