package com.sean.leetcode.LeetCode1277;

/**
 * @Author xionghaiyang
 * @Date 2025-08-20 06:06
 * @Description https://leetcode.cn/problems/count-square-submatrices-with-all-ones
 * 1277. 统计全为 1 的正方形子矩阵
 * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 */
public class Solution {

    public int countSquares(int[][] matrix) {
        int res = 0;
        int m = matrix.length, n = matrix[0].length;
        //dp[i][j]以(i,j)为右下角的正方形个数（也就是最大边长）
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = 1;
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    }
                    res += dp[i][j];
                }
            }
        }
        return res;
    }

}
