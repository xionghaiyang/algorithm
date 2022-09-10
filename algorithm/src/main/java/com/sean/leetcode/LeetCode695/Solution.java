package com.sean.leetcode.LeetCode695;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-09 12:19
 * @Description: https://leetcode.cn/problems/max-area-of-island/
 * 695. 岛屿的最大面积
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。
 * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 */
public class Solution {

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int row, int col) {
        int m = grid.length;
        int n = grid[0].length;
        grid[row][col] = 0;
        int res = 1;
        if (row - 1 >= 0 && grid[row - 1][col] == 1) {
            res += dfs(grid, row - 1, col);
        }
        if (row + 1 < m && grid[row + 1][col] == 1) {
            res += dfs(grid, row + 1, col);
        }
        if (col - 1 >= 0 && grid[row][col - 1] == 1) {
            res += dfs(grid, row, col - 1);
        }
        if (col + 1 < n && grid[row][col + 1] == 1) {
            res += dfs(grid, row, col + 1);
        }
        return res;
    }

}
