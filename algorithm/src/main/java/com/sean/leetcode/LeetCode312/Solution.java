package com.sean.leetcode.LeetCode312;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-06-09 08:47
 * @Description https://leetcode.cn/problems/burst-balloons/
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。
 * 戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
 * 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。
 * 如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * 求所能获得硬币的最大数量。
 */
public class Solution {

    private int[] arr;
    private int[][] dp;

    public int maxCoins(int[] nums) {
        int n = nums.length;
        arr = new int[n + 2];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = nums[i - 1];
        }
        arr[0] = 1;
        arr[n + 1] = 1;
        dp = new int[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(0, n + 1);
    }

    private int process(int left, int right) {
        if (left >= right - 1) {
            return 0;
        }
        if (dp[left][right] != -1) {
            return dp[left][right];
        }
        for (int i = left + 1; i < right; i++) {
            int sum = arr[left] * arr[i] * arr[right];
            sum += process(left, i) + process(i, right);
            dp[left][right] = Math.max(dp[left][right], sum);
        }
        return dp[left][right];
    }

}
