package com.sean.leetcode.LeetCode1895;

/**
 * @Author xionghaiyang
 * @Date 2026-01-18 09:40
 * @Description https://leetcode.cn/problems/largest-magic-square
 * 1895. 最大的幻方
 * 一个 k x k 的 幻方 指的是一个 k x k 填满整数的方格阵，且每一行、每一列以及两条对角线的和 全部相等 。
 * 幻方中的整数 不需要互不相同 。
 * 显然，每个 1 x 1 的方格都是一个幻方。
 * 给你一个 m x n 的整数矩阵 grid ，请你返回矩阵中 最大幻方 的 尺寸 （即边长 k）。
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j] <= 10^6
 */
public class Solution {


    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        //行前缀和
        int[][] rowSum = new int[m][n + 1];
        //列前缀和
        int[][] colSum = new int[m + 1][n];
        //主对角线前缀和
        int[][] diagSum = new int[m + 1][n + 1];
        //反对角线前缀和
        int[][] antiSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = grid[i][j];
                rowSum[i][j + 1] = rowSum[i][j] + x;
                colSum[i + 1][j] = colSum[i][j] + x;
                diagSum[i + 1][j + 1] = diagSum[i][j] + x;
                antiSum[i + 1][j] = antiSum[i][j + 1] + x;
            }
        }
        //k*k子矩阵的左上角为(i-k,j-k),右下角为(i-1,j-1)
        for (int k = Math.min(m, n); ; k--) {
            out:
            for (int i = k; i <= m; i++) {
                for (int j = k; j <= n; j++) {
                    int sum = diagSum[i][j] - diagSum[i - k][j - k];
                    if (antiSum[i][j - k] - antiSum[i - k][j] != sum) {
                        continue;
                    }
                    for (int r = i - k; r < i; r++) {
                        if (rowSum[r][j] - rowSum[r][j - k] != sum) {
                            continue out;
                        }
                    }
                    for (int c = j - k; c < j; c++) {
                        if (colSum[i][c] - colSum[i - k][c] != sum) {
                            continue out;
                        }
                    }
                    return k;
                }
            }
        }
    }

}
