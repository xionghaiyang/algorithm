package com.sean.leetcode.LeetCode334;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-29 16:37
 * @Description: https://leetcode.cn/problems/increasing-triplet-subsequence/?envType=study-plan-v2&envId=leetcode-75
 * 334. 递增的三元子序列
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，
 * 使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 */
public class Solution {

    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int small = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= small) {
                small = num;
            } else if (num <= mid) {
                mid = num;
            } else if (num > mid) {
                return true;
            }
        }
        return false;
    }

}
