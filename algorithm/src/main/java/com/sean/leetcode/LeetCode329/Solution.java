package com.sean.leetcode.LeetCode329;

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

    private static final int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, process(matrix, memo, i, j));
            }
        }
        return res;
    }

    private int process(int[][] matrix, int[][] memo, int x, int y) {
        if (memo[x][y] != 0) {
            return memo[x][y];
        }
        int m = matrix.length, n = matrix[0].length;
        int res = 1;
        for (int[] dir : dirs) {
            int nx = x + dir[0], ny = y + dir[1];
            if (0 <= nx && nx < m && 0 <= ny && ny < n && matrix[nx][ny] > matrix[x][y]) {
                res = Math.max(res, process(matrix, memo, nx, ny) + 1);
            }
        }
        return memo[x][y] = res;
    }

    public int longestIncreasingPath1(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] outDegrees = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] dir : dirs) {
                    int x = i + dir[0], y = j + dir[1];
                    if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j]) {
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
                    if (0 <= nx && nx < m && 0 <= ny && ny < n && matrix[nx][ny] < matrix[x][y]) {
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
