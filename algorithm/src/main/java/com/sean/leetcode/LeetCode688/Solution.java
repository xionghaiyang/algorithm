package com.sean.leetcode.LeetCode688;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-01 17:55
 * @Description: https://leetcode.cn/problems/knight-probability-in-chessboard/description/
 * 688. 骑士在棋盘上的概率
 * 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。
 * 行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 * 象棋骑士有8种可能的走法，如下图所示。
 * 每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 */
public class Solution {

    public double knightProbability(int n, int k, int row, int column) {
        int[][] dirs = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};
        //dp[step][i][j]表示骑士从棋盘上的点(i,j)出发，走了step步时仍然留在棋盘上的概率
        double[][][] dp = new double[k + 1][n][n];
        for (int step = 0; step <= k; step++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (step == 0) {
                        dp[step][i][j] = 1;
                    } else {
                        for (int[] dir : dirs) {
                            int ni = i + dir[0];
                            int nj = j + dir[1];
                            if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                                dp[step][i][j] += dp[step - 1][ni][nj] / 8;
                            }
                        }
                    }
                }
            }
        }
        return dp[k][row][column];
    }

}
