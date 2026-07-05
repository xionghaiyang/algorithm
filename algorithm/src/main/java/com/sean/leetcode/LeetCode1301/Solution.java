package com.sean.leetcode.LeetCode1301;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-05 09:12
 * @Description: https://leetcode.cn/problems/number-of-paths-with-max-score
 * 1301. 最大得分的路径数目
 * 给你一个正方形字符数组 board ，你从数组最右下方的字符 'S' 出发。
 * 你的目标是到达数组最左上角的字符 'E' ，数组剩余的部分为数字字符 1, 2, ..., 9 或者障碍 'X'。
 * 在每一步移动中，你可以向上、向左或者左上方移动，可以移动的前提是到达的格子没有障碍。
 * 一条路径的 「得分」 定义为：路径上所有数字的和。
 * 请你返回一个列表，包含两个整数：第一个整数是 「得分」 的最大值，第二个整数是得到最大得分的方案数，请把结果对 10^9 + 7 取余。
 * 如果没有任何路径可以到达终点，请返回 [0, 0] 。
 * 2 <= board.length == board[i].length <= 100
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int[] pathsWithMaxScore(List<String> board) {
        int m = board.size(), n = board.get(0).length();
        int[][] maxSum = new int[m + 1][n + 1];
        int[][] ways = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            Arrays.fill(maxSum[i], Integer.MIN_VALUE);
        }
        maxSum[0][0] = 0;
        ways[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = board.get(i).charAt(j);
                if (c == 'X') {
                    continue;
                }
                //左上、正上、正左
                maxSum[i + 1][j + 1] = Math.max(Math.max(maxSum[i][j], maxSum[i][j + 1]), maxSum[i + 1][j]);
                int s = maxSum[i + 1][j + 1];
                long w = 0;
                //如果路径和相同，则累加方案数（加法原理）
                if (maxSum[i][j] == s) {
                    w += ways[i][j];
                }
                if (maxSum[i][j + 1] == s) {
                    w += ways[i][j + 1];
                }
                if (maxSum[i + 1][j] == s) {
                    w += ways[i + 1][j];
                }
                ways[i + 1][j + 1] = (int) (w % MOD);
                if ('1' <= c && c <= '9') {
                    maxSum[i + 1][j + 1] += c - '0';
                }
            }
        }
        return maxSum[m][n] < 0 ? new int[]{0, 0} : new int[]{maxSum[m][n], ways[m][n]};
    }

}
