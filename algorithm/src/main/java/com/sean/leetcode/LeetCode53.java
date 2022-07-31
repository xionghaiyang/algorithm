package com.sean.leetcode;

/**
 * 最大子序和
 * https://leetcode-cn.com/problems/maximum-subarray/
 */
public class LeetCode53 {

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum > 0) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{1, 2, -3, 4, -5}));
    }

}
