package com.sean.leetcode.LeetCode3640;

/**
 * @Author xionghaiyang
 * @Date 2025-08-09 20:59
 * @Description https://leetcode.cn/problems/trionic-array-ii
 * 3640. 三段式数组 II
 * 给你一个长度为 n 的整数数组 nums。
 * 三段式子数组 是一个连续子数组 nums[l...r]（满足 0 <= l < r < n），并且存在下标 l < p < q < r，使得：
 * nums[l...p] 严格 递增，
 * nums[p...q] 严格 递减，
 * nums[q...r] 严格 递增。
 * 请你从数组 nums 的所有三段式子数组中找出和最大的那个，并返回其 最大 和。
 * 4 <= n = nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 保证至少存在一个三段式子数组。
 */
public class Solution {

    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        final long INF = Long.MIN_VALUE / 2;
        long res = INF, f1 = INF, f2 = INF, f3 = INF;
        for (int i = 1; i < n; i++) {
            int x = nums[i - 1], y = nums[i];
            f3 = x < y ? Math.max(f3, f2) + y : INF;
            f2 = x > y ? Math.max(f2, f1) + y : INF;
            f1 = x < y ? Math.max(f1, x) + y : INF;
            res = Math.max(res, f3);
        }
        return res;
    }

}
