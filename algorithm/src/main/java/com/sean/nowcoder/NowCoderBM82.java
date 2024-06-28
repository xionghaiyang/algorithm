package com.sean.nowcoder;

import java.util.Arrays;

public class NowCoderBM82 {

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][5];
        Arrays.fill(dp[0], -10000);
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return Math.max(dp[n - 1][2], Math.max(0, dp[n - 1][4]));
    }

}
