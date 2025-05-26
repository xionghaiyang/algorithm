package com.sean.leetcode.LeetCode2894;

/**
 * @Author xionghaiyang
 * @Date 2025-05-27 05:55
 * @Description https://leetcode.cn/problems/divisible-and-non-divisible-sums-difference
 * 2894. 分类求和并作差
 * 给你两个正整数 n 和 m 。
 * 现定义两个整数 num1 和 num2 ，如下所示：
 * num1：范围 [1, n] 内所有 无法被 m 整除 的整数之和。
 * num2：范围 [1, n] 内所有 能够被 m 整除 的整数之和。
 * 返回整数 num1 - num2 。
 * 1 <= n, m <= 1000
 */
public class Solution {

    public int differenceOfSums(int n, int m) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (i % m != 0) {
                res += i;
            } else {
                res -= i;
            }
        }
        return res;
    }

    public int differenceOfSums1(int n, int m) {
        int k = n / m;
        return n * (n + 1) / 2 - k * (k + 1) * m;
    }

}
