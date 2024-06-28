package com.sean.leetcode.LeetCode221;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 10:02
 * @Description: https://leetcode.cn/problems/maximal-square/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 221. 最大正方形
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 */
public class Solution {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];//dp[i][j] 以(i-1,j-1)为右下顶点的最大正方形边长
        int maxSize = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    maxSize = Math.max(maxSize, dp[i][j]);
                }
            }
        }
        return maxSize * maxSize;
    }

}
