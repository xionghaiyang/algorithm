package com.sean.leetcode.LeetCode2368;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-02 15:16
 * @Description: https://leetcode.cn/problems/reachable-nodes-with-restrictions/
 * 2368. 受限条件下可到达节点的数目
 * 现有一棵由 n 个节点组成的无向树，节点编号从 0 到 n - 1 ，共有 n - 1 条边。
 * 给你一个二维整数数组 edges ，长度为 n - 1 ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。
 * 另给你一个整数数组 restricted 表示 受限 节点。
 * 在不访问受限节点的前提下，返回你可以从节点 0 到达的 最多 节点数目。
 * 注意，节点 0 不 会标记为受限节点。
 */
public class Solution {

    List<Integer>[] g;
    boolean[] isRestricted;
    int res = 0;

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            g[from].add(to);
            g[to].add(from);
        }
        isRestricted = new boolean[n];
        for (int i : restricted) {
            isRestricted[i] = true;
        }
        dfs(0, -1);
        return res;
    }

    private void dfs(int x, int parent) {
        res++;
        for (int y : g[x]) {
            if (y == parent || isRestricted[y]) {
                continue;
            }
            dfs(y, x);
        }
    }

    class UnionFind {

        private int[] parent;
        private int[] size;
        private int[] help;
        private int sets;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            sets = n;
        }

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                if (size[fx] >= size[fy]) {
                    size[fx] += size[fy];
                    parent[fy] = parent[fx];
                } else {
                    size[fy] += size[fx];
                    parent[fx] = parent[fy];
                }
                sets--;
            }
        }

        private int find(int x) {
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

        public int sets() {
            return sets;
        }

        public int sets(int x) {
            return size[find(x)];
        }

    }

    public int reachableNodes1(int n, int[][] edges, int[] restricted) {
        boolean[] isRestricted = new boolean[n];
        for (int i : restricted) {
            isRestricted[i] = true;
        }
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            if (isRestricted[edge[0]] || isRestricted[edge[1]]) {
                continue;
            }
            unionFind.union(edge[0], edge[1]);
        }
        return unionFind.sets(0);
    }

}
