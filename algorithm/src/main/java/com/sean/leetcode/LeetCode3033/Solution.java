package com.sean.leetcode.LeetCode3033;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xionghaiyang
 * @Date 2024-07-05 06:53
 * @Description https://leetcode.cn/problems/modify-the-matrix/
 * 3033. 修改矩阵
 * 给你一个下标从 0 开始、大小为 m x n 的整数矩阵 matrix ，新建一个下标从 0 开始、名为 answer 的矩阵。
 * 使 answer 与 matrix 相等，接着将其中每个值为 -1 的元素替换为所在列的 最大 元素。
 * 返回矩阵 answer 。
 */
public class Solution {

    public int[][] modifiedMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[m][n];
        Queue<Integer> queue = new LinkedList<>();
        for (int j = 0; j < n; j++) {
            int max = -1;
            for (int i = 0; i < m; i++) {
                if (matrix[i][j] != -1) {
                    res[i][j] = matrix[i][j];
                    max = Math.max(max, matrix[i][j]);
                } else {
                    queue.add(i);
                }
            }
            while (!queue.isEmpty()) {
                int i = queue.poll();
                res[i][j] = max;
            }
        }
        return res;
    }

    public int[][] modifiedMatrix1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] res = new int[m][n];
        for (int j = 0; j < n; j++) {
            int max = -1;
            for (int i = 0; i < m; i++) {
                max = Math.max(max, matrix[i][j]);
            }
            for (int i = 0; i < m; i++) {
                if (matrix[i][j] != -1) {
                    res[i][j] = matrix[i][j];
                } else {
                    res[i][j] = max;
                }
            }
        }
        return res;
    }

}
