package com.sean.leetcode.LeetCode3515;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-29 09:40
 * @Description: https://leetcode.cn/problems/shortest-path-in-a-weighted-tree
 * 3515. 带权树中的最短路径
 * 给你一个整数 n 和一个以节点 1 为根的无向带权树，该树包含 n 个编号从 1 到 n 的节点。
 * 它由一个长度为 n - 1 的二维数组 edges 表示，其中 edges[i] = [ui, vi, wi] 表示一条从节点 ui 到 vi 的无向边，权重为 wi。
 * 同时给你一个二维整数数组 queries，长度为 q，其中每个 queries[i] 为以下两种之一：
 * [1, u, v, w'] – 更新 节点 u 和 v 之间边的权重为 w'，其中 (u, v) 保证是 edges 中存在的边。
 * [2, x] – 计算 从根节点 1 到节点 x 的 最短 路径距离。
 * 返回一个整数数组 answer，其中 answer[i] 是对于第 i 个 [2, x] 查询，从节点 1 到 x 的最短路径距离。
 * 1 <= n <= 10^5
 * edges.length == n - 1
 * edges[i] == [ui, vi, wi]
 * 1 <= ui, vi <= n
 * 1 <= wi <= 10^4
 * 输入保证 edges 构成一棵合法的树。
 * 1 <= queries.length == q <= 10^5
 * queries[i].length == 2 或 4
 * queries[i] == [1, u, v, w']，或者
 * queries[i] == [2, x]
 * 1 <= u, v, x <= n
 * (u, v) 一定是 edges 中的一条边。
 * 1 <= w' <= 10^4
 */
public class Solution {

    public class FenwickTree {
        private int[] tree;

        public FenwickTree(int n) {
            tree = new int[n + 1];
        }

        //a[i]增加val
        //1<=i<=n
        public void update(int i, int val) {
            while (i < tree.length) {
                tree[i] += val;
                i += i & -i;
            }
        }

        //求前缀和a[1] + ...+ a[i]
        //1 <= i <= n
        public int pre(int i) {
            int res = 0;
            while (i > 0) {
                res += tree[i];
                i &= i - 1;
            }
            return res;
        }
    }

    private int clock = 0;

    public int[] treeQueries(int n, int[][] edges, int[][] queries) {
        List<Integer>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            g[u].add(v);
            g[v].add(u);
        }
        int[] in = new int[n + 1];
        int[] out = new int[n + 1];
        dfs(g, in, out, 1, 0);
        int[] weight = new int[n + 1];
        FenwickTree diff = new FenwickTree(n);
        for (int[] edge : edges) {
            update(in, out, weight, diff, edge[0], edge[1], edge[2]);
        }
        List<Integer> list = new ArrayList<>();
        for (int[] query : queries) {
            if (query[0] == 1) {
                update(in, out, weight, diff, query[1], query[2], query[3]);
            } else {
                list.add(diff.pre(in[query[1]]));
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void dfs(List<Integer>[] g, int[] in, int[] out, int u, int p) {
        in[u] = ++clock;
        for (int v : g[u]) {
            if (v != p) {
                dfs(g, in, out, v, u);
            }
        }
        out[u] = clock;
    }

    private void update(int[] in, int[] out, int[] weight, FenwickTree diff, int u, int v, int w) {
        //保证v是u的儿子
        if (in[u] > in[v]) {
            v = u;
        }
        int delta = w - weight[v];
        weight[v] = w;
        diff.update(in[v], delta);
        diff.update(out[v] + 1, -delta);
    }

}
