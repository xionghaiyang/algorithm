package com.sean.leetcode.LeetCode3559;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-12 05:20
 * @Description: https://leetcode.cn/problems/number-of-ways-to-assign-edge-weights-ii
 * 3559. 给边赋权值的方案数 II
 * 给你一棵有 n 个节点的无向树，节点从 1 到 n 编号，树以节点 1 为根。
 * 树由一个长度为 n - 1 的二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示在节点 ui 和 vi 之间有一条边。
 * 一开始，所有边的权重为 0。
 * 你可以将每条边的权重设为 1 或 2。
 * 两个节点 u 和 v 之间路径的 代价 是连接它们路径上所有边的权重之和。
 * 给定一个二维整数数组 queries。
 * 对于每个 queries[i] = [ui, vi]，计算从节点 ui 到 vi 的路径中，使得路径代价为 奇数 的权重分配方式数量。
 * 返回一个数组 answer，其中 answer[i] 表示第 i 个查询的合法赋值方式数量。
 * 由于答案可能很大，请对每个 answer[i] 取模 10^9 + 7。
 * 注意： 对于每个查询，仅考虑 ui 到 vi 路径上的边，忽略其他边。
 * 2 <= n <= 10^5
 * edges.length == n - 1
 * edges[i] == [ui, vi]
 * 1 <= queries.length <= 10^5
 * queries[i] == [ui, vi]
 * 1 <= ui, vi <= n
 * edges 表示一棵合法的树。
 */
public class Solution {

    public class LCA {
        private int n;
        private int m;
        private List<Integer>[] g;
        private int[][] f;
        private int[] d;

        public LCA(int[][] edges, int root) {
            n = edges.length + 1;
            m = (int) (Math.log(n) / Math.log(2)) + 1;
            g = new ArrayList[n + 1];
            f = new int[n + 1][m];
            d = new int[n + 1];
            Arrays.setAll(g, k -> new ArrayList<>());
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1];
                g[u].add(v);
                g[v].add(u);
            }
            dfs(root, 0);
            for (int i = 1; i < m; i++) {
                for (int x = 1; x <= n; x++) {
                    f[x][i] = f[f[x][i - 1]][i - 1];
                }
            }
        }

        private void dfs(int x, int fa) {
            f[x][0] = fa;
            for (int y : g[x]) {
                if (y == fa) {
                    continue;
                }
                d[y] = d[x] + 1;
                dfs(y, x);
            }
        }

        public int lca(int x, int y) {
            if (d[x] > d[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            for (int i = m - 1; i >= 0; i--) {
                if (d[x] <= d[f[y][i]]) {
                    y = f[y][i];
                }
            }
            if (x == y) {
                return x;
            }
            for (int i = m - 1; i >= 0; i--) {
                if (f[y][i] != f[x][i]) {
                    x = f[x][i];
                    y = f[y][i];
                }
            }
            return f[x][0];
        }

        public int dis(int x, int y) {
            return d[x] + d[y] - d[lca(x, y)] * 2;
        }
    }

    private static final int MOD = 1_000_000_007;
    private static final int N = 100_010;
    private static int[] p2 = new int[N];

    static {
        p2[0] = 1;
        for (int i = 1; i < N; i++) {
            p2[i] = (int) ((long) p2[i - 1] * 2 % MOD);
        }
    }

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        LCA lca = new LCA(edges, 1);
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int x = queries[i][0], y = queries[i][1];
            if (x != y) {
                res[i] = p2[lca.dis(x, y) - 1];
            }
        }
        return res;
    }

}
