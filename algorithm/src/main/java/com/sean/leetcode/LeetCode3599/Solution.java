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
        //dp[i][j]:前j个元素分为i个连续子数组，最大子数组异或和的最小值
        int[][] dp = new int[k + 1][n + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 0;
        for (int i = 1; i <= k; i++) {
            //每个子数组的长度至少是1，所以j的最小值为i,剩余k-i个元素，所以j的最大值为n - (k - i)
            for (int j = i; j <= n - (k - i); j++) {
                int res = Integer.MAX_VALUE;
                for (int x = j - 1,xor = 0; x >= i - 1; x--) {
                    xor ^= nums[x];
                    res = Math.min(res, Math.max(dp[i - 1][x], xor));
                }
                dp[i][j] = res;
            }
        }
        return dp[k][n];
    }


}
