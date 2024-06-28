package com.sean.leetcode.LeetCode1139;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-17 08:14
 * @Description: https://leetcode.cn/problems/largest-1-bordered-square/
 * 1139. 最大的以 1 为边界的正方形
 * 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，
 * 并返回该子网格中的元素数量。如果不存在，则返回 0。
 */
public class Solution {

    public int largest1BorderedSquare(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] left = new int[m + 1][n + 1];
        int[][] up = new int[m + 1][n + 1];
        int maxBorder = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (grid[i - 1][j - 1] == 1) {
                    left[i][j] = left[i][j - 1] + 1;
                    up[i][j] = up[i - 1][j] + 1;
                    int border = Math.min(left[i][j], up[i][j]);
                    while (left[i - border + 1][j] < border || up[i][j - border + 1] < border) {
                        border--;
                    }
                    maxBorder = Math.max(maxBorder, border);
                }
            }
        }
        return maxBorder * maxBorder;
    }

}
