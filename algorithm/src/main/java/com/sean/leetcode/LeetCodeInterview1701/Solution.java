package com.sean.leetcode.LeetCodeInterview1701;

/**
 * @Author xionghaiyang
 * @Date 2026-03-24 19:50
 * @Description https://leetcode.cn/problems/add-without-plus-lcci
 * 面试题 17.01. 不用加号的加法
 * 设计一个函数把两个数字相加。
 * 不得使用 + 或者其他算术运算符。
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 */
public class Solution {

    public int add(int a, int b) {
        while (b != 0) {
            //二进制位都为1时，才会产生进位
            int carry = (a & b) << 1;
            //异或就是无进位相加
            a ^= b;
            b = carry;
        }
        return a;
    }

}
