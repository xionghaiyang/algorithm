package com.sean.leetcode;

/**
 * 只出现一次的数字
 * https://leetcode-cn.com/problems/single-number/
 */
public class LeetCode136 {

    public static int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 1}));
        System.out.println(singleNumber(new int[]{4, 1, 2, 1, 2}));
    }

}
