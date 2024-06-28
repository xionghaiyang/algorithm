package com.sean.leetcode.LeetCode1584;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-18 16:11
 * @Description: https://leetcode.cn/problems/min-cost-to-connect-all-points/
 * 1584. 连接所有点的最小费用
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 * 请你返回将所有点连接的最小总费用。
 * 只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 */
public class Solution {

    class UnionFind {
        int n;
        int[] parent;
        int[] size;

        public UnionFind(int n) {
            this.n = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            size = new int[n];
            Arrays.fill(size, 1);
        }

        public int find(int x) {
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }

        public boolean union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) {
                return false;
            }
            if (size[fx] < size[fy]) {
                int temp = fx;
                fx = fy;
                fy = temp;
            }
            size[fx] += size[fy];
            parent[fy] = fx;
            return true;
        }
    }

    class Edge {
        int dist;
        int x;
        int y;

        public Edge(int dist, int x, int y) {
            this.dist = dist;
            this.x = x;
            this.y = y;
        }
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        UnionFind unionFind = new UnionFind(n);
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new Edge(dist(points, i, j), i, j));
            }
        }
        Collections.sort(edges, (a, b) -> a.dist - b.dist);
        int res = 0, count = 1;
        for (Edge edge : edges) {
            int dist = edge.dist;
            int x = edge.x;
            int y = edge.y;
            if (unionFind.union(x, y)) {
                res += dist;
                count++;
                if (count == n) {
                    break;
                }
            }
        }
        return res;
    }

    private int dist(int[][] points, int x, int y) {
        return Math.abs(points[x][0] - points[y][0]) + Math.abs(points[x][1] - points[y][1]);
    }

}
