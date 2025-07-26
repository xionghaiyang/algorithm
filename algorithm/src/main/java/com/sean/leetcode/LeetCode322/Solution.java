package com.sean.leetcode.LeetCode322;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 13:18
 * @Description: https://leetcode.cn/problems/coin-change
 * 322. 零钱兑换
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 */
public class Solution {

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        int res = process(coins, 0, amount, dp);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int process(int[] coins, int i, int amount, int[][] dp) {
        if (dp[i][amount] != -1) {
            return dp[i][amount];
        }
        if (i == coins.length) {
            dp[i][amount] = amount == 0 ? 0 : Integer.MAX_VALUE;
            return dp[i][amount];
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; coins[i] * j <= amount; j++) {
            int res1 = process(coins, i + 1, amount - coins[i] * j, dp);
            if (res1 != Integer.MAX_VALUE) {
                res = Math.min(res, res1 + j);
            }
        }
        dp[i][amount] = res;
        return res;
    }

    public int coinChange1(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        int res = process1(coins, 0, amount, dp);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int process1(int[] coins, int i, int amount, int[][] dp) {
        if (dp[i][amount] != -1) {
            return dp[i][amount];
        }
        if (i == coins.length) {
            dp[i][amount] = amount == 0 ? 0 : Integer.MAX_VALUE;
            return dp[i][amount];
        }
        int res = process1(coins, i + 1, amount, dp);
        if (coins[i] <= amount) {
            int res2 = process1(coins, i, amount - coins[i], dp);
            if (res2 != Integer.MAX_VALUE) {
                res = Math.min(res, res2 + 1);
            }
        }
        dp[i][amount] = res;
        return res;
    }

    public int coinChange2(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int j = 1; j <= amount; j++) {
            dp[n][j] = Integer.MAX_VALUE;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = dp[i + 1][j];
                if (coins[i] <= j && dp[i][j - coins[i]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - coins[i]] + 1);
                }
            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }

}
