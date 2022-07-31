package com.sean.nowcoder;

public class NowCoderBM68 {

    public static int minPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = matrix[i][0] + dp[i - 1][0];
        }
        for (int j = 1; j < m; j++) {
            dp[0][j] = matrix[0][j] + dp[0][j - 1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = matrix[i][j] + (dp[i - 1][j] > dp[i][j - 1] ? dp[i][j - 1] : dp[i - 1][j]);
            }
        }
        return dp[n - 1][m - 1];
    }

}
