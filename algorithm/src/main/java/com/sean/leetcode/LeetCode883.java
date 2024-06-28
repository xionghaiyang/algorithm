package com.sean.leetcode;

public class LeetCode883 {

    public static int projectionArea(int[][] grid) {
        int n = grid.length;
        int xyArea = 0, yzArez = 0, xzArea = 0;
        for (int i = 0; i < n; i++) {
            int yzHeight = 0, xzHeight = 0;
            for (int j = 0; j < n; j++) {
                xyArea += grid[i][j] > 0 ? 1 : 0;
                yzHeight = Math.max(yzHeight, grid[i][j]);
                xzHeight = Math.max(xzHeight, grid[j][i]);
            }
            yzArez += yzHeight;
            xzArea += xzHeight;
        }
        return xyArea + yzArez + xzArea;
    }

}
