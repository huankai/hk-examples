package com.hk.util;

import com.hk.commons.util.SkuUtils;

/**
 * sku
 *
 * @author kevin
 * @date 2019-6-29 15:30
 * @see https://hooray.github.io/posts/8b2bd6f8/
 * @see SkuUtils
 */
public class SkuTest {

//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    private static class NameValue implements Comparable<NameValue> {
//
//        private String name;
//
//        private List<String> values;
//
//        @Override
//        public int compareTo(NameValue o) {
//            return name.compareTo(o.name);
//        }
//    }
//
//    public static void main(String[] args) {
//        String text = "颜色:蓝色男款,白色男款;尺码:38,39";
//        String[] nameValues = StringUtils.tokenizeToStringArray(text, ";");
//        List<NameValue> lists = new ArrayList<>(nameValues.length);
//        List<Position> positions = new ArrayList<>();
//        int excludeFirstValuesSize = 1; //排除第一个元素后其它总元素总数
//        for (int index = 0; index < nameValues.length; index++) {
//            String nameValue = nameValues[index];
//            String[] keyValue = StringUtils.tokenizeToStringArray(nameValue, ":");
//            if (keyValue.length == 2) {
//                String name = keyValue[0];
//                String[] values = StringUtils.splitByComma(keyValue[1]);
//                if (ArrayUtils.isNotEmpty(values)) {
//                    if (index != 0) { // 第一个元素不放进去
//                        excludeFirstValuesSize = excludeFirstValuesSize * values.length;
//                        positions.add(new Position(index, values.length));
//                    }
//                    lists.add(new NameValue(name, ArrayUtils.asArrayList(values)));
//                }
//            }
//        }
//        if (CollectionUtils.size(lists) <= 1) {
//            System.out.println("---------over---------");
//            return;
//        }
//        NameValue firstNameValue = lists.get(0);
//        List<List<TextValueItem>> result = new ArrayList<>();
//        Collections.reverse(positions);//集合反转
//        Collections.reverse(lists);//集合反转
//        for (String firstValue : firstNameValue.values) {
//            for (int i = 0; i < excludeFirstValuesSize; i++) {
//                List<TextValueItem> list = new ArrayList<>();
//                list.add(new TextValueItem(firstNameValue.name, firstValue));
//                CollectionUtils.addAll(list, getList(i, positions, lists));
//                result.add(list);
//                System.out.println(JsonUtils.serialize(list));
//            }
////            颜色:蓝色男款,白色男款
////            尺码:38,39,40;类别:a,b
//            /*
//                    1:3
//                    2:2
//
//             */
//            System.out.println("---------------------------");
//        }
//        System.out.println(JsonUtils.serialize(result, true));
//        System.out.println(result.size());
//    }
//
//
//    private static List<TextValueItem> getList(int index, List<Position> positions, List<NameValue> nameValues) {
//        List<TextValueItem> result = new ArrayList<>();
//        int before = index % positions.get(0).getMaxPosition();
//        for (int i = 0, size = positions.size(); i < size; i++) {
//            NameValue nameValue = nameValues.get(i);
//            if (before >= nameValue.values.size()) {
//                before = before % nameValue.values.size();
//            }
//            result.add(new TextValueItem(nameValue.name, nameValue.values.get(before)));
//            before = index / positions.stream().limit(i + 1).map(Position::getMaxPosition).reduce(1, (x, y) -> x * y);
//        }
//        Collections.reverse(result);
//        return result;
//    }
//
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    private static class Position {
//
//        private int index;
//
//        private int maxPosition;
//    }

}
