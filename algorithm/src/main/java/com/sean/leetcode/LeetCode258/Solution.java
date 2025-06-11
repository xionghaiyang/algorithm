package com.sean.leetcode.LeetCode258;

/**
 * @Author xionghaiyang
 * @Date 2025-06-11 18:41
 * @Description https://leetcode.cn/problems/add-digits
 * 258. 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 * 返回这个结果。
 * 0 <= num <= 2^31 - 1
 */
public class Solution {

    public int addDigits(int num) {
        if (num >= 0 && num <= 9) {
            return num;
        }
        int res = 0;
        while (num > 0) {
            res += (num % 10);
            num /= 10;
        }
        return addDigits(res);
    }

    public int addDigits1(int num) {
        return (num - 1) % 9 + 1;
    }

}
