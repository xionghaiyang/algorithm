package com.sean.leetcode.LeetCodeInterview0811;

/**
 * @Author xionghaiyang
 * @Date 2026-03-17 20:21
 * @Description https://leetcode.cn/problems/coin-lcci
 * 面试题 08.11. 硬币
 * 硬币。
 * 给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。
 * (结果可能会很大，你需要将结果模上1000000007)
 * 0 <= n (总金额) <= 1000000
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private int[] coins = {25, 10, 5, 1};

    public int waysToChange(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % MOD;
            }
        }
        return dp[n];
    }

}
