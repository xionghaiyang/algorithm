package com.sean.leetcode.LeetCode416;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-04-07 08:55
 * @Description https://leetcode.cn/problems/partition-equal-subset-sum
 * 416. 分割等和子集
 * 给你一个 只包含正整数 的 非空 数组 nums 。
 * 请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class Solution {

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if ((sum & 1) != 0) {
            return false;
        }
        int[][] dp = new int[(sum >> 1) + 1][nums.length + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(nums, sum >> 1, 0, dp);
    }

    private boolean process(int[] nums, int sum, int i, int[][] dp) {
        if (dp[sum][i] != -1) {
            return dp[sum][i] == 1;
        }
        if (i == nums.length) {
            dp[sum][i] = sum == 0 ? 1 : 0;
            return dp[sum][i] == 1;
        }
        if (sum == 0) {
            dp[sum][i] = 1;
            return true;
        }
        boolean res = process(nums, sum, i + 1, dp) || (sum >= nums[i] && process(nums, sum - nums[i], i + 1, dp));
        dp[sum][i] = res ? 1 : 0;
        return res;
    }

}
