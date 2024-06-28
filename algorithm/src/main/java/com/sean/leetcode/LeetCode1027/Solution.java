package com.sean.leetcode.LeetCode1027;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-23 08:30
 * @Description: https://leetcode.cn/problems/longest-arithmetic-subsequence/
 * 1027. 最长等差数列
 * 给你一个整数数组 nums，返回 nums 中最长等差子序列的长度。
 * 回想一下，nums 的子序列是一个列表 nums[i1], nums[i2], ..., nums[ik] ，
 * 且 0 <= i1 < i2 < ... < ik <= nums.length - 1。
 * 并且如果 seq[i+1] - seq[i]( 0 <= i < seq.length - 1) 的值都相同，那么序列 seq 是等差的。
 */
public class Solution {

    public int longestArithSeqLength1(int[] nums) {
        int n = nums.length;
        int min = 500, max = 0;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        int diff = max - min;
        int[][] dp = new int[n][2 * diff + 1];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int d = nums[i] - nums[j] + diff;
                dp[i][d] = dp[j][d] + 1;
                res = Math.max(res, dp[i][d]);
            }
        }
        return res + 1;
    }

    public int longestArithSeqLength2(int[] nums) {
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();
        int diff = max - min;
        int res = 1;
        for (int d = -diff; d <= diff; d++) {
            int[] f = new int[max + 1];
            Arrays.fill(f, -1);
            for (int num : nums) {
                int prev = num - d;
                if (prev >= min && prev <= max && f[prev] != -1) {
                    f[num] = Math.max(f[num], f[prev] + 1);
                    res = Math.max(res, f[num]);
                }
                f[num] = Math.max(f[num], 1);
            }
        }
        return res;
    }

}
