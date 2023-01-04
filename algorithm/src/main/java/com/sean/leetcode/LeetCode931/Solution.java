package com.sean.leetcode.LeetCode931;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-28 16:46
 * @Description: https://leetcode.cn/problems/minimum-falling-path-sum/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 931. 下降路径最小和
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。
 * 在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
 * 具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 */
public class Solution {

    public int minFallingPathSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int n = matrix.length;
        int res = Integer.MAX_VALUE;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int j = 0; j < n; j++) {
            res = Math.min(res, process(matrix, 0, j, dp));
        }
        return res;
    }

    private int process(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int n = matrix.length;
        if (i == n - 1) {
            dp[i][j] = matrix[i][j];
            return matrix[i][j];
        }
        int res = matrix[i][j] + process(matrix, i + 1, j, dp);
        if (j - 1 >= 0) {
            res = Math.min(res, matrix[i][j] + process(matrix, i + 1, j - 1, dp));
        }
        if (j + 1 < n) {
            res = Math.min(res, matrix[i][j] + process(matrix, i + 1, j + 1, dp));
        }
        dp[i][j] = res;
        return res;
    }

}
