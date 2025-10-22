package com.sean.leetcode.LeetCode643;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-30 10:43
 * @Description: https://leetcode.cn/problems/maximum-average-subarray-i
 * 643. 子数组最大平均数 I
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * 任何误差小于 10^-5 的答案都将被视为正确答案。
 * n == nums.length
 * 1 <= k <= n <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class Solution {

    public double findMaxAverage(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 0, sum = 0; i < n; i++) {
            sum += nums[i];
            if (i < k - 1) {
                continue;
            }
            maxSum = Math.max(maxSum, sum);
            sum -= nums[i - k + 1];
        }
        return (double) maxSum / k;
    }

}
