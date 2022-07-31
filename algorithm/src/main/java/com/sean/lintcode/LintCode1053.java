package com.sean.lintcode;

public class LintCode1053 {

    public static int dominantIndex(int[] nums) {
        int res = -1, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                res = i;
            }
        }
        for (int i = 0; i != res && i < nums.length; i++) {
            if (max < (nums[i] * 2)) {
                return -1;
            }
        }
        return res;
    }

}
