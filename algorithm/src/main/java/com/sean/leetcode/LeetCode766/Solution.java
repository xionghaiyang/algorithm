package com.sean.leetcode.LeetCode766;

/**
 * @Author xionghaiyang
 * @Date 2025-10-08 19:49
 * @Description https://leetcode.cn/problems/toeplitz-matrix
 * 766. 托普利茨矩阵
 * 给你一个 m x n 的矩阵 matrix 。
 * 如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 20
 * 0 <= matrix[i][j] <= 99
 */
public class Solution {

    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

}
