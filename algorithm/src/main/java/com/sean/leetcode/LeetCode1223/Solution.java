package com.sean.leetcode.LeetCode1223;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-10 09:16
 * @Description: https://leetcode.cn/problems/dice-roll-simulation/
 * 1223. 掷骰子模拟
 * 有一个骰子模拟器会每次投掷的时候生成一个 1 到 6 的随机数。
 * 不过我们在使用它时有个约束，就是使得投掷骰子时，连续 掷出数字 i 的次数不能超过 rollMax[i]（i 从 1 开始编号）。
 * 现在，给你一个整数数组 rollMax 和一个整数 n，请你来计算掷 n 次骰子可得到的不同点数序列的数量。
 * 假如两个序列中至少存在一个元素不同，就认为这两个序列是不同的。
 * 由于答案可能很大，所以请返回 模 10^9 + 7 之后的结果。
 */
public class Solution {

    //超时
    private final int MOD = 1000000007;

    public int dieSimulator1(int n, int[] rollMax) {
        return process(0, n, -1, 0, rollMax);
    }

    private int process(int i, int n, int pre, int cnt, int[] rollMax) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < 6; j++) {
            int count = j == pre ? cnt + 1 : 1;
            if (count <= rollMax[j]) {
                res = (res + process(i + 1, n, j, count, rollMax)) % MOD;
            }
        }
        return res;
    }

    public int dieSimulator2(int n, int[] rollMax) {
        int mod = 1000000007;
        int[][][] dp = new int[n + 1][6][16];
        for (int j = 0; j < 6; j++) {
            dp[1][j][1] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 1; k <= rollMax[j]; k++) {
                    for (int p = 0; p < 6; p++) {
                        if (p != j) {
                            dp[i][p][1] = (dp[i][p][1] + dp[i - 1][j][k]) % mod;
                        } else if (k + 1 <= rollMax[j]) {
                            dp[i][p][k + 1] = (dp[i][p][k + 1] + dp[i - 1][j][k]) % mod;
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int j = 0; j < 6; j++) {
            for (int k = 1; k <= rollMax[j]; k++) {
                res = (res + dp[n][j][k]) % mod;
            }
        }
        return res;
    }

    public int dieSimulator3(int n, int[] rollMax) {
        int mod = 1000000007;
        int[][][] dp = new int[2][6][16];
        for (int j = 0; j < 6; j++) {
            dp[1][j][1] = 1;
        }
        for (int i = 2; i <= n; i++) {
            int t = i & 1;
            for (int j = 0; j < 6; j++) {
                Arrays.fill(dp[t][j], 0);
            }
            for (int j = 0; j < 6; j++) {
                for (int k = 1; k <= rollMax[j]; k++) {
                    for (int p = 0; p < 6; p++) {
                        if (p != j) {
                            dp[t][p][1] = (dp[t][p][1] + dp[t ^ 1][j][k]) % mod;
                        } else if (k + 1 <= rollMax[j]) {
                            dp[t][p][k + 1] = (dp[t][p][k + 1] + dp[t ^ 1][j][k]) % mod;
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int j = 0; j < 6; j++) {
            for (int k = 1; k <= rollMax[j]; k++) {
                res = (res + dp[n & 1][j][k]) % mod;
            }
        }
        return res;
    }

    public int dieSimulator(int n, int[] rollMax) {
        int mod = 1000000007;
        int[][] dp = new int[n + 1][6];
        int[] sum = new int[n + 1];
        sum[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                int pos = Math.max(i - rollMax[j] - 1, 0);
                int sub = ((sum[pos] - dp[pos][j]) % mod + mod) % mod;
                dp[i][j] = ((sum[i - 1] - sub) % mod + mod) % mod;
                if (i <= rollMax[j]) {
                    dp[i][j] = (dp[i][j] + 1) % mod;
                }
                sum[i] = (sum[i] + dp[i][j]) % mod;
            }
        }
        return sum[n];
    }

}
