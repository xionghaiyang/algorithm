package com.sean.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class LeetCode862 {

    public int shortestSubarray(int[] nums, int k) {
        int len = nums.length;
        long[] P = new long[len + 1];
        for (int i = 0; i < len; i++) {
            P[i + 1] = P[i] + (long) nums[i];
        }
        int res = len + 1;
        Deque<Integer> deque = new LinkedList<>();
        for (int y = 0; y < len + 1; y++) {
            while (!deque.isEmpty() && P[y] <= P[deque.getLast()]) {
                deque.removeLast();
            }
            while (!deque.isEmpty() && P[y] >= P[deque.getFirst()] + k) {
                res = Math.min(res, y - deque.removeFirst());
            }
            deque.addLast(y);
        }
        return res < len + 1 ? res : -1;
    }

}
