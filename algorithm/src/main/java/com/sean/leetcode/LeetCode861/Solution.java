package com.sean.leetcode.LeetCode861;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-28 16:50
 * @Description: https://leetcode.cn/problems/score-after-flipping-matrix/description/
 * 861. 翻转矩阵后的得分
 * 给你一个大小为 m x n 的二元矩阵 grid ，矩阵中每个元素的值为 0 或 1 。
 * 一次 移动 是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的 得分 就是这些数字的总和。
 * 在执行任意次 移动 后（含 0 次），返回可能的最高分数。
 */
public class Solution {

    public int matrixScore(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = m * (1 << (n - 1));
        for (int j = 1; j < n; j++) {
            int nOnes = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][0] == 1) {
                    nOnes += grid[i][j];
                } else {
                    //如果这一行进行了行反转，则该元素的实际取值为1-grid[i][j]
                    nOnes += (1 - grid[i][j]);
                }
            }
            int k = Math.max(nOnes, m - nOnes);
            res += k * (1 << (n - j - 1));
        }
        return res;
    }

}
