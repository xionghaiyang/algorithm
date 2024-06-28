package com.sean.leetcode;

/**
 * 统计位数为偶数的数字
 * https://leetcode-cn.com/problems/find-numbers-with-even-number-of-digits/
 */
public class LeetCode1295 {

    public static int findNumbers(int[] nums) {
        int res = 0;
        for (int num : nums) {
            String s = String.valueOf(num);
            if (s.length() % 2 == 0) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findNumbers(new int[]{12, 345, 2, 6, 7896}));
        System.out.println(findNumbers(new int[]{555, 901, 482, 1771}));
    }
}
