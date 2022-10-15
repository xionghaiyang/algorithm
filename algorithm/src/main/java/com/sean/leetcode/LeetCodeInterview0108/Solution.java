package com.sean.leetcode.LeetCodeInterview0108;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-30 22:40
 * @Description: https://leetcode.cn/problems/zero-matrix-lcci/
 * 面试题 01.08. 零矩阵
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 */
public class Solution {

    public void setZeroes1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0 && !visited[i][j]) {
                    dfs(matrix, i, j, visited);
                }
            }
        }
    }

    private void dfs(int[][] matrix, int x, int y, boolean[][] visited) {
        int m = matrix.length;
        int n = matrix[0].length;
        visited[x][y] = true;
        for (int i = 0; i < m; i++) {
            if (!visited[i][y]) {
                if (matrix[i][y] == 0) {
                    dfs(matrix, i, y, visited);
                } else {
                    matrix[i][y] = 0;
                    visited[i][y] = true;
                }
            }
        }
        for (int j = 0; j < n; j++) {
            if (!visited[x][j]) {
                if (matrix[x][j] == 0) {
                    dfs(matrix, x, j, visited);
                } else {
                    matrix[x][j] = 0;
                    visited[x][j] = true;
                }
            }
        }
    }

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean flagRow = false;
        boolean flagCol = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                flagCol = true;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                flagRow = true;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (flagCol) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (flagRow) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }

}
