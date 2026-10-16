package com.sean.leetcode.LeetCode1155;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-16 17:17
 * @Description: https://leetcode.cn/problems/number-of-dice-rolls-with-target-sum/
 * 1155. 掷骰子等于目标和的方法数
 * 这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。
 * 给定三个整数 n、k 和 target，请返回投掷骰子的所有可能得到的结果（共有 k^n 种方式），使得骰子面朝上的数字总和等于 target。
 * 由于答案可能很大，你需要对 10^9 + 7 取模。
 * 1 <= n, k <= 30
 * 1 <= target <= 1000
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int numRollsToTarget(int n, int k, int target) {
        if (target < n || target > n * k) {
            return 0;
        }
        int[][] memo = new int[n + 1][target - n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return process(memo, n, target - n, k);
    }

    private int process(int[][] memo, int i, int j, int k) {
        if (i == 0) {
            return j == 0 ? 1 : 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = 0;
        for (int x = 0; x < k && x <= j; x++) {
            res = (res + process(memo, i - 1, j - x, k)) % MOD;
        }
        return memo[i][j] = res;
    }

    public int numRollsToTarget1(int n, int k, int target) {
        if (target < n || target > n * k) {
            return 0;
        }
        int[][] f = new int[n + 1][target - n + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target - n; j++) {
                for (int x = 0; x < k && x <= j; x++) {
                    f[i][j] = (f[i][j] + f[i - 1][j - x]) % MOD;
                }
            }
        }
        return f[n][target - n];
    }

    public int numRollsToTarget2(int n, int k, int target) {
        if (target < n || target > n * k) {
            return 0;
        }
        long[] f = new long[target - n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            int max = Math.min(i * (k - 1), target - n);
            for (int j = 1; j <= max; j++) {
                f[j] += f[j - 1];
            }
            for (int j = max; j >= k; j--) {
                f[j] = (f[j] - f[j - k]) % MOD;
            }
        }
        return (int) f[target - n];
    }

}
