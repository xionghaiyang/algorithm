package com.sean.leetcode.LeetCode1727;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-03-17 08:27
 * @Description https://leetcode.cn/problems/largest-submatrix-with-rearrangements
 * 1727. 重新排列后的最大子矩阵
 * 给你一个二进制矩阵 matrix ，它的大小为 m x n ，你可以将 matrix 中的 列 按任意顺序重新排列。
 * 请你返回最优方案下将 matrix 重新排列后，全是 1 的子矩阵面积。
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m * n <= 10^5
 * matrix[i][j] 要么是 0 ，要么是 1 。
 */
public class Solution {

    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] heights = new int[n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    heights[j] = 0;
                } else {
                    heights[j]++;
                }
            }
            int[] hs = heights.clone();
            Arrays.sort(hs);
            for (int k = 0; k < n; k++) {
                res = Math.max(res, (n - k) * hs[k]);
            }
        }
        return res;
    }

}
