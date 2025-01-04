package com.sean.leetcode.LeetCode732;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-01-04 21:01
 * @Description TODO
 */
public class MyCalendarThree1 {

    Map<Integer, Integer> tree;
    Map<Integer, Integer> lazy;

    public MyCalendarThree1() {
        tree = new HashMap<Integer, Integer>();
        lazy = new HashMap<Integer, Integer>();
    }

    public int book(int start, int end) {
        update(start, end - 1, 0, 1_000_000_000, 1);
        return tree.getOrDefault(1, 0);
    }

    private void update(int start, int end, int left, int right, int index) {
        if (right < start || end < left) {
            return;
        }
        if (start <= left && right <= end) {
            tree.put(index, tree.getOrDefault(index, 0) + 1);
            lazy.put(index, lazy.getOrDefault(index, 0) + 1);
        } else {
            int mid = (left + right) >> 1;
            update(start, end, left, mid, 2 * index);
            update(start, end, mid + 1, right, 2 * index + 1);
            tree.put(index, lazy.getOrDefault(index, 0) + Math.max(tree.getOrDefault(2 * index, 0), tree.getOrDefault(2 * index + 1, 0)));
        }
    }

}
