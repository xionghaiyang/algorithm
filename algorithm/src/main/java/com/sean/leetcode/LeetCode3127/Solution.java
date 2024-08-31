package com.sean.leetcode.LeetCode3127;

/**
 * @Author xionghaiyang
 * @Date 2024-08-31 14:32
 * @Description https://leetcode.cn/problems/make-a-square-with-the-same-color/
 * 3127. 构造相同颜色的正方形
 * 给你一个二维 3 x 3 的矩阵 grid ，每个格子都是一个字符，要么是 'B' ，要么是 'W' 。
 * 字符 'W' 表示白色，字符 'B' 表示黑色。
 * 你的任务是改变 至多一个 格子的颜色，使得矩阵中存在一个 2 x 2 颜色完全相同的正方形。
 * 如果可以得到一个相同颜色的 2 x 2 正方形，那么返回 true ，否则返回 false 。
 */
public class Solution {

    public boolean canMakeSquare(char[][] grid) {
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                if (check(grid, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(char[][] grid, int x, int y) {
        int count = 0;
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                if (grid[x + i][y + j] == 'B') {
                    count++;
                }
            }
        }
        return count != 2;
    }

}
