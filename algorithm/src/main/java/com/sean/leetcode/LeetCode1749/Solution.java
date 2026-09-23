package com.sean.leetcode.LeetCode1749;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-23 18:38
 * @Description: https://leetcode.cn/problems/maximum-absolute-sum-of-any-subarray
 * 1749. 任意子数组和的绝对值的最大值
 * 给你一个整数数组 nums 。
 * 一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
 * 请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
 * abs(x) 定义如下：
 * 如果 x 是负整数，那么 abs(x) = -x 。
 * 如果 x 是非负整数，那么 abs(x) = x 。
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class Solution {

    public int maxAbsoluteSum(int[] nums) {
        int res = 0, max = 0, min = 0;
        for (int num : nums) {
            max = Math.max(max, 0) + num;
            min = Math.min(min, 0) + num;
            res = Math.max(res, Math.max(max, -min));
        }
        return res;
    }

}
