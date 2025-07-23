package com.sean.leetcode.LeetCode66;

/**
 * @Author xionghaiyang
 * @Date 2025-07-23 19:55
 * @Description https://leetcode.cn/problems/plus-one
 * 66. 加一
 * 给定一个表示 大整数 的整数数组 digits，其中 digits[i] 是整数的第 i 位数字。
 * 这些数字按从左到右，从最高位到最低位排列。
 * 这个大整数不包含任何前导 0。
 * 将大整数加 1，并返回结果的数字数组。
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 * digits 不包含任何前导 0。
 */
public class Solution {

    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }

}
