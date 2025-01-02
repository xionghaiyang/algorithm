package com.sean.leetcode.LeetCode729;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author xionghaiyang
 * @Date 2025-01-02 20:30
 * @Description TODO
 */
public class MyCalendar2 {

    Set<Integer> tree;
    Set<Integer> lazy;

    public MyCalendar2() {
        tree = new HashSet<Integer>();
        lazy = new HashSet<Integer>();
    }

    public boolean book(int start, int end) {
        if (query(start, end - 1, 0, 1_000_000_000, 1)) {
            return false;
        }
        update(start, end - 1, 0, 1_000_000_000, 1);
        return true;
    }

    private boolean query(int start, int end, int left, int right, int index) {
        if (start > right || end < left) {
            return false;
        }
        //如果该区间已经被预订，则直接返回
        if (lazy.contains(index)) {
            return true;
        }
        if (start <= left && right <= end) {
            return tree.contains(index);
        } else {
            int mid = (left + right) >> 1;
            if (end <= mid) {
                return query(start, end, left, mid, 2 * index);
            } else if (start > mid) {
                return query(start, end, mid + 1, right, 2 * index + 1);
            } else {
                return query(start, end, left, mid, 2 * index) | query(start, end, mid + 1, right, 2 * index + 1);
            }
        }
    }

    private void update(int start, int end, int left, int right, int index) {
        if (right < start || end < left) {
            return;
        }
        if (start <= left && right <= end) {
            tree.add(index);
            lazy.add(index);
        } else {
            int mid = (left + right) >> 1;
            update(start, end, left, mid, 2 * index);
            update(start, end, mid + 1, right, 2 * index + 1);
            tree.add(index);
        }
    }

}
