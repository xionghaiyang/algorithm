package com.sean.leetcode.LeetCode2538;

/**
 * @Author xionghaiyang
 * @Date 2025-11-14 11:06
 * @Description https://leetcode.cn/problems/increment-submatrices-by-one
 * 2536. 子矩阵元素加 1
 * 给你一个正整数 n ，表示最初有一个 n x n 、下标从 0 开始的整数矩阵 mat ，矩阵中填满了 0 。
 * 另给你一个二维整数数组 query 。
 * 针对每个查询 query[i] = [row1i, col1i, row2i, col2i] ，请你执行下述操作：
 * 找出 左上角 为 (row1i, col1i) 且 右下角 为 (row2i, col2i) 的子矩阵，将子矩阵中的 每个元素 加 1 。
 * 也就是给所有满足 row1i <= x <= row2i 和 col1i <= y <= col2i 的 mat[x][y] 加 1 。
 * 返回执行完所有操作后得到的矩阵 mat 。
 * 1 <= n <= 500
 * 1 <= queries.length <= 10^4
 * 0 <= row1i <= row2i < n
 * 0 <= col1i <= col2i < n
 */
public class Solution {

    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] diff = new int[n + 2][n + 2];
        for (int[] query : queries) {
            int row1 = query[0], col1 = query[1], row2 = query[2], col2 = query[3];
            diff[row1 + 1][col1 + 1]++;
            diff[row1 + 1][col2 + 2]--;
            diff[row2 + 2][col1 + 1]--;
            diff[row2 + 2][col2 + 2]++;
        }
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                diff[i + 1][j + 1] += diff[i + 1][j] + diff[i][j + 1] - diff[i][j];
                res[i][j] = diff[i + 1][j + 1];
            }
        }
        return res;
    }

}
