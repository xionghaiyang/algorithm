package com.sean.leetcode.LeetCode1289;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-02 17:59
 * @Description: https://leetcode.cn/problems/minimum-falling-path-sum-ii
 * 1289. 下降路径最小和 II
 * 给你一个 n x n 整数矩阵 grid ，请你返回 非零偏移下降路径 数字和的最小值。
 * 非零偏移下降路径 定义为：从 grid 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。
 * n == grid.length == grid[i].length
 * 1 <= n <= 200
 * -99 <= grid[i][j] <= 99
 */
public class Solution {

    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            res = Math.min(res, process(grid, memo, 0, j));
        }
        return res;
    }

    private int process(int[][] grid, int[][] memo, int i, int j) {
        if (memo[i][j] != Integer.MAX_VALUE) {
            return memo[i][j];
        }
        int n = grid.length;
        if (i == n - 1) {
            return memo[i][j] = grid[i][j];
        }
        int res = Integer.MAX_VALUE;
        for (int k = 0; k < n; k++) {
            if (k != j) {
                res = Math.min(res, process(grid, memo, i + 1, k));
            }
        }
        return memo[i][j] = res + grid[i][j];
    }

}
