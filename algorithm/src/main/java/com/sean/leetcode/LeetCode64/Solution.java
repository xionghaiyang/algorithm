package com.sean.leetcode.LeetCode64;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 09:48
 * @Description: https://leetcode.cn/problems/minimum-path-sum/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 */
public class Solution {

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(grid, m - 1, n - 1, dp);
    }

    private int process(int[][] grid, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (i == 0 && j == 0) {
            dp[i][j] = grid[i][j];
            return dp[i][j];
        }
        int res = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            res = Math.min(res, grid[i][j] + process(grid, i - 1, j, dp));
        }
        if (j - 1 >= 0) {
            res = Math.min(res, grid[i][j] + process(grid, i, j - 1, dp));
        }
        dp[i][j] = res;
        return res;
    }

}
