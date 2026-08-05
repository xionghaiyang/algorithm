package com.sean.leetcode.LeetCode3345;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-06 07:27
 * @Description: https://leetcode.cn/problems/smallest-divisible-digit-product-i
 * 3345. 最小可整除数位乘积 I
 * 给你两个整数 n 和 t 。
 * 请你返回大于等于 n 的 最小 整数，且该整数的 各数位之积 能被 t 整除。
 * 1 <= n <= 100
 * 1 <= t <= 10
 */
public class Solution {

    public int smallestNumber(int n, int t) {
        for (int i = n; ; i++) {
            int prod = 1;
            for (int x = i; x > 0; x /= 10) {
                prod *= x % 10;
            }
            if (prod % t == 0) {
                return i;
            }
        }
    }

}
