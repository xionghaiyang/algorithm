package com.sean.leetcode.LeetCode1292;

/**
 * @Author xionghaiyang
 * @Date 2026-01-19 11:13
 * @Description https://leetcode.cn/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold
 * 1292. 元素和小于等于阈值的正方形的最大边长
 * 给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
 * 请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 300
 * 0 <= mat[i][j] <= 10^4
 * 0 <= threshold <= 10^5
 */
public class Solution {

    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i + 1][j + 1] = preSum[i + 1][j] + preSum[i][j + 1] - preSum[i][j] + mat[i][j];
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                while (i + res < m && j + res < n && query(preSum, i, j, i + res, j + res) <= threshold) {
                    res++;
                }
            }
        }
        return res;
    }

    private int query(int[][] preSum, int x1, int y1, int x2, int y2) {
        return preSum[x2 + 1][y2 + 1] - preSum[x2 + 1][y1] - preSum[x1][y2 + 1] + preSum[x1][y1];
    }

}
