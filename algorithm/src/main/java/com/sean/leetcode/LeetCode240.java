package com.sean.leetcode;

/**
 * 搜索二维矩阵 II
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/submissions/
 */
public class LeetCode240 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

}
