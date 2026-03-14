package com.sean.leetcode.LeetCodeInterview0805;

/**
 * @Author xionghaiyang
 * @Date 2026-03-14 19:15
 * @Description https://leetcode.cn/problems/recursive-mulitply-lcci
 * 面试题 08.05. 递归乘法
 * 递归乘法。
 * 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。
 * 可以使用加号、减号、位移，但要吝啬一些。
 * 保证乘法范围不会溢出
 */
public class Solution {

    public int multiply(int A, int B) {
        return B != 0 ? multiply(A << 1, B >> 1) + ((B & 1) == 1 ? A : 0) : 0;
    }

}
