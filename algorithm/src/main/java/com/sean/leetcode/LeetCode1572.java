package com.sean.leetcode;

/**
 * 矩阵对角线元素的和
 * https://leetcode-cn.com/problems/matrix-diagonal-sum/
 */
public class LeetCode1572 {

    public static int diagonalSum(int[][] mat) {
        int res = 0, n = mat.length;
        for (int i = 0; i < n; i++) {
            res += mat[i][i];
            res += mat[i][n - i - 1];
        }
        if (n % 2 == 1) {
            res -= mat[n /2][n/2];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(diagonalSum(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        System.out.println(diagonalSum(new int[][]{{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}}));
        System.out.println(diagonalSum(new int[][]{{5}}));
    }

}
