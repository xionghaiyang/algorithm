package com.sean.leetcode.LeetCode3105;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-12 06:24
 * @Description: https://leetcode.cn/problems/longest-strictly-increasing-or-strictly-decreasing-subarray
 * 3105. 最长的严格递增或递减子数组
 * 给你一个整数数组 nums 。
 * 返回数组 nums 中 严格递增 或 严格递减 的最长非空子数组的长度。
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 50
 */
public class Solution {

    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        int res = 1, inc = 1, dec = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                dec = 1;
                res = Math.max(res, ++inc);
            } else if (nums[i] < nums[i - 1]) {
                inc = 1;
                res = Math.max(res, ++dec);
            } else {
                inc = 1;
                dec = 1;
            }
        }
        return res;
    }

}
