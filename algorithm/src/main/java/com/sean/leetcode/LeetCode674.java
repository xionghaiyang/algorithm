package com.sean.leetcode;

public class LeetCode674 {

    public int findLengthOfLCIS(int[] nums) {
        int res = 0;
        int len = nums.length;
        int start = 0;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] <= nums[i - 1]) {
                start = i;
            }
            res = Math.max(res, i - start + 1);
        }
        return res;
    }

}
