package com.sean.leetcode.LeetCode1975;

/**
 * @Author xionghaiyang
 * @Date 2026-01-05 08:34
 * @Description https://leetcode.cn/problems/maximum-matrix-sum
 * 1975. 最大方阵和
 * 给你一个 n x n 的整数方阵 matrix 。
 * 你可以执行以下操作 任意次 ：
 * 选择 matrix 中 相邻 两个元素，并将它们都 乘以 -1 。
 * 如果两个元素有 公共边 ，那么它们就是 相邻 的。
 * 你的目的是 最大化 方阵元素的和。
 * 请你在执行以上操作之后，返回方阵的 最大 和。
 * n == matrix.length == matrix[i].length
 * 2 <= n <= 250
 * -10^5 <= matrix[i][j] <= 10^5
 */
public class Solution {

    public long maxMatrixSum(int[][] matrix) {
        long total = 0;
        int negCnt = 0, min = Integer.MAX_VALUE;
        int n = matrix.length, m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = matrix[i][j];
                if (x < 0) {
                    negCnt++;
                    x = -x;
                }
                min = Math.min(min, x);
                total += x;
            }
        }
        if ((negCnt & 1) == 1) {
            total -= min * 2;
        }
        return total;
    }

}
