package com.sean.leetcode.LeetCode840;

/**
 * @Author xionghaiyang
 * @Date 2025-12-30 08:24
 * @Description https://leetcode.cn/problems/magic-squares-in-grid
 * 840. 矩阵中的幻方
 * 3 x 3 的幻方是一个填充有 从 1 到 9  的不同数字的 3 x 3 矩阵，其中每行，每列以及两条对角线上的各数之和都相等。
 * 给定一个由整数组成的row x col 的 grid，其中有多少个 3 × 3 的 “幻方” 子矩阵？
 * 注意：虽然幻方只能包含 1 到 9 的数字，但 grid 可以包含最多15的数字。
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 10
 * 0 <= grid[i][j] <= 15
 */
public class Solution {

    public int numMagicSquaresInside(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == 5 && check(grid, i, j)) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean check(int[][] grid, int x, int y) {
        boolean[] has = new boolean[10];
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (grid[i][j] > 9 || grid[i][j] == 0 || has[grid[i][j]]) {
                    return false;
                } else {
                    has[grid[i][j]] = true;
                }
            }
        }
        for (int d = -1; d <= 1; d++) {
            if (grid[x + d][y - 1] + grid[x + d][y] + grid[x + d][y + 1] != 15) {
                return false;
            }
            if (grid[x - 1][y + d] + grid[x][y + d] + grid[x + 1][y + d] != 15) {
                return false;
            }
        }
        if (grid[x - 1][y - 1] + grid[x][y] + grid[x + 1][y + 1] != 15 || grid[x + 1][y - 1] + grid[x][y] + grid[x - 1][y + 1] != 15) {
            return false;
        }
        return true;
    }

}
