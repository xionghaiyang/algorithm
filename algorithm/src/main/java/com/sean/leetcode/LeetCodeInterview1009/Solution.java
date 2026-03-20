package com.sean.leetcode.LeetCodeInterview1009;

/**
 * @Author xionghaiyang
 * @Date 2026-03-20 12:12
 * @Description https://leetcode.cn/problems/sorted-matrix-search-lcci
 * 面试题 10.09. 排序矩阵查找
 * 给定 M×N 矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
 */
public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

}
