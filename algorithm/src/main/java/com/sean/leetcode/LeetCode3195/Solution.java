package com.sean.leetcode.LeetCode3195;

/**
 * @Author xionghaiyang
 * @Date 2025-08-22 06:47
 * @Description https://leetcode.cn/problems/find-the-minimum-area-to-cover-all-ones-i
 * 3195. 包含所有 1 的最小矩形面积 I
 * 给你一个二维 二进制 数组 grid。请你找出一个边在水平方向和竖直方向上、面积 最小 的矩形，并且满足 grid 中所有的 1 都在矩形的内部。
 * 返回这个矩形可能的 最小 面积。
 * 1 <= grid.length, grid[i].length <= 1000
 * grid[i][j] 是 0 或 1。
 * 输入保证 grid 中至少有一个 1 。
 */
public class Solution {

    public int minimumArea(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int left = m, right = -1, top = n, bottom = -1;
        for (int i = 0; i < m; i++) {
            boolean flag = false;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    flag = true;
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                }
            }
            if (flag) {
                top = Math.min(top, i);
                bottom = Math.max(bottom, i);
            }
        }
        return right >= left && bottom >= top ? (right - left + 1) * (bottom - top + 1) : 0;
    }

}
