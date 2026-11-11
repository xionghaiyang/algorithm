package com.sean.leetcode.LeetCode410;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2024-01-21 17:28
 * @Description: https://leetcode.cn/problems/split-array-largest-sum
 * 410. 分割数组的最大值
 * 给定一个非负整数数组 nums 和一个整数 k ，你需要将这个数组分成 k 个非空的连续子数组。
 * 设计一个算法使得这 k 个子数组各自和的最大值最小。
 * 返回分割后最小的和的最大值。
 * 子数组 是数组中连续的部分。
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 10^6
 * 1 <= k <= min(50, nums.length)
 */
public class Solution {

    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        //dp[i][j]:前i个元素分成j个非空的连续子数组，这j个子数组各自和最大值的最小值
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], preSum[i] - preSum[k]));
                }
            }
        }
        return dp[n][m];
    }

    public int splitArray1(int[] nums, int k) {
        int max = 0, sum = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        int left = max, right = sum, res = sum;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(nums, k, mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    private boolean check(int[] nums, int k, int x) {
        int sum = 0, cnt = 1;
        for (int num : nums) {
            if (sum + num <= x) {
                sum += num;
            } else {
                sum = num;
                cnt++;
            }
        }
        return cnt <= k;
    }

}
