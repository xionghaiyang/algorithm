package com.sean.leetcode.LeetCode3212;

/**
 * @Author xionghaiyang
 * @Date 2026-03-19 08:19
 * @Description https://leetcode.cn/problems/count-submatrices-with-equal-frequency-of-x-and-y
 * 3212. 统计 X 和 Y 频数相等的子矩阵数量
 * 给你一个二维字符矩阵 grid，其中 grid[i][j] 可能是 'X'、'Y' 或 '.'，返回满足以下条件的子矩阵数量：
 * 包含 grid[0][0]
 * 'X' 和 'Y' 的频数相等。
 * 至少包含一个 'X'。
 * 1 <= grid.length, grid[i].length <= 1000
 * grid[i][j] 可能是 'X'、'Y' 或 '.'.
 */
public class Solution {

    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] preSum = new int[m + 1][n + 1][2];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum[i + 1][j + 1][0] = preSum[i][j + 1][0] + preSum[i + 1][j][0] - preSum[i][j][0] + (grid[i][j] == 'X' ? 1 : 0);
                preSum[i + 1][j + 1][1] = preSum[i][j + 1][1] + preSum[i + 1][j][1] - preSum[i][j][1] + (grid[i][j] == 'Y' ? 1 : 0);
                if (preSum[i + 1][j + 1][0] > 0 && preSum[i + 1][j + 1][0] == preSum[i + 1][j + 1][1]) {
                    res++;
                }
            }
        }
        return res;
    }

}
