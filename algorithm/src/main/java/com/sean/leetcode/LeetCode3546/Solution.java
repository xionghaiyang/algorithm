package com.sean.leetcode.LeetCode3546;

/**
 * @Author xionghaiyang
 * @Date 2026-03-25 06:34
 * @Description https://leetcode.cn/problems/equal-sum-grid-partition-i
 * 3546. 等和矩阵分割 I
 * 给你一个由正整数组成的 m x n 矩阵 grid。
 * 你的任务是判断是否可以通过 一条水平或一条垂直分割线 将矩阵分割成两部分，使得：
 * 分割后形成的每个部分都是 非空 的。
 * 两个部分中所有元素的和 相等 。
 * 如果存在这样的分割，返回 true；否则，返回 false。
 * 1 <= m == grid.length <= 10^5
 * 1 <= n == grid[i].length <= 10^5
 * 2 <= m * n <= 10^5
 * 1 <= grid[i][j] <= 10^5
 */
public class Solution {

    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long sum = 0;
        long[] row = new long[m];
        long[] col = new long[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum += grid[i][j];
                row[i] += grid[i][j];
                col[j] += grid[i][j];
            }
        }
        if ((sum & 1) == 1) {
            return false;
        }
        long preSum = 0;
        for (int i = 0; i < m - 1; i++) {
            preSum += row[i];
            if (2 * preSum == sum) {
                return true;
            }
        }
        preSum = 0;
        for (int j = 0; j < n - 1; j++) {
            preSum += col[j];
            if (2 * preSum == sum) {
                return true;
            }
        }
        return false;
    }

}
