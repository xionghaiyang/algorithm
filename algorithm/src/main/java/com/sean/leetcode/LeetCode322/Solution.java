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

    private int process(int[] coins, int i, int cur, int[][] dp) {
        if (dp[i][cur] != -1) {
            return dp[i][cur];
        }
        if (i == coins.length) {
            dp[i][cur] = cur == 0 ? 0 : Integer.MAX_VALUE;
            return dp[i][cur];
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; coins[i] * j <= cur; j++) {
            int res1 = process(coins, i + 1, cur - coins[i] * j, dp);
            if (res1 != Integer.MAX_VALUE) {
                res = Math.min(res, res1 + j);
            }
        }
        dp[i][cur] = res;
        return res;
    }

}
