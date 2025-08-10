package com.sean.leetcode.LeetCode3643;

/**
 * @Author xionghaiyang
 * @Date 2025-08-10 18:46
 * @Description https://leetcode.cn/problems/flip-square-submatrix-vertically
 * 3643. 垂直翻转子矩阵
 * 给你一个 m x n 的整数矩阵 grid，以及三个整数 x、y 和 k。
 * 整数 x 和 y 表示一个 正方形子矩阵 的左上角下标，整数 k 表示该正方形子矩阵的边长。
 * 你的任务是垂直翻转子矩阵的行顺序。
 * 返回更新后的矩阵。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j] <= 100
 * 0 <= x < m
 * 0 <= y < n
 * 1 <= k <= min(m - x, n - y)
 */
public class Solution {

    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int m = grid.length, n = grid[0].length;
        for (int j = 0; j < k; j++) {
            for (int i1 = 0, i2 = k - 1; i1 < i2; i1++, i2--) {
                swap(grid, x + i1, y + j, x + i2, y + j);
            }
        }
        return grid;
    }

    private void swap(int[][] grid, int x1, int y1, int x2, int y2) {
        int temp = grid[x1][y1];
        grid[x1][y1] = grid[x2][y2];
        grid[x2][y2] = temp;
    }

}
