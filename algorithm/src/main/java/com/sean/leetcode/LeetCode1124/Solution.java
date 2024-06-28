package com.sean.leetcode.LeetCode1124;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-05-12 19:35
 * @Description https://leetcode.cn/problems/longest-well-performing-interval/
 * 1124. 表现良好的最长时间段
 * 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
 * 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
 * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
 * 请你返回「表现良好时间段」的最大长度。
 */
public class Solution {

    public int longestWPI(int[] hours) {
        int n = hours.length;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, res = 0;
        for (int i = 0; i < n; i++) {
            sum += hours[i] > 8 ? 1 : -1;
            if (sum > 0) {
                res = Math.max(res, i + 1);
            } else {
                if (map.containsKey(sum - 1)) {
                    res = Math.max(res, i - map.get(sum - 1));
                }
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return res;
    }

}
