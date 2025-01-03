package com.sean.leetcode.LeetCode731;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2025-01-03 21:05
 * @Description TODO
 */
public class MyCalendarTwo2 {

    Map<Integer, int[]> tree;

    public MyCalendarTwo2() {
        tree = new HashMap<Integer, int[]>();
    }

    public boolean book(int start, int end) {
        update(start, end - 1, 1, 0, 1_000_000_000, 1);
        tree.putIfAbsent(1, new int[2]);
        if (tree.get(1)[0] > 2) {
            update(start, end - 1, -1, 0, 1_000_000_000, 1);
            return false;
        }
        return true;
    }

    private void update(int start, int end, int val, int left, int right, int index) {
        if (right < start || end < left) {
            return;
        }
        tree.putIfAbsent(index, new int[2]);
        if (start <= left && right <= end) {
            tree.get(index)[0] += val;
            tree.get(index)[1] += val;
        } else {
            int mid = (left + right) >> 1;
            update(start, end, val, left, mid, 2 * index);
            update(start, end, val, mid + 1, right, 2 * index + 1);
            tree.putIfAbsent(2 * index, new int[2]);
            tree.putIfAbsent(2 * index + 1, new int[2]);
            tree.get(index)[0] = tree.get(index)[1] + Math.max(tree.get(2 * index)[0], tree.get(2 * index + 1)[0]);
        }
    }

}
