package com.sean.leetcode.LeetCode410;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-21 17:28
 * @Description: https://leetcode.cn/problems/split-array-largest-sum/
 * 410. 分割数组的最大值
 * 给定一个非负整数数组 nums 和一个整数 k ，你需要将这个数组分成 k 个非空的连续子数组。
 * 设计一个算法使得这 k 个子数组各自和的最大值最小。
 */
public class Solution {

    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return dp[n][m];
    }

    public int splitArray1(int[] nums, int m) {
        int left = 0, right = 0;
        for (int num : nums) {
            right += num;
            if (left < num) {
                left = num;
            }
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (check(nums, mid, m)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] num, int x, int m) {
        int sum = 0;
        int cnt = 1;
        for (int i = 0; i < num.length; i++) {
            if (sum + num[i] > x) {
                cnt++;
                sum = num[i];
            } else {
                sum += num[i];
            }
        }
        return cnt <= m;
    }

}
