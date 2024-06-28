package com.sean.leetcode.LeetCode74;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-18 12:43
 * @Description: https://leetcode.cn/problems/search-a-2d-matrix/
 * 74. 搜索二维矩阵
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 */
public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int x = matrix[mid / n][mid % n];
            if (x < target) {
                left = mid + 1;
            } else if (x > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

}
