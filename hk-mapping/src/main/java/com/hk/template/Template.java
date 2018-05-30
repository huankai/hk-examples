package com.hk.template;

/**
 * 模板标记接口
 *
 * @author: huangkai
 * @date 2018-05-29 13:11
 */
public interface Template {

    /**
     * 如果文件存在，是否强制覆盖
     *
     * @return
     */
    default boolean forceCover() {
        return true;
    }


}
