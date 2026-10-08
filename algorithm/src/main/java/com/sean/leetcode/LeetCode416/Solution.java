package com.sean.leetcode.LeetCode416;

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
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int n = nums.length;
        int[][] memo = new int[n + 1][(sum >> 1) + 1];
        return process(nums, memo, 0, sum >> 1) == 1;
    }

    private int process(int[] nums, int[][] memo, int i, int sum) {
        if (memo[i][sum] != 0) {
            return memo[i][sum];
        }
        int n = nums.length;
        if (i == n) {
            return memo[i][sum] = sum == 0 ? 1 : -1;
        }
        return memo[i][sum] = (process(nums, memo, i + 1, sum) == 1 || (nums[i] <= sum && process(nums, memo, i + 1, sum - nums[i]) == 1)) ? 1 : -1;
    }

}
