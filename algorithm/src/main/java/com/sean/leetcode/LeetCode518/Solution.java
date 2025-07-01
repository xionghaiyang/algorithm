package com.sean.leetcode.LeetCode518;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 13:38
 * @Description: https://leetcode.cn/problems/coin-change-ii
 * 518. 零钱兑换 II
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。
 * 如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。 
 * 题目数据保证结果符合 32 位带符号整数。
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * coins 中的所有值 互不相同
 * 0 <= amount <= 5000
 */
public class Solution {

    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(n - 1, amount, coins, dp);
    }

    private int process(int i, int cur, int[] coins, int[][] dp) {
        if (i < 0) {
            return cur == 0 ? 1 : 0;
        }
        if (dp[i][cur] != -1) {
            return dp[i][cur];
        }
        if (cur < coins[i]) {
            dp[i][cur] = process(i - 1, cur, coins, dp);
            return dp[i][cur];
        }
        dp[i][cur] = process(i - 1, cur, coins, dp) + process(i, cur - coins[i], coins, dp);
        return dp[i][cur];
    }

    public int change1(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int c = 0; c <= amount; c++) {
                if (c < coins[i]) {
                    dp[i + 1][c] = dp[i][c];
                } else {
                    dp[i + 1][c] = dp[i][c] + dp[i + 1][c - coins[i]];
                }
            }
        }
        return dp[n][amount];
    }

    public int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int x : coins) {
            for (int c = x; c <= amount; c++) {
                dp[c] += dp[c - x];
            }
        }
        return dp[amount];
    }

}
