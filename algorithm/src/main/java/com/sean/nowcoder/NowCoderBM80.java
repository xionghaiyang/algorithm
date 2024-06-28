package com.sean.nowcoder;

public class NowCoderBM80 {

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        //dp[i][0]表示某天不持股到该天为止的最大收益，dp[i][1]表示某天持股，到该天为止的最大收益
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(-prices[i], dp[i - 1][1]);
        }
        return dp[n - 1][0];
    }

}
