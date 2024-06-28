package com.sean.leetcode.LeetCode539;

import java.util.Collections;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-10 10:52
 * @Description: https://leetcode.cn/problems/minimum-time-difference/
 * 539. 最小时间差
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 */
public class Solution {

    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        int n = timePoints.size();
        int res = Integer.MAX_VALUE;
        int t0 = getMinutes(timePoints.get(0));
        int pre = t0;
        for (int i = 1; i < n; i++) {
            int cur = getMinutes(timePoints.get(i));
            res = Math.min(res, cur - pre);
            pre = cur;
        }
        res = Math.min(res, t0 + 1440 - pre);
        return res;
    }

    private int getMinutes(String t) {
        return ((t.charAt(0) - '0') * 10 + (t.charAt(1) - '0')) * 60 + ((t.charAt(3) - '0') * 10 + (t.charAt(4) - '0'));
    }

}
