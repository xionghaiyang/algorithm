package com.sean.leetcode.LeetCode1925;

/**
 * @Author xionghaiyang
 * @Date 2025-12-08 11:28
 * @Description https://leetcode.cn/problems/count-square-sum-triples
 * 1925. 统计平方和三元组的数目
 * 一个 平方和三元组 (a,b,c) 指的是满足 a^2 + b^2 = c^2 的 整数 三元组 a，b 和 c 。
 * 给你一个整数 n ，请你返回满足 1 <= a, b, c <= n 的 平方和三元组 的数目。
 * 1 <= n <= 250
 */
public class Solution {

    public int countTriples(int n) {
        int res = 0;
        for (int a = 1; a < n; a++) {
            for (int b = 1; b < a && a * a + b * b <= n * n; b++) {
                int c2 = a * a + b * b;
                int c = (int) Math.sqrt(c2);
                if (c * c == c2) {
                    res++;
                }
            }
        }
        return res * 2;
    }

    public int countTriples1(int n) {
        int res = 0;
        for (int u = 3; u * u < n * 2; u += 2) {
            for (int v = 1; v < u && u * u + v * v <= n * 2; v += 2) {
                if (gcd(u, v) == 1) {
                    res += n * 2 / (u * u + v * v);
                }
            }
        }
        return res * 2;
    }

    private int gcd(int a, int b) {
        while (a != 0) {
            int tmp = a;
            a = b % a;
            b = tmp;
        }
        return b;
    }

}
