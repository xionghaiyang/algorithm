package com.sean.leetcode;

/**
 * 只出现一次的数字 II
 * https://leetcode-cn.com/problems/single-number-ii/
 */
public class LeetCode137 {

    public static int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            b = (b ^ num) & ~a;
            a = (a ^ num) & ~b;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{1, 1, 1, 3, 2, 2, 2}));
        System.out.println(singleNumber(new int[]{0,1,0,1,0,1,99}));
    }

}
