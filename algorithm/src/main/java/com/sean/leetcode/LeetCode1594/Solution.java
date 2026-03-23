package com.sean.leetcode.LeetCode1594;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-03-23 08:36
 * @Description https://leetcode.cn/problems/maximum-non-negative-product-in-a-matrix
 * 1594. 矩阵的最大非负积
 * 给你一个大小为 m x n 的矩阵 grid 。
 * 最初，你位于左上角 (0, 0) ，每一步，你可以在矩阵中 向右 或 向下 移动。
 * 在从左上角 (0, 0) 开始到右下角 (m - 1, n - 1) 结束的所有路径中，找出具有 最大非负积 的路径。
 * 路径的积是沿路径访问的单元格中所有整数的乘积。
 * 返回 最大非负积 对 10^9 + 7 取余 的结果。
 * 如果最大积为 负数 ，则返回 -1 。
 * 注意，取余是在得到最大积之后执行的。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 15
 * -4 <= grid[i][j] <= 4
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][][] memo = new long[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], Long.MIN_VALUE);
            }
        }
        long res = process(grid, memo, m - 1, n - 1)[1];
        return res < 0 ? -1 : (int) (res % MOD);
    }

    private long[] process(int[][] grid, long[][][] memo, int i, int j) {
        long[] res = memo[i][j];
        if (res[0] != Long.MIN_VALUE) {
            return res;
        }
        long x = grid[i][j];
        if (i == 0 && j == 0) {
            return new long[]{x, x};
        }
        long resMin = Long.MAX_VALUE, resMax = Long.MIN_VALUE;
        if (i > 0) {
            long[] res1 = process(grid, memo, i - 1, j);
            long min = res1[0], max = res1[1];
            resMin = Math.min(resMin, Math.min(min * x, max * x));
            resMax = Math.max(resMax, Math.max(min * x, max * x));
        }
        if (j > 0) {
            long[] res2 = process(grid, memo, i, j - 1);
            long min = res2[0], max = res2[1];
            resMin = Math.min(resMin, Math.min(min * x, max * x));
            resMax = Math.max(resMax, Math.max(min * x, max * x));
        }
        res[0] = resMin;
        res[1] = resMax;
        return res;
    }

    public int maxProductPath1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][][] memo = new long[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], Long.MIN_VALUE);
            }
        }
        long res = process(grid, memo, m - 1, n - 1)[1];
        return res < 0 ? -1 : (int) (res % MOD);
    }

}
