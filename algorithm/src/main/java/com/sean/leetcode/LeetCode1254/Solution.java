package com.sean.leetcode.LeetCode1254;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-09 12:40
 * @Description: https://leetcode.cn/problems/number-of-closed-islands/?plan=graph&plan_progress=zq56763
 * 1254. 统计封闭岛屿的数目
 * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。
 * 岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
 * 请返回 封闭岛屿 的数目。
 */
public class Solution {

    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    if (dfs(grid, i, j)) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private boolean dfs(int[][] grid, int row, int col) {
        int m = grid.length;
        int n = grid[0].length;
        grid[row][col] = -1;
        boolean res = true;
        if (row == 0 || row == m - 1 || col == 0 || col == n - 1) {
            res = false;
        }
        if (row - 1 >= 0 && grid[row - 1][col] == 0) {
            res &= dfs(grid, row - 1, col);
        }
        if (row + 1 < m && grid[row + 1][col] == 0) {
            res &= dfs(grid, row + 1, col);
        }
        if (col - 1 >= 0 && grid[row][col - 1] == 0) {
            res &= dfs(grid, row, col - 1);
        }
        if (col + 1 < n && grid[row][col + 1] == 0) {
            res &= dfs(grid, row, col + 1);
        }
        return res;
    }

}
