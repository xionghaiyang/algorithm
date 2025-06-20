package com.sean.leetcode.LeetCode64;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 09:48
 * @Description: https://leetcode.cn/problems/minimum-path-sum
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 200
 */
public class Solution {

    private int[][] grid;
    private int m;
    private int n;

    public int minPathSum(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(0, 0, dp);
    }

    private int process(int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (i == m - 1 && j == n - 1) {
            dp[i][j] = grid[i][j];
            return dp[i][j];
        }
        int res = Integer.MAX_VALUE;
        if (i < m - 1) {
            res = Math.min(res, process(i + 1, j, dp));
        }
        if (j < n - 1) {
            res = Math.min(res, process(i, j + 1, dp));
        }
        dp[i][j] = res + grid[i][j];
        return dp[i][j];
    }

}
