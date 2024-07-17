package com.sean.leetcode.LeetCode2959;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-07-17 07:33
 * @Description https://leetcode.cn/problems/number-of-possible-sets-of-closing-branches/
 * 2959. 关闭分部的可行集合数目
 * 一个公司在全国有 n 个分部，它们之间有的有道路连接。
 * 一开始，所有分部通过这些道路两两之间互相可以到达。
 * 公司意识到在分部之间旅行花费了太多时间，所以它们决定关闭一些分部（也可能不关闭任何分部），同时保证剩下的分部之间两两互相可以到达且最远距离不超过 maxDistance 。
 * 两个分部之间的 距离 是通过道路长度之和的 最小值 。
 * 给你整数 n ，maxDistance 和下标从 0 开始的二维整数数组 roads ，其中 roads[i] = [ui, vi, wi] 表示一条从 ui 到 vi 长度为 wi的 无向 道路。
 * 请你返回关闭分部的可行方案数目，满足每个方案里剩余分部之间的最远距离不超过 maxDistance。
 * 注意，关闭一个分部后，与之相连的所有道路不可通行。
 * 注意，两个分部之间可能会有多条道路。
 * 1 <= n <= 10
 * 1 <= maxDistance <= 10^5
 * 0 <= roads.length <= 1000
 * roads[i].length == 3
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 1 <= wi <= 1000
 * 一开始所有分部之间通过道路互相可以到达。
 */
public class Solution {

    private static final int INF = 0x3f3f3f3f;

    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int[][] dis = new int[n][n];
        boolean[] active = new boolean[n];
        int res = 0;
        for (int mask = 0; mask < 1 << n; mask++) {
            for (int i = 0; i < n; i++) {
                Arrays.fill(dis[i], INF);
                dis[0][0] = 0;
            }
            for (int i = 0; i < n; i++) {
                active[i] = ((mask >> i) & 1) == 1;
            }
            for (int[] road : roads) {
                int u = road[0];
                int v = road[1];
                int w = road[2];
                if (active[u] && active[v] && dis[u][v] > w) {
                    dis[u][v] = w;
                    dis[v][u] = w;
                }
            }
            for (int k = 0; k < n; k++) {
                if (active[k]) {
                    for (int i = 0; i < n; i++) {
                        if (active[i]) {
                            for (int j = i + 1; j < n; j++) {
                                if (active[j] && dis[i][j] > dis[i][k] + dis[k][j]) {
                                    dis[i][j] = dis[i][k] + dis[k][j];
                                    dis[j][i] = dis[i][k] + dis[k][j];
                                }
                            }
                        }
                    }
                }
            }
            int good = 1;
            for (int i = 0; i < n; i++) {
                if (active[i]) {
                    for (int j = i + 1; j < n; j++) {
                        if (active[j] && dis[i][j] > maxDistance) {
                            good = 0;
                            break;
                        }
                    }
                    if (good == 0) {
                        break;
                    }
                }
            }
            res += good;
        }
        return res;
    }

}
