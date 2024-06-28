package com.sean.leetcode.LeetCode2699;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-12 10:32
 * @Description: https://leetcode.cn/problems/modify-graph-edge-weights/
 * 2699. 修改图中的边权
 * 给你一个 n 个节点的 无向带权连通 图，节点编号为 0 到 n - 1 ，
 * 再给你一个整数数组 edges ，其中 edges[i] = [ai, bi, wi] 表示节点 ai 和 bi 之间有一条边权为 wi 的边。
 * 部分边的边权为 -1（wi = -1），其他边的边权都为 正 数（wi > 0）。
 * 你需要将所有边权为 -1 的边都修改为范围 [1, 2 * 109] 中的 正整数 ，使得从节点 source 到节点 destination 的 最短距离 为整数 target 。
 * 如果有 多种 修改方案可以使 source 和 destination 之间的最短距离等于 target ，你可以返回任意一种方案。
 * 如果存在使 source 到 destination 最短距离为 target 的方案，
 * 请你按任意顺序返回包含所有边的数组（包括未修改边权的边）。如果不存在这样的方案，请你返回一个 空数组 。
 * 注意：你不能修改一开始边权为正数的边。
 */
public class Solution {

    public int[][] modifiedGraphEdges1(int n, int[][] edges, int source, int destination, int target) {
        int k = 0;
        for (int[] edge : edges) {
            if (edge[2] == -1) {
                k++;
            }
        }
        if (dijkstra(source, destination, construct(n, edges, 0, target)) > target) {
            return new int[0][];
        }
        if (dijkstra(source, destination, construct(n, edges, (long) k * (target - 1), target)) < target) {
            return new int[0][];
        }
        long left = 0, right = (long) k * (target - 1), res = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (dijkstra(source, destination, construct(n, edges, mid, target)) >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        for (int[] edge : edges) {
            if (edge[2] == -1) {
                if (res >= target - 1) {
                    edge[2] = target;
                    res -= target - 1;
                } else {
                    edge[2] = (int) (1 + res);
                    res = 0;
                }
            }
        }
        return edges;
    }

    private long dijkstra(int source, int destination, int[][] adjMatrix) {
        //朴素的dijkstra算法
        //adjMatrix是一个邻接矩阵
        int n = adjMatrix.length;
        long[] dist = new long[n];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        boolean[] used = new boolean[n];
        dist[source] = 0;
        for (int round = 0; round < n - 1; round++) {
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!used[i] && (u == -1 || dist[i] < dist[u])) {
                    u = i;
                }
            }
            used[u] = true;
            for (int v = 0; v < n; v++) {
                if (!used[v] && adjMatrix[u][v] != -1) {
                    dist[v] = Math.min(dist[v], dist[u] + adjMatrix[u][v]);
                }
            }
        }
        return dist[destination];
    }

    private int[][] construct(int n, int[][] edges, long idx, int target) {
        //需要构造出第idx种不同的边权情况，返回一个邻接矩阵
        int[][] adjMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(adjMatrix[i], -1);
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            if (w != -1) {
                adjMatrix[u][v] = adjMatrix[v][u] = w;
            } else {
                if (idx >= target - 1) {
                    adjMatrix[u][v] = adjMatrix[v][u] = target;
                    idx -= (target - 1);
                } else {
                    adjMatrix[u][v] = adjMatrix[v][u] = (int) (1 + idx);
                    idx = 0;
                }
            }
        }
        return adjMatrix;
    }

    long[] fromDestination;
    int target;

    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        this.target = target;
        int[][] adjMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(adjMatrix[i], -1);
        }
        //邻接矩阵种存储边的下标
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adjMatrix[u][v] = adjMatrix[v][u] = i;
        }
        fromDestination = dijkstra(0, destination, edges, adjMatrix);
        if (fromDestination[source] > target) {
            return new int[0][];
        }
        long[] fromSource = dijkstra(1, source, edges, adjMatrix);
        if (fromSource[destination] != target) {
            return new int[0][];
        }
        return edges;
    }

    private long[] dijkstra(int op, int source, int[][] edges, int[][] adjMatrix) {
        //朴素的dijkstra算法
        //adjMatrix是一个邻接矩阵
        int n = adjMatrix.length;
        long[] dist = new long[n];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        boolean[] used = new boolean[n];
        dist[source] = 0;
        for (int round = 0; round < n - 1; round++) {
            int u = -1;
            for (int i = 0; i < n; i++) {
                if (!used[i] && (u == -1 || dist[i] < dist[u])) {
                    u = i;
                }
            }
            used[u] = true;
            for (int v = 0; v < n; v++) {
                if (!used[v] && adjMatrix[u][v] != -1) {
                    if (edges[adjMatrix[u][v]][2] != -1) {
                        dist[v] = Math.min(dist[v], dist[u] + edges[adjMatrix[u][v]][2]);
                    } else {
                        if (op == 0) {
                            dist[v] = Math.min(dist[v], dist[u] + 1);
                        } else {
                            int modify = (int) (target - dist[u] - fromDestination[v]);
                            if (modify > 0) {
                                dist[v] = Math.min(dist[v], dist[u] + modify);
                                edges[adjMatrix[u][v]][2] = modify;
                            } else {
                                edges[adjMatrix[u][v]][2] = target;
                            }
                        }
                    }
                }
            }
        }
        return dist;
    }

}
