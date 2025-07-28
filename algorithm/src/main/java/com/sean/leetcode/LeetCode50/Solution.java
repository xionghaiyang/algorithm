package com.sean.leetcode.LeetCode50;

/**
 * @Author xionghaiyang
 * @Date 2025-07-28 13:27
 * @Description https://leetcode.cn/problems/powx-n
 * 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，x^n ）。
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * n 是一个整数
 * 要么 x 不为零，要么 n > 0 。
 * -10^4 <= x^n <= 10^4
 */
public class Solution {

    public double myPow(double x, int N) {
        double res = 1;
        long n = N;
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        while (n > 0) {
            if ((n & 1) != 0) {
                res *= x;
            }
            x *= x;
            n >>= 1;
        }
        return res;
    }

}
