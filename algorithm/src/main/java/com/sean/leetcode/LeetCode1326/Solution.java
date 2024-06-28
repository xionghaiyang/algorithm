package com.sean.leetcode.LeetCode1326;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-21 08:24
 * @Description: https://leetcode.cn/problems/minimum-number-of-taps-to-open-to-water-a-garden/
 * 1326. 灌溉花园的最少水龙头数目
 * 在 x 轴上有一个一维的花园。
 * 花园长度为 n，从点 0 开始，到点 n 结束。
 * 花园里总共有 n + 1 个水龙头，分别位于 [0, 1, ..., n] 。
 * 给你一个整数 n 和一个长度为 n + 1 的整数数组 ranges ，其中 ranges[i] （下标从 0 开始）表示：
 * 如果打开点 i 处的水龙头，可以灌溉的区域为 [i -  ranges[i], i + ranges[i]] 。
 * 请你返回可以灌溉整个花园的 最少水龙头数目 。如果花园始终存在无法灌溉到的地方，请你返回 -1 。
 */
public class Solution {

    public int minTaps1(int n, int[] ranges) {
        int[][] intervals = new int[n + 1][];
        for (int i = 0; i <= n; i++) {
            int start = Math.max(0, i - ranges[i]);
            int end = Math.min(n, i + ranges[i]);
            intervals[i] = new int[]{start, end};
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            if (dp[start] == Integer.MAX_VALUE) {
                return -1;
            }
            for (int j = start; j <= end; j++) {
                dp[j] = Math.min(dp[j], dp[start] + 1);
            }
        }
        return dp[n];
    }


    public int minTaps(int n, int[] ranges) {
        int[] rightmost = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            rightmost[i] = i;
        }
        for (int i = 0; i <= n; i++) {
            int start = Math.max(0, i - ranges[i]);
            int end = Math.min(n, i + ranges[i]);
            rightmost[start] = Math.max(rightmost[start], end);
        }
        int last = 0, res = 0, pre = 0;
        for (int i = 0; i < n; i++) {
            last = Math.max(last, rightmost[i]);
            if (i == last) {
                return -1;
            }
            if (i == pre) {
                res++;
                pre = last;
            }
        }
        return res;
    }

}
