package com.sean.leetcode.LeetCode3882;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-02 18:23
 * @Description: https://leetcode.cn/problems/minimum-xor-path-in-a-grid
 * 3882. 网格图中最小异或路径
 * 给你一个大小为 m * n 的二维整数数组 grid。
 * 你从 左上角 的单元格 (0, 0) 出发，想要到达 右下角 的单元格 (m - 1, n - 1)。
 * 在每一步中，你 可以 向右或向下 移动。
 * 路径的 代价 定义为该路径上所有单元格（包括 起点和终点）的值的 按位异或。
 * 返回从 (0, 0) 到 (m - 1, n - 1) 的所有有效路径中 最小 的可能异或值。
 * 1 <= m == grid.length <= 1000
 * 1 <= n == grid[i].length <= 1000
 * m * n <= 1000
 * 0 <= grid[i][j] <= 1023​
 */
public class Solution {

    private int res = Integer.MAX_VALUE;

    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int xor = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                xor |= grid[i][j];
            }
        }
        boolean[][][] vis = new boolean[m][n][xor + 1];
        process(grid, vis, m - 1, n - 1, 0);
        return res;
    }

    private void process(int[][] grid, boolean[][][] vis, int i, int j, int xor) {
        if (res == 0 || i < 0 || j < 0 || vis[i][j][xor]) {
            return;
        }
        vis[i][j][xor] = true;
        xor ^= grid[i][j];
        if (i == 0 && j == 0) {
            res = Math.min(res, xor);
            return;
        }
        process(grid, vis, i - 1, j, xor);
        process(grid, vis, i, j - 1, xor);
    }

}
