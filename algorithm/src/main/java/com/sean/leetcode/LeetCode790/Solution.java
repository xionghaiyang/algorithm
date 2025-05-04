package com.sean.leetcode.LeetCode790;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-12 22:45
 * @Description: https://leetcode.cn/problems/domino-and-tromino-tiling/
 * 790. 多米诺和托米诺平铺
 * 有两种形状的瓷砖：一种是 2 x 1 的多米诺形，另一种是形如 "L" 的托米诺形。两种形状都可以旋转。
 * 给定整数 n ，返回可以平铺 2 x n 的面板的方法的数量。
 * 返回对 10^9 + 7 取模 的值。
 * 平铺指的是每个正方形都必须有瓷砖覆盖。
 * 两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形。
 * 1 <= n <= 1000
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private int n;

    public int numTilings(int n) {
        this.n = n;
        int[][] dp = new int[n][4];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        return process(0, 0, dp);
    }

    //之前的已经铺好，现在铺第i列，其状态为status
    //一个正方形都没有被覆盖，记为状态0
    //只有上方的正方形被覆盖，记为状态1
    //只有下方的正方形被覆盖，记为状态2
    //上下两个正方形，都被覆盖，记为状态3
    private int process(int i, int status, int[][] dp) {
        if (dp[i][status] != -1) {
            return dp[i][status];
        }
        if (i == n - 1) {
            dp[i][status] = (status == 0 || status == 3) ? 1 : 0;
            return dp[i][status];
        }
        if (status == 0) {
            dp[i][status] = (((process(i + 1, 0, dp) + process(i + 1, 3, dp)) % MOD + process(i + 1, 2, dp)) % MOD + process(i + 1, 1, dp)) % MOD;
        } else if (status == 1) {
            dp[i][status] = (process(i + 1, 2, dp) + process(i + 1, 3, dp)) % MOD;
        } else if (status == 2) {
            dp[i][status] = (process(i + 1, 1, dp) + process(i + 1, 3, dp)) % MOD;
        } else {
            dp[i][status] = process(i + 1, 0, dp);
        }
        return dp[i][status];
    }

    //f[n] = 2 * f[n-1] + f[n-3]
    public int numTilings1(int n) {
        if (n == 1) {
            return 1;
        }
        int MOD = 1_000_000_007;
        long[] f = new long[n + 1];
        f[0] = 1;
        f[1] = 1;
        f[2] = 2;
        for (int i = 3; i <= n; i++) {
            f[i] = (f[i - 1] * 2 + f[i - 3]) % MOD;
        }
        return (int) f[n];
    }

    public int numTilings2(int n) {
        if (n == 1) {
            return 1;
        }
        int MOD = 1_000_000_007;
        long a = 1, b = 1, c = 2;
        for (int i = 3; i <= n; i++) {
            long f = (c * 2 + a) % MOD;
            a = b;
            b = c;
            c = f;
        }
        return (int) c;
    }

    //private static final int MOD = 1_000_000_007;
    private static final long[] f = new long[1001];

    static {
        f[0] = 1;
        f[1] = 1;
        f[2] = 2;
        for (int i = 3; i < f.length; i++) {
            f[i] = (f[i - 1] * 2 + f[i - 3]) % MOD;
        }
    }

    public int numTilings3(int n) {
        return (int) f[n];
    }

    //private static final int MOD = 1_000_000_007;

    public int numTilings4(int n) {
        if (n == 1) {
            return 1;
        }
        long[][] f2 = {{2}, {1}, {1}};
        long[][] m = {
                {2, 0, 1},
                {1, 0, 0},
                {0, 1, 0}
        };
        long[][] fn = powMul(m, n - 2, f2);
        return (int) fn[0][0];
    }

    //a^n * f
    private long[][] powMul(long[][] a, int n, long[][] f) {
        long[][] res = f;
        while (n > 0) {
            if ((n & 1) != 0) {
                res = mul(a, res);
            }
            a = mul(a, a);
            n >>= 1;
        }
        return res;
    }

    private long[][] mul(long[][] a, long[][] b) {
        long[][] res = new long[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                res[i][j] = 0;
                for (int k = 0; k < a[0].length; k++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return res;
    }

}
