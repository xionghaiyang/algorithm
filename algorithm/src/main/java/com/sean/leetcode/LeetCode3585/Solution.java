package com.sean.leetcode.LeetCode3585;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-06-19 09:20
 * @Description https://leetcode.cn/problems/find-weighted-median-node-in-tree/
 * 3585. 树中找到带权中位节点
 * 给你一个整数 n，以及一棵 无向带权 树，根节点为节点 0，树中共有 n 个节点，编号从 0 到 n - 1。
 * 该树由一个长度为 n - 1 的二维数组 edges 表示，其中 edges[i] = [ui, vi, wi] 表示存在一条从节点 ui 到 vi 的边，权重为 wi。
 * 带权中位节点 定义为从 ui 到 vi 路径上的 第一个 节点 x，使得从 ui 到 x 的边权之和 大于等于 该路径总权值和的一半。
 * 给你一个二维整数数组 queries。
 * 对于每个 queries[j] = [uj, vj]，求出从 uj 到 vj 路径上的带权中位节点。
 * 返回一个数组 ans，其中 ans[j] 表示查询 queries[j] 的带权中位节点编号。
 * 2 <= n <= 10^5
 * edges.length == n - 1
 * edges[i] == [ui, vi, wi]
 * 0 <= ui, vi < n
 * 1 <= wi <= 10^9
 * 1 <= queries.length <= 10^5
 * queries[j] == [uj, vj]
 * 0 <= uj, vj < n
 * 输入保证 edges 表示一棵合法的树。
 */
public class Solution {

    // 邻接表存储树结构，每个节点保存相邻节点和边权
    private List<int[]>[] g;
    // 记录每个节点的深度
    private int[] depth;
    // 记录从根节点到每个节点的累计边权
    private long[] distances;
    // 记录每个节点的父节点
    private int[] parents;
    // 倍增法提升表
    private int[][] lifting;

    public int[] findMedian(int n, int[][] edges, int[][] queries) {
        // 初始化邻接表
        g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            g[u].add(new int[]{v, w});
            g[v].add(new int[]{u, w});
        }

        // 初始化深度、距离和父节点数组
        depth = new int[n];
        distances = new long[n];
        parents = new int[n];
        process(0, 0, 0, 0);

        // 构建倍增法提升表
        List<int[]> liftingList = new ArrayList<>();
        liftingList.add(parents);
        boolean isChanged = true;
        while (isChanged) {
            int[] cur = new int[n];
            isChanged = false;
            for (int i = 0; i < n; i++) {
                cur[i] = parents[parents[i]];
                if (cur[i] != parents[i]) {
                    isChanged = true;
                }
            }
            liftingList.add(cur);
            parents = cur;
        }
        lifting = liftingList.toArray(new int[0][]);

        // 处理查询
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int u = queries[i][0], v = queries[i][1];
            if (u == v) {
                res[i] = u;
                continue;
            }
            // 找到最近公共祖先
            int lca = getLca(u, v);
            // 计算u到lca和v到lca的距离
            long distance1 = distances[u] - distances[lca];
            long distance2 = distances[v] - distances[lca];
            int result = -1;
            // 根据距离决定查找方向
            if (distance1 >= distance2) {
                long target = (distance1 + distance2 + 1) >> 1;
                result = nodeUp(u, distances[u] - target);
                result = lifting[0][result];
            } else {
                long target = (distance1 + distance2) >> 1;
                result = nodeUp(v, distances[v] - (target + 1));
            }
            res[i] = result;
        }
        return res;
    }

    // 深度优先遍历预处理树结构
    private void process(int u, int p, int step, long distance) {
        depth[u] = step;
        distances[u] = distance;
        parents[u] = p;
        for (int[] next : g[u]) {
            int v = next[0], w = next[1];
            if (v != p) {
                process(v, u, step + 1, distance + w);
            }
        }
    }

    // 使用倍增法查找最近公共祖先
    private int getLca(int u, int v) {
        if (depth[u] > depth[v]) {
            return getLca(v, u);
        }
        // 将v提升到与u相同深度
        int diff = depth[v] - depth[u];
        for (int i = 0; diff > 0; i++, diff >>= 1) {
            if ((diff & 1) == 1) {
                v = lifting[i][v];
            }
        }
        if (u != v) {
            // 同时提升u和v直到找到公共祖先
            int k = lifting.length;
            for (int i = k - 1; i >= 0; i--) {
                int[] parent = lifting[i];
                if (parent[u] != parent[v]) {
                    u = parent[u];
                    v = parent[v];
                }
            }
            int[] parent = lifting[0];
            u = parent[u];
            v = parent[v];
        }
        return u;
    }

    // 从节点u向上查找满足条件的节点
    private int nodeUp(int u, long distance) {
        long distanceu = distances[u];
        for (int i = lifting.length - 1; i >= 0; i--) {
            int p = lifting[i][u];
            if (distances[p] <= distance) {
                continue;
            }
            u = p;
        }
        return u;
    }

}
