package com.sean.leetcode.LeetCode63;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
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
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        return process(obstacleGrid, memo, m - 1, n - 1);
    }

    private int process(int[][] obstacleGrid, int[][] memo, int i, int j) {
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (obstacleGrid[i][j] == 0) {
            if (i == 0 && j == 0) {
                return memo[i][j] = 1;
            }
            memo[i][j] = (i - 1 >= 0 ? process(obstacleGrid, memo, i - 1, j) : 0) + (j - 1 >= 0 ? process(obstacleGrid, memo, i, j - 1) : 0);
        } else {
            memo[i][j] = 0;
        }
        return memo[i][j];
    }

}
