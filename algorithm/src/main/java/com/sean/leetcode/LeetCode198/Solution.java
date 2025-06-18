package com.sean.leetcode.LeetCode198;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-14 19:54
 * @Description: https://leetcode.cn/problems/house-robber
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class Solution {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];
    }

    public int rob1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return process(nums, dp, 0);
    }

    private int process(int[] nums, int[] dp, int i) {
        if (i >= nums.length) {
            return 0;
        }
        if (dp[i] != -1) {
            return dp[i];
        }
        //偷取
        int res = nums[i] + process(nums, dp, i + 2);
        //不偷取
        res = Math.max(res, process(nums, dp, i + 1));
        dp[i] = res;
        return res;
    }

}
