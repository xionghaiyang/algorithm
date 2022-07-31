package com.sean.nowcoder;

public class NowCoderBM61 {

    private static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int m, n;

    public static int dfs(int[][] martrix, int[][] dp, int i, int j) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        dp[i][j]++;
        for (int k = 0; k < 4; k++) {
            int nexti = i + dirs[k][0];
            int nextj = j + dirs[k][1];
            if (nexti >= 0 && nexti < m && nextj >= 0 && nextj < n && martrix[nexti][nextj] > martrix[i][j]) {
                dp[i][j] = Math.max(dp[i][j], dfs(martrix, dp, nexti, nextj) + 1);
            }
        }
        return dp[i][j];
    }

    public static int solve(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int res = 0;
        m = matrix.length;
        n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, dfs(matrix, dp, i, j));
            }
        }
        return res;
    }

}
