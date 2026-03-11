package com.sean.leetcode.LeetCodeInterview0506;

/**
 * @Author xionghaiyang
 * @Date 2026-03-11 20:03
 * @Description https://leetcode.cn/problems/convert-integer-lcci
 * 面试题 05.06. 整数转换
 * 整数转换。
 * 编写一个函数，确定需要改变几个位才能将整数 A 转成整数 B。
 * A，B范围在[-2147483648, 2147483647]之间
 */
public class Solution {

    public int convertInteger(int A, int B) {
        return Integer.bitCount(A ^ B);
    }

}
