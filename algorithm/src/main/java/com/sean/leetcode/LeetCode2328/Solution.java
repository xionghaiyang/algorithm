package com.sean.leetcode.LeetCode2328;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-10-09 20:14
 * @Description https://leetcode.cn/problems/number-of-increasing-paths-in-a-grid
 * 2328. 网格图中递增路径的数目
 * 给你一个 m x n 的整数网格图 grid ，你可以从一个格子移动到 4 个方向相邻的任意一个格子。
 * 请你返回在网格图中从 任意 格子出发，达到 任意 格子，且路径中的数字是 严格递增 的路径数目。
 * 由于答案可能会很大，请将结果对 10^9 + 7 取余 后返回。
 * 如果两条路径中访问过的格子不是完全相同的，那么它们视为两条不同的路径。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 1000
 * 1 <= m * n <= 10^5
 * 1 <= grid[i][j] <= 10^5
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int countPaths(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        long res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res += dfs(grid, memo, i, j);
            }
        }
        return (int) (res % MOD);
    }

    private int dfs(int[][] grid, int[][] memo, int i, int j) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        long res = 1;
        for (int[] dir : DIRS) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] > grid[i][j]) {
                res += dfs(grid, memo, x, y);
            }
        }
        return memo[i][j] = (int) (res % MOD);
    }

}
