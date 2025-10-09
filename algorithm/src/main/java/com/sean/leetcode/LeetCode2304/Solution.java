package com.sean.leetcode.LeetCode2304;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-10-09 20:35
 * @Description https://leetcode.cn/problems/minimum-path-cost-in-a-grid
 * 2304. 网格中的最小路径代价
 * 给你一个下标从 0 开始的整数矩阵 grid ，矩阵大小为 m x n ，由从 0 到 m * n - 1 的不同整数组成。
 * 你可以在此矩阵中，从一个单元格移动到 下一行 的任何其他单元格。
 * 如果你位于单元格 (x, y) ，且满足 x < m - 1 ，你可以移动到 (x + 1, 0), (x + 1, 1), ..., (x + 1, n - 1) 中的任何一个单元格。
 * 注意： 在最后一行中的单元格不能触发移动。
 * 每次可能的移动都需要付出对应的代价，代价用一个下标从 0 开始的二维数组 moveCost 表示，该数组大小为 (m * n) x n ，其中 moveCost[i][j] 是从值为 i 的单元格移动到下一行第 j 列单元格的代价。
 * 从 grid 最后一行的单元格移动的代价可以忽略。
 * grid 一条路径的代价是：所有路径经过的单元格的 值之和 加上 所有移动的 代价之和 。
 * 从 第一行 任意单元格出发，返回到达 最后一行 任意单元格的最小路径代价。
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 50
 * grid 由从 0 到 m * n - 1 的不同整数组成
 * moveCost.length == m * n
 * moveCost[i].length == n
 * 1 <= moveCost[i][j] <= 100
 */
public class Solution {

    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length, n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            res = Math.min(res, dfs(grid, moveCost, memo, 0, j));
        }
        return res;
    }

    private int dfs(int[][] grid, int[][] moveCost, int[][] memo, int i, int j) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (i == grid.length - 1) {
            return memo[i][j] = grid[i][j];
        }
        int res = Integer.MAX_VALUE;
        for (int k = 0; k < grid[0].length; k++) {
            res = Math.min(res, dfs(grid, moveCost, memo, i + 1, k) + moveCost[grid[i][j]][k]);
        }
        return memo[i][j] = res + grid[i][j];
    }

}
