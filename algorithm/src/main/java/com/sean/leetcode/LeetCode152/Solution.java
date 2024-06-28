package com.sean.leetcode.LeetCode152;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-14 21:48
 * @Description: https://leetcode.cn/problems/maximum-product-subarray/
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-位 整数。
 * 子数组 是数组的连续子序列。
 */
public class Solution {

    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] maxdp = new int[n];
        for (int i = 0; i < n; i++) {
            maxdp[i] = nums[i];
        }
        int[] mindp = new int[n];
        for (int i = 0; i < n; i++) {
            mindp[i] = nums[i];
        }
        for (int i = 1; i < n; i++) {
            maxdp[i] = Math.max(maxdp[i - 1] * nums[i], Math.max(nums[i], mindp[i - 1] * nums[i]));
            mindp[i] = Math.min(mindp[i - 1] * nums[i], Math.min(nums[i], maxdp[i - 1] * nums[i]));
        }
        int res = maxdp[0];
        for (int i = 1; i < n; i++) {
            res = Math.max(res, maxdp[i]);
        }
        return res;
    }

}
