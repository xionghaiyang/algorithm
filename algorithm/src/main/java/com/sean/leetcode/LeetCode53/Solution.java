package com.sean.leetcode.LeetCode53;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-08 21:17
 * @Description: https://leetcode.cn/problems/maximum-subarray/?plan=data-structures&plan_progress=zquui9s
 * 53. 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 */
public class Solution {

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }

}
