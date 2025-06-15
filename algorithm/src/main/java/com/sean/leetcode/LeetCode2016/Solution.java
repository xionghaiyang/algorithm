package com.sean.leetcode.LeetCode2016;

/**
 * @Author xionghaiyang
 * @Date 2025-06-16 06:45
 * @Description https://leetcode.cn/problems/maximum-difference-between-increasing-elements
 * 2016. 增量元素之间的最大差值
 * 给你一个下标从 0 开始的整数数组 nums ，该数组的大小为 n ，
 * 请你计算 nums[j] - nums[i] 能求得的 最大差值 ，其中 0 <= i < j < n 且 nums[i] < nums[j] 。
 * 返回 最大差值 。
 * 如果不存在满足要求的 i 和 j ，返回 -1 。
 * n == nums.length
 * 2 <= n <= 1000
 * 1 <= nums[i] <= 10^9
 */
public class Solution {

    public int maximumDifference(int[] nums) {
        int n = nums.length;
        int res = Integer.MIN_VALUE, min = nums[0];
        for (int i = 1; i < n; i++) {
            res = Math.max(res, nums[i] - min);
            min = Math.min(min, nums[i]);
        }
        return res <= 0 ? -1 : res;
    }

}
