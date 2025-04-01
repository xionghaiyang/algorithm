package com.sean.leetcode.LeetCode2140;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-04-01 08:41
 * @Description https://leetcode.cn/problems/solving-questions-with-brainpower
 * 2140. 解决智力问题
 * 给你一个下标从 0 开始的二维整数数组 questions ，其中 questions[i] = [pointsi, brainpoweri] 。
 * 这个数组表示一场考试里的一系列题目，你需要 按顺序 （也就是从问题 0 开始依次解决），针对每个问题选择 解决 或者 跳过 操作。
 * 解决问题 i 将让你 获得  pointsi 的分数，但是你将 无法 解决接下来的 brainpoweri 个问题（即只能跳过接下来的 brainpoweri 个问题）。
 * 如果你跳过问题 i ，你可以对下一个问题决定使用哪种操作。
 * 比方说，给你 questions = [[3, 2], [4, 3], [4, 4], [2, 5]] ：
 * 如果问题 0 被解决了， 那么你可以获得 3 分，但你不能解决问题 1 和 2 。
 * 如果你跳过问题 0 ，且解决问题 1 ，你将获得 4 分但是不能解决问题 2 和 3 。
 * 请你返回这场考试里你能获得的 最高 分数。
 * 1 <= questions.length <= 10^5
 * questions[i].length == 2
 * 1 <= pointsi, brainpoweri <= 10^5
 */
public class Solution {

    public long mostPoints(int[][] questions) {
        int n = questions.length;
        return process(questions, 0);
    }

    private long process(int[][] questions, int i) {
        int n = questions.length;
        long res1 = questions[i][0];
        if (i + questions[i][1] + 1 < n) {
            res1 += process(questions, i + questions[i][1] + 1);
        }
        long res2 = 0;
        if (i + 1 < n) {
            res2 = process(questions, i + 1);
        }
        return Math.max(res1, res2);
    }

    public long mostPoints1(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n];
        Arrays.fill(dp, -1);
        return process1(questions, 0, dp);
    }

    private long process1(int[][] questions, int i, long[] dp) {
        if (dp[i] != -1) {
            return dp[i];
        }
        int n = questions.length;
        long res1 = questions[i][0];
        if (i + questions[i][1] + 1 < n) {
            res1 += process1(questions, i + questions[i][1] + 1, dp);
        }
        long res2 = 0;
        if (i + 1 < n) {
            res2 = process1(questions, i + 1, dp);
        }
        dp[i] = Math.max(res1, res2);
        return dp[i];
    }

    public long mostPoints2(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            long res1 = questions[i][0];
            if (i + questions[i][1] + 1 < n) {
                res1 += dp[i + questions[i][1] + 1];
            }
            long res2 = 0;
            if (i + 1 < n) {
                res2 = dp[i + 1];
            }
            dp[i] = Math.max(res1, res2);
        }
        return dp[0];
    }

}
