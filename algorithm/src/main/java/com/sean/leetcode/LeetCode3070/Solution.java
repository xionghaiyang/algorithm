package com.sean.leetcode.LeetCode3070;

/**
 * @Author xionghaiyang
 * @Date 2026-03-18 08:11
 * @Description https://leetcode.cn/problems/count-submatrices-with-top-left-element-and-sum-less-than-k
 * 3070. 元素和小于等于 k 的子矩阵的数目
 * 给你一个下标从 0 开始的整数矩阵 grid 和一个整数 k。
 * 返回包含 grid 左上角元素、元素和小于或等于 k 的 子矩阵的数目。
 * m == grid.length
 * n == grid[i].length
 * 1 <= n, m <= 1000
 * 0 <= grid[i][j] <= 1000
 * 1 <= k <= 10^9
 */
public class Solution {

    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] preSum = new int[m + 1][n + 1];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i + 1][j + 1] = preSum[i][j + 1] + preSum[i + 1][j] - preSum[i][j] + grid[i][j];
                if (preSum[i + 1][j + 1] <= k) {
                    res++;
                }
            }
        }
        return res;
    }

}
