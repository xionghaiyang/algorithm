package com.sean.leetcode.LeetCode152;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-14 21:48
 * @Description: https://leetcode.cn/problems/maximum-product-subarray
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-位 整数。
 * 子数组 是数组的连续子序列。
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * nums 的任何子数组的乘积都 保证 是一个 32-位 整数
 */
public class Solution {

    public int maxProduct(int[] nums) {
        int n = nums.length;
        //dp[i][0]以i结尾的连续子数组，乘积的最小值
        //dp[i][1]以i结尾的连续子数组，乘积的最大值
        int[][] dp = new int[n][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(nums[i], Math.min(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i]));
            dp[i][1] = Math.max(nums[i], Math.max(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i]));
        }
        int res = dp[0][1];
        for (int i = 1; i < n; i++) {
            res = Math.max(res, dp[i][1]);
        }
        return res;
    }

    public int maxProduct1(int[] nums) {
        int n = nums.length;
        int min = nums[0], max = nums[0];
        int res = nums[0];
        for (int i = 1; i < n; i++) {
            int min1 = Math.min(nums[i], Math.min(min * nums[i], max * nums[i]));
            int max1 = Math.max(nums[i], Math.max(min * nums[i], max * nums[i]));
            min = min1;
            max = max1;
            res = Math.max(res, max);
        }
        return res;
    }

}
