package com.sean.leetcode.LeetCode787;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-09-04 18:39
 * @Description https://leetcode.cn/problems/cheapest-flights-within-k-stops
 * 787. K 站中转内最便宜的航班
 * 有 n 个城市通过一些航班连接。
 * 给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。
 * 如果不存在这样的路线，则输出 -1。
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 10^4
 * 航班没有重复，且不存在自环
 * 0 <= src, dst, k < n
 * src != dst
 */
public class Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        final int INF = 10_000 * 101 + 1;
        int[][] dp = new int[k + 2][n];
        for (int i = 0; i < k + 2; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][src] = 0;
        for (int t = 1; t <= k + 1; t++) {
            for (int[] flight : flights) {
                int from = flight[0], to = flight[1], price = flight[2];
                dp[t][to] = Math.min(dp[t][to], dp[t - 1][from] + price);
            }
        }
        int res = INF;
        for (int t = 1; t <= k + 1; t++) {
            res = Math.min(res, dp[t][dst]);
        }
        return res == INF ? -1 : res;
    }

}
