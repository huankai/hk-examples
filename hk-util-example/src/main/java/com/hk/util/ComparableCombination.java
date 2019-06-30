package com.hk.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author huangkai
 * @date 2019-06-30 20:24
 * @see https://blog.csdn.net/u010157717/article/details/8811923
 */

public class ComparableCombination<T extends Comparable<? super T>> implements Combinationable<T> {

    private List<T> elements;

    public ComparableCombination(List<T> elements) {
        this.elements = elements;
    }

    @Override
    public List<List<T>> findCombinations(int start, int end) {
        if (start < 0) {
            throw new IllegalArgumentException("start can't be negative: "
                    + start);
        }
        if (end > elements.size()) {
            throw new IllegalArgumentException(
                    "end can't be larger than elements size: " + end + ">"
                            + elements.size());
        }
        if (start > end) {
            throw new IllegalArgumentException(
                    "start can't be larger than end: " + start + ">"
                            + end);
        }
        List<List<T>> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            result.addAll(findCombinations(i));
        }
        return result;
    }

    @Override
    public List<List<T>> findCombinations(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size can't be negative: "
                    + size);
        }
        if (size > elements.size()) {
            throw new IllegalArgumentException(
                    "size can't be larger than elements size: " + size + ">"
                            + elements.size());
        }

        List<List<T>> result = new ArrayList<>();

        if (size == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        List<List<T>> combinations = findCombinations(size - 1);
        for (List<T> combination : combinations) {
            for (T element : elements) {
                if (combination.contains(element)) {
                    continue;
                }
                List<T> list = new ArrayList<>(combination);
                if (list.contains(element)) {
                    continue;
                }
                list.add(element);
                Collections.sort(list);
                if (result.contains(list)) {
                    continue;
                }
                result.add(list);
            }
        }
        return result;
    }

    @Override
    public List<T> getElements() {
        return elements;
    }
}
