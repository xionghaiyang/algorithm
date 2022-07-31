package com.sean.leetcode;

/**
 * 递增的三元子序列
 * https://leetcode-cn.com/problems/increasing-triplet-subsequence/
 */
public class LeetCode334 {

    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int small = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
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
