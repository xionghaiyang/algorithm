package com.sean.leetcode;

import java.util.Arrays;

/**
 * 数组拆分 I
 * https://leetcode-cn.com/problems/array-partition-i/
 */
public class LeetCode561 {

    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(arrayPairSum(new int[]{1,4,3,2}));
        System.out.println(arrayPairSum(new int[]{6,2,6,5,1,2}));
    }

}
