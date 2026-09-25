package com.sean.leetcode.LeetCode64;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
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

    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return process(grid, memo, 0, 0);
    }

    private int process(int[][] grid, int[][] memo, int i, int j) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int m = grid.length, n = grid[0].length;
        if (i == m - 1 && j == n - 1) {
            return memo[i][j] = grid[i][j];
        }
        int res = Integer.MAX_VALUE;
        if (i < m - 1) {
            res = Math.min(res, process(grid, memo, i + 1, j) + grid[i][j]);
        }
        if (j < n - 1) {
            res = Math.min(res, process(grid, memo, i, j + 1) + grid[i][j]);
        }
        return memo[i][j] = res;
    }

}
