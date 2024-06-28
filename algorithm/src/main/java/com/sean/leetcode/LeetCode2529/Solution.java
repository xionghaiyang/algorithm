package com.sean.leetcode.LeetCode2529;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-09 10:14
 * @Description: https://leetcode.cn/problems/maximum-count-of-positive-integer-and-negative-integer/
 * 2529. 正整数和负整数的最大计数
 * 给你一个按 非递减顺序 排列的数组 nums ，返回正整数数目和负整数数目中的最大值。
 * 换句话讲，如果 nums 中正整数的数目是 pos ，而负整数的数目是 neg ，返回 pos 和 neg二者中的最大值。
 * 注意：0 既不是正整数也不是负整数。
 */
public class Solution {

    public int maximumCount(int[] nums) {
        int pos1 = lowerBound(nums, 0);
        int pos2 = lowerBound(nums, 1);
        return Math.max(pos1, nums.length - pos2);
    }

    private int lowerBound(int[] nums, int val) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= val) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

}
