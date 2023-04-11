package com.sean.leetcode.LeetCode1124;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-14 10:36
 * @Description: https://leetcode.cn/problems/longest-well-performing-interval/
 * 1124. 表现良好的最长时间段
 * 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
 * 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
 * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
 * 请你返回「表现良好时间段」的最大长度。
 */
public class Solution {

    public int longestWPI1(int[] hours) {
        if (hours == null || hours.length == 0) {
            return 0;
        }
        int n = hours.length;
        int[] s = new int[n + 1];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
            if (s[stack.peek()] > s[i]) {
                stack.push(i);
            }
        }
        int res = 0;
        for (int r = n; r >= 1; r--) {
            while (!stack.isEmpty() && s[stack.peek()] < s[r]) {
                res = Math.max(res, r - stack.pop());
            }
        }
        return res;
    }

    public int longestWPI(int[] hours) {
        if (hours == null || hours.length == 0) {
            return 0;
        }
        int n = hours.length;
        Map<Integer, Integer> map = new HashMap<>();
        int s = 0, res = 0;
        for (int i = 0; i < n; i++) {
            s += hours[i] > 8 ? 1 : -1;
            if (s > 0) {
                res = Math.max(res, i + 1);
            } else {
                if (map.containsKey(s - 1)) {
                    res = Math.max(res, i - map.get(s - 1));
                }
            }
            if (!map.containsKey(s)) {
                map.put(s, i);
            }
        }
        return res;
    }

}
