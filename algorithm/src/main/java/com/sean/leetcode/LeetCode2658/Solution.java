package com.sean.leetcode.LeetCode2658;

/**
 * @Author xionghaiyang
 * @Date 2025-09-01 12:10
 * @Description https://leetcode.cn/problems/maximum-number-of-fish-in-a-grid
 * 2658. 网格图中鱼的最大数目
 * 给你一个下标从 0 开始大小为 m x n 的二维整数数组 grid ，其中下标在 (r, c) 处的整数表示：
 * 如果 grid[r][c] = 0 ，那么它是一块 陆地 。
 * 如果 grid[r][c] > 0 ，那么它是一块 水域 ，且包含 grid[r][c] 条鱼。
 * 一位渔夫可以从任意 水域 格子 (r, c) 出发，然后执行以下操作任意次：
 * 捕捞格子 (r, c) 处所有的鱼，或者
 * 移动到相邻的 水域 格子。
 * 请你返回渔夫最优策略下， 最多 可以捕捞多少条鱼。
 * 如果没有水域格子，请你返回 0 。
 * 格子 (r, c) 相邻 的格子为 (r, c + 1) ，(r, c - 1) ，(r + 1, c) 和 (r - 1, c) ，前提是相邻格子在网格图内。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * 0 <= grid[i][j] <= 10
 */
public class Solution {

    private int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private int[][] grid;
    private int m;
    private int n;

    public int findMaxFish(int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    res = Math.max(res, dfs(i, j));
                }
            }
        }
        return res;
    }

    private int dfs(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            return 0;
        }
        int res = grid[i][j];
        grid[i][j] = 0;
        for (int[] dir : dirs) {
            res += dfs(i + dir[0], j + dir[1]);
        }
        return res;
    }

}
