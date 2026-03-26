package com.sean.leetcode.LeetCode2946;

/**
 * @Author xionghaiyang
 * @Date 2026-03-27 07:03
 * @Description https://leetcode.cn/problems/matrix-similarity-after-cyclic-shifts
 * 2946. 循环移位后的矩阵相似检查
 * 给你一个下标从 0 开始且大小为 m x n 的整数矩阵 mat 和一个整数 k 。
 * 请你将矩阵中的 奇数 行循环 右 移 k 次，偶数 行循环 左 移 k 次。
 * 如果初始矩阵和最终矩阵完全相同，则返回 true ，否则返回 false 。
 * 1 <= mat.length <= 25
 * 1 <= mat[i].length <= 25
 * 1 <= mat[i][j] <= 25
 * 1 <= k <= 50
 */
public class Solution {

    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        k %= n;
        for (int i = 0; i < m; i++) {
            //如果左移k次等于自己，那么右移动k次也等于自己
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != mat[i][(j + k) % n]) {
                    return false;
                }
            }
        }
        return true;
    }

}
