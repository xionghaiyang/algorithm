package com.sean.leetcode;


public class LeetCode1219 {

    int[][] grid;
    int rows;
    int columns;
    int res = 0;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int getMaximumGold(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.columns = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] > 0) {
                    dfs(i, j, 0);
                }
            }
        }
        return res;
    }

    public void dfs(int x, int y, int gold) {
        int t = grid[x][y];
        gold += t;
        res = Math.max(res, gold);
        grid[x][y] = 0;
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < rows && ny >= 0 && ny < columns && grid[nx][ny] > 0) {
                dfs(nx, ny, gold);
            }
        }
        grid[x][y] = t;
    }

}
