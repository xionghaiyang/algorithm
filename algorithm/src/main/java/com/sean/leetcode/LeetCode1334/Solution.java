package com.sean.leetcode.LeetCode1334;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-11-14 20:29
 * @Description: https://leetcode.cn/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
 * 1334. 阈值距离内邻居最少的城市
 * 有 n 个城市，按从 0 到 n-1 编号。
 * 给你一个边数组 edges，其中 edges[i] = [fromi, toi, weighti] 代表 fromi 和 toi 两个城市之间的双向加权边，距离阈值是一个整数 distanceThreshold。
 * 返回能通过某些路径到达其他城市数目最少、且路径距离 最大 为 distanceThreshold 的城市。
 * 如果有多个这样的城市，则返回编号最大的城市。
 * 注意，连接城市 i 和 j 的路径的距离等于沿该路径的所有边的权重之和。
 */
public class Solution {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[] res = {Integer.MAX_VALUE / 2, -1};
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE / 2);
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            map[from][to] = weight;
            map[to][from] = weight;
        }
        for (int k = 0; k < n; k++) {
            map[k][k] = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (map[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= res[0]) {
                res[0] = cnt;
                res[1] = i;
            }
        }
        return res[1];
    }

    public int findTheCity1(int n, int[][] edges, int distanceThreshold) {
        int[] res = {Integer.MAX_VALUE / 2, -1};
        int[][] dis = new int[n][n];
        boolean[][] visit = new boolean[n][n];
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE / 2);
            Arrays.fill(map[i], Integer.MAX_VALUE / 2);
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            map[from][to] = weight;
            map[to][from] = weight;
        }
        for (int i = 0; i < n; i++) {
            dis[i][i] = 0;
            for (int j = 0; j < n; j++) {
                int t = -1;
                for (int k = 0; k < n; k++) {
                    if (!visit[i][k] && (t == -1 || dis[i][k] < dis[i][t])) {
                        t = k;
                    }
                }
                for (int k = 0; k < n; k++) {
                    dis[i][k] = Math.min(dis[i][k], dis[i][t] + map[t][k]);
                }
                visit[i][t] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (dis[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= res[0]) {
                res[0] = cnt;
                res[1] = i;
            }
        }
        return res[1];
    }

}
