package com.sean.leetcode.LeetCode1416;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-06 17:31
 * @Description: https://leetcode.cn/problems/restore-the-array
 * 1416. 恢复数组
 * 某个程序本来应该输出一个整数数组。
 * 但是这个程序忘记输出空格了以致输出了一个数字字符串，我们所知道的信息只有：数组中所有整数都在 [1, k] 之间，且数组中的数字都没有前导 0 。
 * 给你字符串 s 和整数 k 。
 * 可能会有多种不同的数组恢复结果。
 * 按照上述程序，请你返回所有可能输出字符串 s 的数组方案数。
 * 由于数组方案数可能会很大，请你返回它对 10^9 + 7 取余 后的结果。
 * 1 <= s.length <= 10^5.
 * s 只包含数字且不包含前导 0 。
 * 1 <= k <= 10^9.
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int numberOfArrays(String s, int k) {
        int n = s.length();
        char[] str = s.toCharArray();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            long num = 0, base = 1;
            for (int j = i - 1; j >= 0 && j >= i - 10; j--) {
                num += (str[j] - '0') * base;
                if (num > k) {
                    break;
                }
                if (str[j] != '0') {
                    dp[i] = (dp[i] + dp[j]) % MOD;
                }
                base *= 10;
            }
        }
        return (int) dp[n];
    }

}
