package com.sean.leetcode.LeetCode3599;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-07-05 18:32
 * @Description https://leetcode.cn/problems/partition-array-to-minimize-xor
 * 3599. 划分数组得到最小 XOR
 * 给你一个整数数组 nums 和一个整数 k。
 * 你的任务是将 nums 分成 k 个非空的 子数组 。
 * 对每个子数组，计算其所有元素的按位 XOR 值。
 * 返回这 k 个子数组中 最大 XOR 的 最小值 。
 * 子数组 是数组中连续的 非空 元素序列。
 * 1 <= nums.length <= 250
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= n
 */
public class Solution {

    public int minXor(int[] nums, int k) {
        int n = nums.length;
        //dp[i][j]:以i结尾的数组，分成j个非空子数组，最大XOR的最小值
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        return process(nums, n - 1, k, dp);
    }

    private int process(int[] nums, int i, int j, int[][] dp) {
        if (dp[i][j] != Integer.MAX_VALUE || j == 0) {
            return dp[i][j];
        }
        if (j == 1) {
            int res = 0;
            for (int k = 0; k <= i; k++) {
                res ^= nums[k];
            }
            dp[i][j] = res;
            return res;
        }
        int res = Integer.MAX_VALUE;
        for (int k = i, xor = 0; k >= j - 1; k--) {
            xor ^= nums[k];
            res = Math.min(res, Math.max(xor, process(nums, k - 1, j - 1, dp)));
        }
        dp[i][j] = res;
        return dp[i][j];
    }

}
