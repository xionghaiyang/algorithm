package com.sean.leetcode.LeetCode2373;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-01 08:11
 * @Description: https://leetcode.cn/problems/largest-local-values-in-a-matrix/
 * 2373. 矩阵中的局部最大值
 * 给你一个大小为 n x n 的整数矩阵 grid 。
 * 生成一个大小为 (n - 2) x (n - 2) 的整数矩阵  maxLocal ，并满足：
 * maxLocal[i][j] 等于 grid 中以 i + 1 行和 j + 1 列为中心的 3 x 3 矩阵中的 最大值 。
 * 换句话说，我们希望找出 grid 中每个 3 x 3 矩阵中的最大值。
 * 返回生成的矩阵。
 */
public class Solution {

    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] maxLocal = new int[n - 2][n - 2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                maxLocal[i][j] = process(grid, i, j);
            }
        }
        return maxLocal;
    }

    private int process(int[][] grid, int i, int j) {
        int res = Integer.MIN_VALUE;
        for (int m = i; m < i + 3; m++) {
            for (int n = j; n < j + 3; n++) {
                res = Math.max(res, grid[m][n]);
            }
        }
        return res;
    }

}
