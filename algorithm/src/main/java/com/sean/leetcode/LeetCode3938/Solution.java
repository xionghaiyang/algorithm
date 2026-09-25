package com.sean.leetcode.LeetCode3938;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-25 18:06
 * @Description: https://leetcode.cn/problems/maximum-path-intersection-sum-in-a-grid
 * 3938. 矩阵中最大共享路径和
 * 给你一个 m x n 的整数矩阵 grid 。
 * 两个玩家在矩阵中移动：
 * 玩家 1 从左上角单元格 (0, 0) 出发，只能向右或向下移动。
 * 他们的目的地是右下角单元格 (m - 1, n - 1) 。
 * 玩家 2 从左下角单元格 (m - 1, 0) 出发，只能向右或向上移动。
 * 他们的目的地是右上角单元格 (0, n - 1) 。
 * 每个玩家必须选择一条从各自起始单元格到目的地的有效路径。
 * 如果一个单元格属于 两条 被选中的路径，则称该单元格为 共享 单元格。
 * 返回一个整数，表示所有 共享 单元格的值的 最大 可能总和。
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 5 * 10^5
 * -100 <= grid[i][j] <= 100
 */
public class Solution {

    public int maxScore(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                res = Math.max(res, grid[i][j]);
            }
        }
        for (int[] row : grid) {
            res = Math.max(res, maxSubArray(row));
        }
        int[] col = new int[m];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                col[i] = grid[i][j];
            }
            res = Math.max(res, maxSubArray(col));
        }
        return res;
    }

    private int maxSubArray(int[] nums) {
        int n = nums.length;
        int res = Integer.MIN_VALUE, f = nums[0];
        for (int i = 1; i < n; i++) {
            res = Math.max(res, f + nums[i]);
            f = Math.max(f, 0) + nums[i];
        }
        return res;
    }

}
