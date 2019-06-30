package com.hk.util;

import java.util.List;

/**
 * @author huangkai
 * @date 2019-06-30 20:23
 */
public interface Combinationable<T> {


    List<List<T>> findCombinations(int start, int end);

    default List<List<T>> findCombinations() {
        return findCombinations(0, getElements().size());
    }

    List<List<T>> findCombinations(int size);

    List<T> getElements();

}
