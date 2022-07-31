package com.sean.leetcode;

/**
 * 区域和检索 - 数组不可变
 * https://leetcode-cn.com/problems/range-sum-query-immutable/
 */
public class LeetCode303 {

    private static class NumArray {

        private int[] preSum = null;

        public NumArray(int[] nums) {
            preSum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                preSum[i + 1] = preSum[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }
    }

}
