package com.sean.leetcode.LeetCode329;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xionghaiyang
 * @Date 2025-08-12 20:27
 * @Description https://leetcode.cn/problems/longest-increasing-path-in-a-matrix
 * 329. 矩阵中的最长递增路径
 * 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。
 * 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 2^31 - 1
 */
public class Solution {

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, process(matrix, i, j, memo));
            }
        }
        return res;
    }

    private int process(int[][] matrix, int i, int j, int[][] memo) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int m = matrix.length, n = matrix[0].length;
        int res = 1;
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            res = Math.max(res, 1 + process(matrix, i - 1, j, memo));
        }
        if (i + 1 < m && matrix[i + 1][j] > matrix[i][j]) {
            res = Math.max(res, 1 + process(matrix, i + 1, j, memo));
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            res = Math.max(res, 1 + process(matrix, i, j - 1, memo));
        }
        if (j + 1 < n && matrix[i][j + 1] > matrix[i][j]) {
            res = Math.max(res, 1 + process(matrix, i, j + 1, memo));
        }
        memo[i][j] = res;
        return res;
    }

    public int longestIncreasingPath1(int[][] matrix) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = matrix.length, n = matrix[0].length;
        int[][] outDegrees = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] dir : dirs) {
                    int x = i + dir[0], y = j + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                        outDegrees[i][j]++;
                    }
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (outDegrees[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int x = cell[0], y = cell[1];
                for (int[] dir : dirs) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && matrix[nx][ny] < matrix[x][y]) {
                        if (--outDegrees[nx][ny] == 0) {
                            queue.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
        return res;
    }

}
