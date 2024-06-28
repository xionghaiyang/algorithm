package com.sean.leetcode.LeetCode1329;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2024-04-29 17:22
 * @Description https://leetcode.cn/problems/sort-the-matrix-diagonally/
 * 1329. 将矩阵按对角线排序
 * 矩阵对角线 是一条从矩阵最上面行或者最左侧列中的某个元素开始的对角线，沿右下方向一直到矩阵末尾的元素。
 * 例如，矩阵 mat 有 6 行 3 列，从 mat[2][0] 开始的 矩阵对角线 将会经过 mat[2][0]、mat[3][1] 和 mat[4][2] 。
 * 给你一个 m * n 的整数矩阵 mat ，请你将同一条 矩阵对角线 上的元素按升序排序后，返回排好序的矩阵。
 */
public class Solution {

    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        List<List<Integer>> diag = new ArrayList<>(m + n);
        for (int i = 0; i < m + n; i++) {
            diag.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                diag.get(i - j + n).add(mat[i][j]);
            }
        }
        for (List<Integer> list : diag) {
            Collections.sort(list);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = diag.get(i - j + n).remove(0);
            }
        }
        return mat;
    }

}
