package com.sean.leetcode.LeetCode191;

/**
 * @Author xionghaiyang
 * @Date 2025-07-23 18:23
 * @Description https://leetcode.cn/problems/number-of-1-bits
 * 191. 位1的个数
 * 给定一个正整数 n，编写一个函数，获取一个正整数的二进制形式并返回其二进制表达式中 设置位 的个数（也被称为汉明重量）。
 * 1 <= n <= 2^31 - 1
 */
public class Solution {

    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n &= (n - 1);
            res++;
        }
        return res;
    }

}
