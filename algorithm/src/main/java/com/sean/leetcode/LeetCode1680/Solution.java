package com.sean.leetcode.LeetCode1680;

/**
 * @Author xionghaiyang
 * @Date 2026-02-28 07:24
 * @Description https://leetcode.cn/problems/concatenation-of-consecutive-binary-numbers
 * 1680. 连接连续二进制数字
 * 给你一个整数 n ，请你将 1 到 n 的二进制表示连接起来，并返回连接结果对应的 十进制 数字对 10^9 + 7 取余的结果。
 * 1 <= n <= 10^5
 */
public class Solution {

    private static int SIZE = 100_001;
    private static long[] memo = new long[SIZE];
    private static int MOD = 1_000_000_007;
    private static boolean initialized = false;

    public void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        int shift = 0;
        long res = 0;
        for (int i = 1; i < SIZE; i++) {
            if ((i & (i - 1)) == 0) {
                shift++;
            }
            res = ((res << shift) | i) % MOD;
            memo[i] = res;
        }
    }

    public int concatenatedBinary(int n) {
        init();
        return (int) memo[n];
    }

}
