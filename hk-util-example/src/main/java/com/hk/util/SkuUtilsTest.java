package com.hk.util;

import com.hk.commons.util.SkuUtils;
import com.hk.commons.util.TextValueItem;

import java.util.List;

/**
 * sku 工具类测试
 *
 * @author kevin
 * @date 2019-7-8 17:56
 */
public class SkuUtilsTest {

    public static void main(String[] args) {
        List<List<TextValueItem>> result = SkuUtils.getSkuAttributeList("颜色:蓝色男款,白色男款");
        System.out.println(result.size());
    }
}
