package com.sean.leetcode.LeetCode518;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 13:38
 * @Description: https://leetcode.cn/problems/coin-change-ii/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 518. 零钱兑换 II
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。
 * 如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。 
 * 题目数据保证结果符合 32 位带符号整数。
 */
public class Solution {

    public int change(int amount, int[] coins) {
        if (coins == null || coins.length == 0) {
            return amount == 0 ? 1 : 0;
        }
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(coins, 0, amount, dp);
    }

    private int process(int[] coins, int i, int cur, int[][] dp) {
        if (dp[i][cur] != -1) {
            return dp[i][cur];
        }
        if (i == coins.length) {
            dp[i][cur] = cur == 0 ? 1 : 0;
            return dp[i][cur];
        }
        int res = 0;
        for (int j = 0; coins[i] * j <= cur; j++) {
            res += process(coins, i + 1, cur - coins[i] * j, dp);
        }
        dp[i][cur] = res;
        return res;
    }

}
