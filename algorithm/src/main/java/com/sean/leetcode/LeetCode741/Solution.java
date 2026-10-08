package com.sean.leetcode.LeetCode741;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-05-06 17:56
 * @Description https://leetcode.cn/problems/cherry-pickup
 * 741. 摘樱桃
 * 给你一个 n x n 的网格 grid ，代表一块樱桃地，每个格子由以下三种数字的一种来表示：
 * 0 表示这个格子是空的，所以你可以穿过它。
 * 1 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。
 * -1 表示这个格子里有荆棘，挡着你的路。
 * 请你统计并返回：在遵守下列规则的情况下，能摘到的最多樱桃数：
 * 从位置 (0, 0) 出发，最后到达 (n - 1, n - 1) ，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为 0 或者 1 的格子）；
 * 当到达 (n - 1, n - 1) 后，你要继续走，直到返回到 (0, 0) ，只能向上或向左走，并且只能穿越有效的格子；
 * 当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为 0 ）；
 * 如果在 (0, 0) 和 (n - 1, n - 1) 之间不存在一条可经过的路径，则无法摘到任何一个樱桃。
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * grid[i][j] 为 -1、0 或 1
 * grid[0][0] != -1
 * grid[n - 1][n - 1] != -1
 */
public class Solution {

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] memo = new int[n * 2 - 1][n][n];
        for (int i = 0; i < n * 2 - 1; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return Math.max(process(grid, memo, n * 2 - 2, n - 1, n - 1), 0);
    }

    private int process(int[][] grid, int[][][] memo, int t, int j, int k) {
        if (j < 0 || k < 0 || t < j || t < k || grid[t - j][j] < 0 || grid[t - k][k] < 0) {
            return Integer.MIN_VALUE;
        }
        if (t == 0) {
            return grid[0][0];
        }
        if (memo[t][j][k] != -1) {
            return memo[t][j][k];
        }
        int res = Math.max(Math.max(process(grid, memo, t - 1, j, k), process(grid, memo, t - 1, j, k - 1)), Math.max(process(grid, memo, t - 1, j - 1, k), process(grid, memo, t - 1, j - 1, k - 1))) + grid[t - j][j] + (k != j ? grid[t - k][k] : 0);
        return memo[t][j][k] = res;
    }

}
