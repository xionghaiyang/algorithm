package com.sean.leetcode.LeetCodeInterview1724;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-19 13:27
 * @Description: https://leetcode.cn/problems/max-submatrix-lcci
 * 面试题 17.24. 最大子矩阵
 * 给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
 * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。
 * 若有多个满足条件的子矩阵，返回任意一个均可。
 * 注意：本题相对书上原题稍作改动
 */
public class Solution {

    public int[] getMaxMatrix(int[][] matrix) {
        int[] res = new int[4];
        int m = matrix.length, n = matrix[0].length;
        int[] nums = new int[n];
        int r1 = 0, c1 = 0, sum = 0, maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            Arrays.fill(nums, 0);
            for (int j = i; j < m; j++) {
                sum = 0;
                for (int k = 0; k < n; k++) {
                    nums[k] += matrix[j][k];
                    if (sum > 0) {
                        sum += nums[k];
                    } else {
                        sum = nums[k];
                        r1 = i;
                        c1 = k;
                    }
                    if (sum > maxSum) {
                        maxSum = sum;
                        res = new int[]{r1, c1, j, k};
                    }
                }

            }
        }
        return res;
    }

}
