package com.sean.leetcode.LeetCode3608;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-07-07 18:18
 * @Description https://leetcode.cn/problems/minimum-time-for-k-connected-components
 * 3608. 包含 K 个连通分量需要的最小时间
 * 给你一个整数 n，表示一个包含 n 个节点（从 0 到 n - 1 编号）的无向图。
 * 该图由一个二维数组 edges 表示，其中 edges[i] = [ui, vi, timei] 表示一条连接节点 ui 和节点 vi 的无向边，该边会在时间 timei 被移除。
 * 同时，另给你一个整数 k。
 * 最初，图可能是连通的，也可能是非连通的。
 * 你的任务是找到一个 最小 的时间 t，使得在移除所有满足条件 time <= t 的边之后，该图包含 至少 k 个连通分量。
 * 返回这个 最小 时间 t。
 * 连通分量 是图的一个子图，其中任意两个顶点之间都存在路径，且子图中的任意顶点均不与子图外的顶点共享边。
 * 1 <= n <= 10^5
 * 0 <= edges.length <= 10^5
 * edges[i] = [ui, vi, timei]
 * 0 <= ui, vi < n
 * ui != vi
 * 1 <= timei <= 10^9
 * 1 <= k <= n
 * 不存在重复的边。
 */
public class Solution {

    public class UnionFind {
        private int[] parent;
        private int[] help;
        private int[] size;
        private int set;

        public UnionFind(int n) {
            parent = new int[n];
            help = new int[n];
            size = new int[n];
            set = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            int index = 0;
            while (x != parent[x]) {
                help[index++] = x;
                x = parent[x];
            }
            for (index--; index >= 0; index--) {
                parent[help[index]] = x;
            }
            return x;
        }

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) {
                return;
            }
            set--;
            if (size[fx] > size[fy]) {
                parent[fy] = fx;
                size[fx] += size[fy];
            } else {
                parent[fx] = fy;
                size[fy] += size[fx];
            }
        }

        public int getSet() {
            return set;
        }
    }

    public int minTime(int n, int[][] edges, int k) {
        UnionFind unionFind = new UnionFind(n);
        Arrays.sort(edges, (a, b) -> b[2] - a[2]);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
            if (unionFind.getSet() < k) {
                return edge[2];
            }
        }
        return 0;
    }

}
