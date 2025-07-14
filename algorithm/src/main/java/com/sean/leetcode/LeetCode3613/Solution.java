package com.sean.leetcode.LeetCode3613;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-07-14 12:37
 * @Description https://leetcode.cn/problems/minimize-maximum-component-cost
 * 3613. 最小化连通分量的最大成本
 * 给你一个无向连通图，包含 n 个节点，节点编号从 0 到 n - 1，以及一个二维整数数组 edges，
 * 其中 edges[i] = [ui, vi, wi] 表示一条连接节点 ui 和节点 vi 的无向边，边权为 wi，另有一个整数 k。
 * 你可以从图中移除任意数量的边，使得最终的图中 最多 只包含 k 个连通分量。
 * 连通分量的 成本 定义为该分量中边权的 最大值 。
 * 如果一个连通分量没有边，则其代价为 0。
 * 请返回在移除这些边之后，在所有连通分量之中的 最大成本 的 最小可能值 。
 * 1 <= n <= 5 * 10^4
 * 0 <= edges.length <= 10^5
 * edges[i].length == 3
 * 0 <= ui, vi < n
 * 1 <= wi <= 10^6
 * 1 <= k <= n
 * 输入图是连通图。
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

    public int minCost(int n, int[][] edges, int k) {
        if (k == n) {
            return 0;
        }
        UnionFind unionFind = new UnionFind(n);
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            unionFind.union(u, v);
            if (unionFind.getSet() <= k) {
                return w;
            }
        }
        throw new RuntimeException("impossible");
    }

}
