package com.sean.leetcode.LeetCode63;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 09:32
 * @Description: https://leetcode.cn/problems/unique-paths-ii
 * 63. 不同路径 II
 * 给定一个 m x n 的整数数组 grid。
 * 一个机器人初始位于 左上角（即 grid[0][0]）。
 * 机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。
 * 机器人每次只能向下或者向右移动一步。
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 机器人的移动路径中不能包含 任何 有障碍物的方格。
 * 返回机器人能够到达右下角的不同路径数量。
 * 测试用例保证答案小于等于 2 * 10^9。
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] 为 0 或 1
 */
public class Solution {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(obstacleGrid, m - 1, n - 1, dp);
    }

    private int process(int[][] obstacleGrid, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (obstacleGrid[i][j] == 0) {
            if (i == 0 && j == 0) {
                dp[i][j] = 1;
                return dp[i][j];
            }
            dp[i][j] = (i - 1 >= 0 ? process(obstacleGrid, i - 1, j, dp) : 0) + (j - 1 >= 0 ? process(obstacleGrid, i, j - 1, dp) : 0);
        } else {
            dp[i][j] = 0;
        }
        return dp[i][j];
    }

}
