package com.sean.leetcode.LeetCode498;

/**
 * @Author xionghaiyang
 * @Date 2025-08-25 06:02
 * @Description https://leetcode.cn/problems/diagonal-traverse
 * 498. 对角线遍历
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 10^4
 * 1 <= m * n <= 10^4
 * -10^5 <= mat[i][j] <= 10^5
 */
public class Solution {

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] res = new int[m * n];
        for (int i = 0, index = 0; i < m + n - 1; i++) {
            if ((i & 1) == 1) {//从上至下
                int x = i < n ? 0 : i - (n - 1);
                int y = i < n ? i : n - 1;
                while (x < m && y >= 0) {
                    res[index++] = mat[x++][y--];
                }
            } else {//从下至上
                int x = i < m ? i : m - 1;
                int y = i < m ? 0 : i - (m - 1);
                while (x >= 0 && y < n) {
                    res[index++] = mat[x--][y++];
                }
            }
        }
        return res;
    }

}
