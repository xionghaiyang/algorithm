package com.sean.leetcode.LeetCodeInterview1617;

/**
 * @Author xionghaiyang
 * @Date 2026-03-24 07:48
 * @Description https://leetcode.cn/problems/contiguous-sequence-lcci
 * 面试题 16.17. 连续数列
 * 给定一个整数数组，找出总和最大的连续数列，并返回总和。
 */
public class Solution {

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0, res = nums[0];
        for (int num : nums) {
            sum = Math.max(sum + num, num);
            res = Math.max(res, sum);
        }
        return res;
    }

}
