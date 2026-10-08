package com.sean.leetcode.LeetCode2915;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-09 07:36
 * @Description: https://leetcode.cn/problems/length-of-the-longest-subsequence-that-sums-to-target
 * 2915. 和为目标值的最长子序列的长度
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 target 。
 * 返回和为 target 的 nums 子序列中，子序列 长度的最大值 。
 * 如果不存在和为 target 的子序列，返回 -1 。
 * 子序列 指的是从原数组中删除一些或者不删除任何元素后，剩余元素保持原来的顺序构成的数组。
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * 1 <= target <= 1000
 */
public class Solution {

    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int i : nums) {
            for (int j = target; j >= i; j--) {
                dp[j] = Math.max(dp[j], dp[j - i] + 1);
            }
        }
        return dp[target] > 0 ? dp[target] : -1;
    }

}
