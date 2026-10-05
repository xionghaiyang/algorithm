package com.sean.leetcode.LeetCode2684;

/**
 * @Author: xionghaiyang
 * @Date: 2024-03-16 18:41
 * @Description: https://leetcode.cn/problems/maximum-number-of-moves-in-a-grid
 * 2684. 矩阵中移动的最大次数
 * 给你一个下标从 0 开始、大小为 m x n 的矩阵 grid ，矩阵由若干 正 整数组成。
 * 你可以从矩阵第一列中的 任一 单元格出发，按以下方式遍历 grid ：
 * 从单元格 (row, col) 可以移动到 (row - 1, col + 1)、(row, col + 1) 和 (row + 1, col + 1) 三个单元格中任一满足值 严格 大于当前单元格的单元格。
 * 返回你在矩阵中能够 移动 的 最大 次数。
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 10^5
 * 1 <= grid[i][j] <= 10^6
 */
public class Solution {

    private int res = 0;
    private int m;
    private int n;

    public int maxMoves(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);
        }
        return res;
    }

    private void dfs(int[][] grid, int i, int j) {
        res = Math.max(res, j);
        if (res == n - 1) {
            return;
        }
        for (int k = Math.max(i - 1, 0); k < Math.min(i + 2, m); k++) {
            if (grid[k][j + 1] > grid[i][j]) {
                dfs(grid, k, j + 1);
            }
        }
        grid[i][j] = 0;
    }

}
