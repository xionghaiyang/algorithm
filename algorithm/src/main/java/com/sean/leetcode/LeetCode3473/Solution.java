package com.sean.leetcode.LeetCode3473;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-13 17:27
 * @Description: https://leetcode.cn/problems/sum-of-k-subarrays-with-length-at-least-m
 * 3473. 长度至少为 M 的 K 个子数组之和
 * 给你一个整数数组 nums 和两个整数 k 和 m。
 * 返回数组 nums 中 k 个不重叠子数组的 最大 和，其中每个子数组的长度 至少 为 m。
 * 子数组 是数组中的一个连续序列。
 * 1 <= nums.length <= 2000
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= floor(nums.length / m)
 * 1 <= m <= 3
 */
public class Solution {

    public int maxSum(int[] nums, int k, int m) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        //f[i][j]:长为j的前缀nums[0..j-1]取出i个连续子数组所得到的最大和
        int[][] f = new int[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            f[i][i * m - 1] = Integer.MIN_VALUE;
            int max = Integer.MIN_VALUE;
            for (int j = i * m; j <= n - (k - i) * m; j++) {
                max = Math.max(max, f[i - 1][j - m] - preSum[j - m]);
                f[i][j] = Math.max(f[i][j - 1], max + preSum[j]);
            }
        }
        return f[k][n];
    }

}
