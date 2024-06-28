package com.sean.leetcode.LeetCode2642;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-26 16:06
 * @Description: https://leetcode.cn/problems/design-graph-with-shortest-path-calculator/description/
 * 2642. 设计可以求最短路径的图类
 * 给你一个有 n 个节点的 有向带权 图，节点编号为 0 到 n - 1 。
 * 图中的初始边用数组 edges 表示，其中 edges[i] = [fromi, toi, edgeCosti] 表示从 fromi 到 toi 有一条代价为 edgeCosti 的边。
 * 请你实现一个 Graph 类：
 * Graph(int n, int[][] edges) 初始化图有 n 个节点，并输入初始边。
 * addEdge(int[] edge) 向边集中添加一条边，其中 edge = [from, to, edgeCost] 。数据保证添加这条边之前对应的两个节点之间没有有向边。
 * int shortestPath(int node1, int node2) 返回从节点 node1 到 node2 的路径 最小 代价。如果路径不存在，返回 -1 。一条路径的代价是路径中所有边代价之和。
 */
public class Graph1 {

    private int[][] dist;

    public Graph1(int n, int[][] edges) {
        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }
        for (int[] edge : edges) {
            dist[edge[0]][edge[1]] = edge[2];
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
    }

    public void addEdge(int[] edge) {
        int from = edge[0];
        int to = edge[1];
        int cost = edge[2];
        if (cost >= dist[from][to]) {
            return;
        }
        int n = dist.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][from] != Integer.MAX_VALUE && dist[to][j] != Integer.MAX_VALUE) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][from] + cost + dist[to][j]);
                }
            }
        }
    }

    public int shortestPath(int node1, int node2) {
        int res = dist[node1][node2];
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}