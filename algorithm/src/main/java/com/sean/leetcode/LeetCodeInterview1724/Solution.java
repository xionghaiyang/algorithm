package com.sean.leetcode.LeetCodeInterview1724;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-19 13:27
 * @Description: https://leetcode.cn/problems/max-submatrix-lcci/
 * 面试题 17.24. 最大子矩阵
 * 给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
 * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。
 * 若有多个满足条件的子矩阵，返回任意一个均可。
 * 注意：本题相对书上原题稍作改动
 */
public class Solution {

    public int[] getMaxMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] preSum = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                preSum[i][j] = matrix[i - 1][j - 1] + preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1];
            }
        }
        int s = Integer.MIN_VALUE;
        int[] res = new int[]{0, 0, 0, 0};
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int[] num = new int[m];
                for (int k = 0; k < m; k++) {
                    num[k] = preSum[j + 1][k + 1] + preSum[i][k] - preSum[j + 1][k] - preSum[i][k + 1];
                }
                int start = 0;
                int sum = num[0];
                if (sum > s) {
                    res = new int[]{i, start, j, 0};
                    s = sum;
                }
                for (int k = 1; k < m; k++) {
                    if (sum > 0) {
                        sum += num[k];
                    } else {
                        sum = num[k];
                        start = k;
                    }
                    if (sum > s) {
                        res = new int[]{i, start, j, k};
                        s = sum;
                    }
                }
            }
        }
        return res;
    }

}
