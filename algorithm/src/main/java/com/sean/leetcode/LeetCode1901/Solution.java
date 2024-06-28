package com.sean.leetcode.LeetCode1901;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-19 17:12
 * @Description: https://leetcode.cn/problems/find-a-peak-element-ii/
 * 1901. 寻找峰值 II
 * 一个 2D 网格中的 峰值 是指那些 严格大于 其相邻格子(上、下、左、右)的元素。
 * 给你一个 从 0 开始编号 的 m x n 矩阵 mat ，其中任意两个相邻格子的值都 不相同 。
 * 找出 任意一个 峰值 mat[i][j] 并 返回其位置 [i,j] 。
 * 你可以假设整个矩阵周边环绕着一圈值为 -1 的格子。
 * 要求必须写出时间复杂度为 O(m log(n)) 或 O(n log(m)) 的算法
 */
public class Solution {

    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int low = 0, high = m - 1;
        while (low <= high) {
            int i = (low + high) / 2;
            int j = -1, maxElement = -1;
            for (int k = 0; k < n; k++) {
                if (mat[i][k] > maxElement) {
                    j = k;
                    maxElement = mat[i][k];
                }
            }
            if (i - 1 >= 0 && mat[i][j] < mat[i - 1][j]) {
                high = i - 1;
                continue;
            }
            if (i + 1 < m && mat[i][j] < mat[i + 1][j]) {
                low = i + 1;
                continue;
            }
            return new int[]{i, j};
        }
        return new int[0];
    }

}
