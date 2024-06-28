package com.sean.leetcode.LeetCode2681;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-08-22 15:36
 * @Description: https://leetcode.cn/problems/power-of-heroes/
 * 2681. 英雄的力量
 * 给你一个下标从 0 开始的整数数组 nums ，它表示英雄的能力值。
 * 如果我们选出一部分英雄，这组英雄的 力量 定义为：
 * i0 ，i1 ，... ik 表示这组英雄在数组中的下标。那么这组英雄的力量为 max(nums[i0],nums[i1] ... nums[ik])2 * min(nums[i0],nums[i1] ... nums[ik]) 。
 * 请你返回所有可能的 非空 英雄组的 力量 之和。由于答案可能非常大，请你将结果对 109 + 7 取余。
 */
public class Solution {

    public int sumOfPower1(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        int[] preSum = new int[n + 1];
        int res = 0, mod = 1000000007;
        for (int i = 0; i < n; i++) {
            dp[i] = (nums[i] + preSum[i]) % mod;
            preSum[i + 1] = (preSum[i] + dp[i]) % mod;
            res = (int) ((res + (long) nums[i] * nums[i] % mod * dp[i]) % mod);
            if (res < 0) {
                res += mod;
            }
        }
        return res;
    }

    public int sumOfPower(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int dp = 0, preSum = 0;
        int res = 0, mod = 1000000007;
        for (int i = 0; i < n; i++) {
            dp = (nums[i] + preSum) % mod;
            preSum = (preSum + dp) % mod;
            res = (int) ((res + (long) nums[i] * nums[i] % mod * dp) % mod);
            if (res < 0) {
                res += mod;
            }
        }
        return res;
    }

}
