package com.sean.leetcode.LeetCode1043;

/**
 * @Author: xionghaiyang
 * @Date: 2023-04-19 08:09
 * @Description: https://leetcode.cn/problems/partition-array-for-maximum-sum
 * 1043. 分隔数组以得到最大和
 * 给你一个整数数组 arr，请你将该数组分隔为长度 最多 为 k 的一些（连续）子数组。
 * 分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
 * 返回将数组分隔变换后能够得到的元素最大和。
 * 本题所用到的测试用例会确保答案是一个 32 位整数。
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 10^9
 * 1 <= k <= arr.length
 */
public class Solution {

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1, max = 0; j >= 0 && j >= i - k; j--) {
                max = Math.max(max, arr[j]);
                dp[i] = Math.max(dp[i], dp[j] + max * (i - j));
            }
        }
        return dp[n];
    }

}
