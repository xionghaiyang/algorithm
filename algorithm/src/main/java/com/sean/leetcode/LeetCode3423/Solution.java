package com.sean.leetcode.LeetCode3423;

/**
 * @Author xionghaiyang
 * @Date 2025-06-12 06:28
 * @Description https://leetcode.cn/problems/maximum-difference-between-adjacent-elements-in-a-circular-array
 * 3423. 循环数组中相邻元素的最大差值
 * 给你一个 循环 数组 nums ，请你找出相邻元素之间的 最大 绝对差值。
 * 注意：一个循环数组中，第一个元素和最后一个元素是相邻的。
 * 2 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 */
public class Solution {

    public int maxAdjacentDistance(int[] nums) {
        int n = nums.length;
        int res = Math.abs(nums[0] - nums[n - 1]);
        for (int i = 0; i < n - 1; i++) {
            res = Math.max(res, Math.abs(nums[i] - nums[i + 1]));
        }
        return res;
    }

}
