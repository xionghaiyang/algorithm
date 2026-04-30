package com.sean.leetcode.LeetCode3742;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-04-30 07:30
 * @Description https://leetcode.cn/problems/maximum-path-score-in-a-grid
 * 3742. 网格中得分最大的路径
 * 给你一个 m x n 的网格 grid，其中每个单元格包含以下值之一：0、1 或 2。
 * 另给你一个整数 k。
 * 你从左上角 (0, 0) 出发，目标是到达右下角 (m - 1, n - 1)，只能向 右 或 下 移动。
 * 每个单元格根据其值对路径有以下贡献：
 * 值为 0 的单元格：分数增加 0，花费 0。
 * 值为 1 的单元格：分数增加 1，花费 1。
 * 值为 2 的单元格：分数增加 2，花费 1。
 * 返回在总花费不超过 k 的情况下可以获得的 最大分数 ，如果不存在有效路径，则返回 -1。
 * 注意： 如果到达最后一个单元格时总花费超过 k，则该路径无效。
 * 1 <= m, n <= 200
 * 0 <= k <= 10^3
 * ​​​​​​​grid[0][0] == 0
 * 0 <= grid[i][j] <= 2
 */
public class Solution {

    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][][] memo = new int[m][n][k + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        int res = process(grid, memo, m - 1, n - 1, k);
        return res < 0 ? -1 : res;
    }

    private int process(int[][] grid, int[][][] memo, int i, int j, int k) {
        if (i < 0 || j < 0 || k < 0) {
            return Integer.MIN_VALUE;
        }
        if (i == 0 && j == 0) {
            return 0;
        }
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        int rest = grid[i][j] != 0 ? k - 1 : k;
        return memo[i][j][k] = Math.max(process(grid, memo, i - 1, j, rest), process(grid, memo, i, j - 1, rest)) + grid[i][j];
    }

}
