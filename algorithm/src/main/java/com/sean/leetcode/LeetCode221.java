package com.sean.leetcode;

/**
 * 最大正方形
 * https://leetcode-cn.com/problems/maximal-square/
 */
public class LeetCode221 {

    public static int maximalSquare(char[][] matrix) {
        int hight = matrix.length;
        int width = matrix[0].length;
        int[][] dp = new int[hight + 1][width + 1];
        int maxSide = 0;
        for (int i = 1; i <= hight; i++) {
            for (int j = 1; j <= width; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }

    public static int maximalSquare1(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int maxSide = 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        System.out.println(maximalSquare(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}));
        System.out.println(maximalSquare(new char[][]{{'0','1'},{'1','0'}}));
        System.out.println(maximalSquare(new char[][]{{'0'}}));

        System.out.println(maximalSquare1(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}));
        System.out.println(maximalSquare1(new char[][]{{'0','1'},{'1','0'}}));
        System.out.println(maximalSquare1(new char[][]{{'0'}}));
    }


}
