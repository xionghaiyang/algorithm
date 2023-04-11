package com.sean.leetcode.LeetCode2427;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-06 08:17
 * @Description: https://leetcode.cn/problems/number-of-common-factors/
 * 2427. 公因子的数目
 * 给你两个正整数 a 和 b ，返回 a 和 b 的 公 因子的数目。
 * 如果 x 可以同时整除 a 和 b ，则认为 x 是 a 和 b 的一个 公因子 。
 */
public class Solution {

    public int commonFactors(int a, int b) {
        int gcd = gcd(a, b);
        int res = 0;
        for (int i = 1; i * i <= gcd; i++) {
            if (gcd % i == 0) {
                res++;
                if (i * i != gcd) {
                    res++;
                }
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            a %= b;
            a ^= b;
            b ^= a;
            a ^= b;
        }
        return a;
    }

}
