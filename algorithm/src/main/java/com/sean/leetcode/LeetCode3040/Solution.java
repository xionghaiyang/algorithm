package com.sean.leetcode.LeetCode3040;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-06-08 10:46
 * @Description https://leetcode.cn/problems/maximum-number-of-operations-with-the-same-score-ii/
 * 3040. 相同分数的最大操作数目 II
 * 给你一个整数数组 nums ，如果 nums 至少 包含 2 个元素，你可以执行以下操作中的 任意 一个：
 * 选择 nums 中最前面两个元素并且删除它们。
 * 选择 nums 中最后两个元素并且删除它们。
 * 选择 nums 中第一个和最后一个元素并且删除它们。
 * 一次操作的 分数 是被删除元素的和。
 * 在确保 所有操作分数相同 的前提下，请你求出 最多 能进行多少次操作。
 * 请你返回按照上述要求 最多 可以进行的操作次数。
 */
public class Solution {

    int[] nums;
    int[][] dp;

    public int maxOperations(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        this.dp = new int[n][n];
        int res = 0;
        res = Math.max(res, process(0, n - 1, nums[0] + nums[n - 1]));
        res = Math.max(res, process(0, n - 1, nums[0] + nums[1]));
        res = Math.max(res, process(0, n - 1, nums[n - 2] + nums[n - 1]));
        return res;
    }

    private int process(int i, int j, int target) {
        for (int k = 0; k < nums.length; k++) {
            Arrays.fill(dp[k], -1);
        }
        return dfs(i, j, target);
    }

    private int dfs(int i, int j, int target) {
        if (i >= j) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int res = 0;
        if (nums[i] + nums[i + 1] == target) {
            res = Math.max(res, dfs(i + 2, j, target) + 1);
        }
        if (nums[j - 1] + nums[j] == target) {
            res = Math.max(res, dfs(i, j - 2, target) + 1);
        }
        if (nums[i] + nums[j] == target) {
            res = Math.max(res, dfs(i + 1, j - 1, target) + 1);
        }
        dp[i][j] = res;
        return res;
    }

}
