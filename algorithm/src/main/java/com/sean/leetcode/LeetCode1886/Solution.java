package com.sean.leetcode.LeetCode1886;

/**
 * @Author xionghaiyang
 * @Date 2026-03-22 08:02
 * @Description https://leetcode.cn/problems/determine-whether-matrix-can-be-obtained-by-rotation
 * 1886. 判断矩阵经轮转后是否一致
 * 给你两个大小为 n x n 的二进制矩阵 mat 和 target 。
 * 现 以 90 度顺时针轮转 矩阵 mat 中的元素 若干次 ，如果能够使 mat 与 target 一致，返回 true ；否则，返回 false 。
 * n == mat.length == target.length
 * n == mat[i].length == target[i].length
 * 1 <= n <= 10
 * mat[i][j] 和 target[i][j] 不是 0 就是 1
 */
public class Solution {

    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        if (isEqual(mat, target)) {
            return true;
        }
        for (int k = 0; k < 3; k++) {
            rotate(mat);
            if (isEqual(mat, target)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEqual(int[][] mat, int[][] target) {
        int n = mat.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void rotate(int[][] mat) {
        int n = mat.length;
        //转置
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                swap(mat, i, j, j, i);
            }
        }
        //行翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                swap(mat, i, j, i, n - 1 - j);
            }
        }
    }

    private void swap(int[][] mat, int x1, int y1, int x2, int y2) {
        int temp = mat[x1][y1];
        mat[x1][y1] = mat[x2][y2];
        mat[x2][y2] = temp;
    }

    //(i,j) -> (j,n-1-i)
    private void rotate1(int[][] mat) {
        int n = mat.length;
        int[][] clone = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                clone[j][n - i - 1] = clone[i][j];
            }
        }
        mat = clone;
    }

    public boolean findRotation1(int[][] mat, int[][] target) {
        int n = mat.length;
        //(i,j) -> (j,n-1-i) -> (n-1-i,n-1-j) -> (n-1-j,i)
        int ok = (1 << 4) - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = mat[i][j];
                if (x != target[i][j]) {
                    ok &= ~(1 << 0);
                }
                if (x != target[j][n - 1 - i]) {
                    ok &= ~(1 << 1);
                }
                if (x != target[n - 1 - i][n - 1 - j]) {
                    ok &= ~(1 << 2);
                }
                if (x != target[n - 1 - j][i]) {
                    ok &= ~(1 << 3);
                }
                if (ok == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
