package com.sean.leetcode.LeetCode643;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-30 10:43
 * @Description: https://leetcode.cn/problems/maximum-average-subarray-i/?envType=study-plan-v2&envId=leetcode-75
 * 643. 子数组最大平均数 I
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * 任何误差小于 10-5 的答案都将被视为正确答案。
 */
public class Solution {

    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = 0;
        long curSum = 0;
        for (; right < k; right++) {
            curSum += nums[right];
        }
        long maxSum = curSum;
        for (; right < n; left++, right++) {
            curSum += nums[right] - nums[left];
            maxSum = Math.max(maxSum, curSum);
        }
        return (double) maxSum / k;
    }

}
