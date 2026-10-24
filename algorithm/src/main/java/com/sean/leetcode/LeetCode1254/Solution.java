package com.sean.leetcode.LeetCode1254;

/**
 * @Author: xionghaiyang
 * @Date: 2022-09-09 12:40
 * @Description: https://leetcode.cn/problems/number-of-closed-islands
 * 1254. 统计封闭岛屿的数目
 * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。
 * 岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
 * 请返回 封闭岛屿 的数目。
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 */
public class Solution {

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && dfs(grid, i, j)) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean dfs(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        grid[x][y] = -1;
        boolean res = true;
        if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
            res = false;
        }
        for (int[] dir : DIRS) {
            int nx = x + dir[0], ny = y + dir[1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 0) {
                res &= dfs(grid, nx, ny);
            }
        }
        return res;
    }

}
