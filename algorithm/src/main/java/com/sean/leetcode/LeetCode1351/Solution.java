package com.sean.leetcode.LeetCode1351;

/**
 * @Author xionghaiyang
 * @Date 2025-12-28 10:13
 * @Description https://leetcode.cn/problems/count-negative-numbers-in-a-sorted-matrix
 * 1351. 统计有序矩阵中的负数
 * 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非严格递减顺序排列。
 * 请你统计并返回 grid 中 负数 的数目。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * -100 <= grid[i][j] <= 100
 */
public class Solution {

    public int countNegatives(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int res = 0;
        for (int i = m - 1, j = 0; i >= 0; i--) {
            while (grid[i][j] >= 0 && j + 1 < n) {
                j++;
            }
            if (grid[i][j] < 0) {
                res += n - j;
            }
        }
        return res;
    }

}
