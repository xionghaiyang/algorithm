package com.sean.leetcode.LeetCode1959;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-13 18:24
 * @Description: https://leetcode.cn/problems/minimum-total-space-wasted-with-k-resizing-operations
 * 1959. K 次调整数组大小浪费的最小总空间
 * 你正在设计一个动态数组。
 * 给你一个下标从 0 开始的整数数组 nums ，其中 nums[i] 是 i 时刻数组中的元素数目。
 * 除此以外，你还有一个整数 k ，表示你可以 调整 数组大小的 最多 次数（每次都可以调整成 任意 大小）。
 * t 时刻数组的大小 sizet 必须大于等于 nums[t] ，因为数组需要有足够的空间容纳所有元素。
 * t 时刻 浪费的空间 为 sizet - nums[t] ，总 浪费空间为满足 0 <= t < nums.length 的每一个时刻 t 浪费的空间 之和 。
 * 在调整数组大小不超过 k 次的前提下，请你返回 最小总浪费空间 。
 * 注意：数组最开始时可以为 任意大小 ，且 不计入 调整大小的操作次数。
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 10^6
 * 0 <= k <= nums.length - 1
 */
public class Solution {

    public int minSpaceWastedKResizing(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        int[][] waste = new int[n][n];
        for (int left = 0; left < n; left++) {
            int max = 0;
            for (int right = left; right < n; right++) {
                max = Math.max(max, nums[right]);
                waste[left][right] = max * (right - left + 1) - (preSum[right + 1] - preSum[left]);
            }
        }
        int[][] dp = new int[k + 2][n + 1];
        for (int i = 0; i <= k + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= k + 1; i++) {
            for (int j = 1; j <= n; j++) {
                for (int t = i - 1; t < j; t++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][t] + waste[t][j - 1]);
                }
            }
        }
        return dp[k + 1][n];
    }

}
