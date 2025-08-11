package com.sean.leetcode.LeetCode2787;

/**
 * @Author xionghaiyang
 * @Date 2025-08-12 07:02
 * @Description https://leetcode.cn/problems/ways-to-express-an-integer-as-sum-of-powers
 * 2787. 将一个数字表示成幂的和的方案数
 * 给你两个 正 整数 n 和 x 。
 * 请你返回将 n 表示成一些 互不相同 正整数的 x 次幂之和的方案数。
 * 换句话说，你需要返回互不相同整数 [n1, n2, ..., nk] 的集合数目，满足 n = n1^x + n2^x + ... + nk^x 。
 * 由于答案可能非常大，请你将它对 10^9 + 7 取余后返回。
 * 比方说，n = 160 且 x = 3 ，一个表示 n 的方法是 n = 2^3 + 3^3 + 5^3 。
 * 1 <= n <= 300
 * 1 <= x <= 5
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int numberOfWays(int n, int x) {
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 1; ; i++) {
            int val = power(i, x);
            if (val > n) {
                break;
            }
            for (int j = n; j >= val; j--) {
                //dp[j] = (dp[j] + dp[j - val]) % MOD;
                dp[j] += dp[j - val];
            }
        }
        return (int) (dp[n] % MOD);
    }

    //x^y
    private int power(int x, int y) {
        int res = 1;
        while (y > 0) {
            if ((y & 1) != 0) {
                res *= x;
            }
            x *= x;
            y >>= 1;
        }
        return res;
    }

}
