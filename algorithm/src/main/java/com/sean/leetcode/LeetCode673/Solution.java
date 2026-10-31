package com.sean.leetcode.LeetCode673;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-31 17:28
 * @Description: https://leetcode.cn/problems/number-of-longest-increasing-subsequence
 * 673. 最长递增子序列的个数
 * 给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。
 * 注意 这个数列必须是 严格 递增的。
 * 1 <= nums.length <= 2000
 * -10^6 <= nums[i] <= 10^6
 */
public class Solution {

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length, maxLen = 0, res = 0;
        //dp[i]:以nums[i]结尾的最长上升子序列的长度
        int[] dp = new int[n];
        //cnt[i]:以nums[i]结尾的最长上升子序列的个数
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                res = cnt[i];
            } else if (dp[i] == maxLen) {
                res += cnt[i];
            }
        }
        return res;
    }

}
