package com.sean.leetcode.LeetCode377;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 13:47
 * @Description: https://leetcode.cn/problems/combination-sum-iv/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 377. 组合总和 Ⅳ
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
 * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 */
public class Solution {

    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return target == 0 ? 1 : 0;
        }
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        return process(nums, target, dp);
    }

    private int process(int[] nums, int cur, int[] dp) {
        if (dp[cur] != -1) {
            return dp[cur];
        }
        if (cur == 0) {
            dp[cur] = 1;
            return dp[cur];
        }
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (cur - nums[i] >= 0) {
                res += process(nums, cur - nums[i], dp);
            }
        }
        dp[cur] = res;
        return res;
    }

}
