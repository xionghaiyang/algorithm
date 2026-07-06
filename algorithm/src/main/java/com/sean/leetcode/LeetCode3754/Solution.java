package com.sean.leetcode.LeetCode3754;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-07 07:37
 * @Description: https://leetcode.cn/problems/concatenate-non-zero-digits-and-multiply-by-sum-i
 * 3754. 连接非零数字并乘以其数字和 I
 * 给你一个整数 n。
 * 将 n 中所有的 非零数字 按照它们的原始顺序连接起来，形成一个新的整数 x。
 * 如果不存在 非零数字 ，则 x = 0。
 * sum 为 x 中所有数字的 数字和 。
 * 返回一个整数，表示 x * sum 的值。
 * 0 <= n <= 10^9
 */
public class Solution {

    public long sumAndMultiply(int n) {
        int x = 0, sum = 0;
        for (int pow10 = 1; n > 0; n /= 10) {
            int d = n % 10;
            if (d > 0) {
                x += d * pow10;
                sum += d;
                pow10 *= 10;
            }
        }
        return (long) x * sum;
    }

}
