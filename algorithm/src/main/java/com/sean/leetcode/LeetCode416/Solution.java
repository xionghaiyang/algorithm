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
        if ((sum & 1) == 1) {
            return false;
        }
        int n = nums.length;
        //只需要构建一个子集，另一个子集自动构建
        //dp[i][sum]，以i的开头的数组，是否可以构建和为sum的子集
        int[][] dp = new int[n + 1][(sum >> 1) + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(nums, 0, sum >> 1, dp) == 1;
    }

    private int process(int[] nums, int i, int sum, int[][] dp) {
        if (dp[i][sum] != -1) {
            return dp[i][sum];
        }
        if (i == nums.length) {
            dp[i][sum] = sum == 0 ? 1 : 0;
            return dp[i][sum];
        }
        if (sum == 0) {
            dp[i][sum] = 1;
            return dp[i][sum];
        }
        dp[i][sum] = (process(nums, i + 1, sum, dp) == 1 || (nums[i] <= sum && process(nums, i + 1, sum - nums[i], dp) == 1)) ? 1 : 0;
        return dp[i][sum];
    }

}
