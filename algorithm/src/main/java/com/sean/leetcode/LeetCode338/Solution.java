package com.sean.leetcode.LeetCode338;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-08 09:59
 * @Description: https://leetcode.cn/problems/counting-bits
 * 338. 比特位计数
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 * 不要使用内置函数来解决（例如，C++ 中的 __builtin_popcount）。
 * 0 <= n <= 10^5
 */
public class Solution {

    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 1, highBit = 0; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            res[i] = res[i - highBit] + 1;
        }
        return res;
    }

    public int[] countBits1(int n) {
        int[] res = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }

}
