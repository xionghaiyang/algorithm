package com.sean.leetcode.LeetCode377;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 13:47
 * @Description: https://leetcode.cn/problems/combination-sum-iv
 * 377. 组合总和 Ⅳ
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
 * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 */
public class Solution {

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        return process(nums, dp, target);
    }

    private int process(int[] nums, int[] dp, int cur) {
        if (dp[cur] != -1) {
            return dp[cur];
        }
        if (cur == 0) {
            return dp[cur] = 1;
        }
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (cur - nums[i] >= 0) {
                res += process(nums, dp, cur - nums[i]);
            }
        }
        return dp[cur] = res;
    }

}
