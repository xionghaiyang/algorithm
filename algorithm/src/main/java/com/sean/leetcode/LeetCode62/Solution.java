package com.sean.leetcode.LeetCode62;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-28 17:36
 * @Description: https://leetcode.cn/problems/unique-paths
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。
 * 机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10^9
 */
public class Solution {

    public int uniquePaths(int m, int n) {
        //dp[i][j],从(i,j)走到(m-1,n-1)有多少条路径
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(m, n, 0, 0, dp);
    }

    private int process(int m, int n, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (i == m - 1) {
            dp[i][j] = 1;
            return dp[i][j];
        }
        if (j == n - 1) {
            dp[i][j] = 1;
            return dp[i][j];
        }
        int res = process(m, n, i + 1, j, dp) + process(m, n, i, j + 1, dp);
        dp[i][j] = res;
        return dp[i][j];
    }


}
