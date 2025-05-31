package com.sean.leetcode.LeetCode53;

/**
 * @Author xionghaiyang
 * @Date 2024-05-07 18:24
 * @Description https://leetcode.cn/problems/maximum-subarray/
 * 53. 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组是数组中的一个连续部分。
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class Solution {

    public int maxSubArray(int[] nums) {
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
