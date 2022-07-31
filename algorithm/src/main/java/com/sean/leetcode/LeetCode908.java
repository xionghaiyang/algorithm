package com.sean.leetcode;

public class LeetCode908 {

    public static int smallestRangeI(int[] nums, int k) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }
        return max - min <= 2 * k ? 0 : max - min - 2 * k;
    }

}
