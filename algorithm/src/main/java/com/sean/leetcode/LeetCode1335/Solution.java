package com.sean.leetcode.LeetCode1335;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-16 08:07
 * @Description: https://leetcode.cn/problems/minimum-difficulty-of-a-job-schedule/
 * 1335. 工作计划的最低难度
 * 你需要制定一份 d 天的工作计划表。
 * 工作之间存在依赖，要想执行第 i 项工作，你必须完成全部 j 项工作（ 0 <= j < i）。
 * 你每天 至少 需要完成一项任务。
 * 工作计划的总难度是这 d 天每一天的难度之和，而一天的工作难度是当天应该完成工作的最大难度。
 * 给你一个整数数组 jobDifficulty 和一个整数 d，分别代表工作难度和需要计划的天数。
 * 第 i 项工作的难度是 jobDifficulty[i]。
 * 返回整个工作计划的 最小难度 。
 * 如果无法制定工作计划，则返回 -1 。
 */
public class Solution {

    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        //dp[i][j]表示前i+1天完成前j项工作的最小难度
        int[][] dp = new int[d + 1][n];
        for (int i = 0; i < d + 1; i++) {
            Arrays.fill(dp[i], 0x3f3f3f3f);
        }
        int max = 0;
        for (int j = 0; j < n; j++) {
            max = Math.max(max, jobDifficulty[j]);
            dp[0][j] = max;
        }
        for (int i = 1; i < d; i++) {
            for (int j = i; j < n; j++) {
                max = 0;
                for (int k = j; k >= i; k--) {
                    max = Math.max(max, jobDifficulty[k]);
                    dp[i][j] = Math.min(dp[i][j], max + dp[i - 1][k - 1]);
                }
            }
        }
        return dp[d - 1][n - 1];
    }

}
