package com.sean.leetcode.LeetCode1928;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-10-08 14:20
 * @Description https://leetcode.cn/problems/minimum-cost-to-reach-destination-in-time
 * 1928. 规定时间内到达终点的最小花费
 * 一个国家有 n 个城市，城市编号为 0 到 n - 1 ，题目保证 所有城市 都由双向道路 连接在一起 。
 * 道路由二维整数数组 edges 表示，其中 edges[i] = [xi, yi, timei] 表示城市 xi 和 yi 之间有一条双向道路，耗费时间为 timei 分钟。
 * 两个城市之间可能会有多条耗费时间不同的道路，但是不会有道路两头连接着同一座城市。
 * 每次经过一个城市时，你需要付通行费。
 * 通行费用一个长度为 n 且下标从 0 开始的整数数组 passingFees 表示，其中 passingFees[j] 是你经过城市 j 需要支付的费用。
 * 一开始，你在城市 0 ，你想要在 maxTime 分钟以内 （包含 maxTime 分钟）到达城市 n - 1 。
 * 旅行的 费用 为你经过的所有城市 通行费之和 （包括 起点和终点城市的通行费）。
 * 给你 maxTime，edges 和 passingFees ，请你返回完成旅行的 最小费用 ，如果无法在 maxTime 分钟以内完成旅行，请你返回 -1 。
 */
public class Solution {

    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        //dp[t][i]表示恰好t分钟到达城市i需要的最少通行费总和
        int[][] dp = new int[maxTime + 1][n];
        for (int i = 0; i <= maxTime; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = passingFees[0];
        for (int t = 1; t <= maxTime; t++) {
            for (int[] edge : edges) {
                int i = edge[0], j = edge[1], cost = edge[2];
                if (cost <= t) {
                    if (dp[t - cost][j] != Integer.MAX_VALUE) {
                        dp[t][i] = Math.min(dp[t][i], dp[t - cost][j] + passingFees[i]);
                    }
                    if (dp[t - cost][i] != Integer.MAX_VALUE) {
                        dp[t][j] = Math.min(dp[t][j], dp[t - cost][i] + passingFees[j]);
                    }
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int t = 1; t <= maxTime; t++) {
            res = Math.min(res, dp[t][n - 1]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
