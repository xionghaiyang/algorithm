package com.sean.leetcode.LeetCode334;

/**
 * @Author: xionghaiyang
 * @Date: 2023-05-29 16:37
 * @Description: https://leetcode.cn/problems/increasing-triplet-subsequence
 * 334. 递增的三元子序列
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，
 * 使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 * 1 <= nums.length <= 5 * 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 */
public class Solution {

    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            } else {
                return true;
            }
        }
        return false;
    }

}
