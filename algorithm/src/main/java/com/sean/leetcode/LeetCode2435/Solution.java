package com.sean.leetcode.LeetCode2435;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-11-26 11:47
 * @Description https://leetcode.cn/problems/paths-in-matrix-whose-sum-is-divisible-by-k
 * 2435. 矩阵中和能被 K 整除的路径
 * 给你一个下标从 0 开始的 m x n 整数矩阵 grid 和一个整数 k 。
 * 你从起点 (0, 0) 出发，每一步只能往 下 或者往 右 ，你想要到达终点 (m - 1, n - 1) 。
 * 请你返回路径和能被 k 整除的路径数目，由于答案可能很大，返回答案对 10^9 + 7 取余 的结果。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 5 * 10^4
 * 1 <= m * n <= 5 * 10^4
 * 0 <= grid[i][j] <= 100
 * 1 <= k <= 50
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private int m;
    private int n;
    private int k;

    public int numberOfPaths(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        this.k = k;
        long[][][] dp = new long[m][n][k];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return (int) (process(grid, dp, 0, 0, 0) % MOD);
    }

    private long process(int[][] grid, long[][][] dp, int x, int y, int z) {
        if (x >= m || y >= n) {
            return 0;
        }
        if (dp[x][y][z] != -1) {
            return dp[x][y][z];
        }
        if (x == m - 1 && y == n - 1) {
            return dp[x][y][z] = (z + grid[x][y]) % k == 0 ? 1 : 0;
        }
        return dp[x][y][z] = (process(grid, dp, x + 1, y, (z + grid[x][y]) % k) + process(grid, dp, x, y + 1, (z + grid[x][y]) % k)) % MOD;
    }

}
