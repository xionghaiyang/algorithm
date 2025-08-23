package com.sean.leetcode.LeetCode3197;

/**
 * @Author xionghaiyang
 * @Date 2025-08-23 07:19
 * @Description https://leetcode.cn/problems/find-the-minimum-area-to-cover-all-ones-ii
 * 3197. 包含所有 1 的最小矩形面积 II
 * 给你一个二维 二进制 数组 grid。
 * 你需要找到 3 个 不重叠、面积 非零 、边在水平方向和竖直方向上的矩形，并且满足 grid 中所有的 1 都在这些矩形的内部。
 * 返回这些矩形面积之和的 最小 可能值。
 * 注意，这些矩形可以相接。
 * 1 <= grid.length, grid[i].length <= 30
 * grid[i][j] 是 0 或 1。
 * 输入保证 grid 中至少有三个 1 。
 */
public class Solution {

    public int minimumSum(int[][] grid) {
        return Math.min(solve(grid), solve(rotate(grid)));
    }

    private int solve(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = Integer.MAX_VALUE;
        if (m >= 3) {
            for (int i = 1; i < m; i++) {
                for (int j = i + 1; j < m; j++) {
                    int area = minimumArea(grid, 0, i, 0, n);
                    area += minimumArea(grid, i, j, 0, n);
                    area += minimumArea(grid, j, m, 0, n);
                    res = Math.min(res, area);
                }
            }
        }
        if (m >= 2 && n >= 2) {
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    int area = minimumArea(grid, 0, i, 0, n);
                    area += minimumArea(grid, i, m, 0, j);
                    area += minimumArea(grid, i, m, j, n);
                    res = Math.min(res, area);

                    area = minimumArea(grid, 0, i, 0, j);
                    area += minimumArea(grid, 0, i, j, n);
                    area += minimumArea(grid, i, m, 0, n);
                    res = Math.min(res, area);
                }
            }
        }
        return res;
    }

    private int minimumArea(int[][] grid, int u, int d, int l, int r) {
        int left = r, right = 0, top = d, bottom = 0;
        for (int i = u; i < d; i++) {
            for (int j = l; j < r; j++) {
                if (grid[i][j] == 1) {
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                    top = Math.min(top, i);
                    bottom = i;
                }
            }
        }
        return (right - left + 1) * (bottom - top + 1);
    }

    private int[][] rotate(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[j][m - 1 - i] = grid[i][j];
            }
        }
        return res;
    }

}
