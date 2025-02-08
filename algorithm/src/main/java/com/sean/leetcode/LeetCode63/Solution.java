package com.sean.leetcode.LeetCode63;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 09:32
 * @Description: https://leetcode.cn/problems/unique-paths-ii/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 63. 不同路径 II
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
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
            dp[i][j] = (j - 1 >= 0 ? process(obstacleGrid, i, j - 1, dp) : 0) + (i - 1 >= 0 ? process(obstacleGrid, i - 1, j, dp) : 0);
        } else {
            dp[i][j] = 0;
        }
        return dp[i][j];
    }

}
