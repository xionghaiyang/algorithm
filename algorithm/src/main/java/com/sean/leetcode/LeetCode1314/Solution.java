package com.sean.leetcode.LeetCode1314;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-28 17:15
 * @Description: https://leetcode.cn/problems/matrix-block-sum/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 1314. 矩阵区域和
 * 给你一个 m x n 的矩阵 mat 和一个整数 k ，请你返回一个矩阵 answer ，其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和： 
 * i - k <= r <= i + k,
 * j - k <= c <= j + k 且
 * (r, c) 在矩阵内。
 */
public class Solution {

    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = get(preSum, m, n, i + k + 1, j + k + 1) - get(preSum, m, n, i - k, j + k + 1) - get(preSum, m, n, i + k + 1, j - k) + get(preSum, m, n, i - k, j - k);
            }
        }
        return res;
    }

    private int get(int[][] preSum, int m, int n, int x, int y) {
        x = Math.max(0, Math.min(x, m));
        y = Math.max(0, Math.min(y, n));
        return preSum[x][y];
    }

}
