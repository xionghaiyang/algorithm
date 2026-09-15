package com.sean.leetcode.LeetCode313;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-15 15:04
 * @Description: https://leetcode.cn/problems/super-ugly-number
 * 313. 超级丑数
 * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
 * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
 * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
 * 1 <= n <= 10^5
 * 1 <= primes.length <= 100
 * 2 <= primes[i] <= 1000
 * 题目数据 保证 primes[i] 是一个质数
 * primes 中的所有值都 互不相同 ，且按 递增顺序 排列
 */
public class Solution {

    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        dp[0] = 1;
        int m = primes.length;
        int[] index = new int[m];
        long[] candidate = new long[m];
        for (int j = 0; j < m; j++) {
            candidate[j] = (long) primes[j] * dp[index[j]];
        }
        for (int i = 1; i < n; i++) {
            long min = candidate[0];
            for (int j = 1; j < m; j++) {
                min = Math.min(min, candidate[j]);
            }
            dp[i] = (int) min;
            for (int j = 0; j < m; j++) {
                if (candidate[j] == min) {
                    index[j]++;
                    candidate[j] = (long) primes[j] * dp[index[j]];
                }
            }
        }
        return dp[n - 1];
    }

}
