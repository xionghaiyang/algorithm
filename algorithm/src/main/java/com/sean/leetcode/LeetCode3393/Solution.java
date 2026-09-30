package com.sean.leetcode.LeetCode3393;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-30 17:31
 * @Description: https://leetcode.cn/problems/count-paths-with-the-given-xor-value
 * 3393. 统计异或值为给定值的路径数目
 * 给你一个大小为 m x n 的二维整数数组 grid 和一个整数 k 。
 * 你的任务是统计满足以下 条件 且从左上格子 (0, 0) 出发到达右下格子 (m - 1, n - 1) 的路径数目：
 * 每一步你可以向右或者向下走，也就是如果格子存在的话，可以从格子 (i, j) 走到格子 (i, j + 1) 或者格子 (i + 1, j) 。
 * 路径上经过的所有数字 XOR 异或值必须 等于 k 。
 * 请你返回满足上述条件的路径总数。
 * 由于答案可能很大，请你将答案对 10^9 + 7 取余 后返回。
 * 1 <= m == grid.length <= 300
 * 1 <= n == grid[r].length <= 300
 * 0 <= grid[r][c] < 16
 * 0 <= k < 16
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int countPathsWithXorValue(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, grid[i][j]);
            }
        }
        int u = 1 << (32 - Integer.numberOfLeadingZeros(max));
        if (k >= u) {
            return 0;
        }
        long[][][] memo = new long[m][n][16];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return (int) process(grid, memo, 0, 0, k);
    }

    private long process(int[][] grid, long[][][] memo, int i, int j, int k) {
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        int m = grid.length, n = grid[0].length;
        if (i == m - 1 && j == n - 1) {
            return memo[i][j][k] = (k ^ grid[i][j]) == 0 ? 1 : 0;
        }
        long res = 0;
        if (i + 1 < m) {
            res = (res + process(grid, memo, i + 1, j, k ^ grid[i][j])) % MOD;
        }
        if (j + 1 < n) {
            res = (res + process(grid, memo, i, j + 1, k ^ grid[i][j])) % MOD;
        }
        return memo[i][j][k] = res;
    }

}
