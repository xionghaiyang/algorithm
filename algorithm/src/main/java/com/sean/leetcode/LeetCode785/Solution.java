package com.sean.leetcode.LeetCode785;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author xionghaiyang
 * @Date 2025-09-08 21:29
 * @Description https://leetcode.cn/problems/is-graph-bipartite
 * 785. 判断二分图
 * 存在一个 无向图 ，图中有 n 个节点。
 * 其中每个节点都有一个介于 0 到 n - 1 之间的唯一编号。
 * 给你一个二维数组 graph ，其中 graph[u] 是一个节点数组，由节点 u 的邻接节点组成。
 * 形式上，对于 graph[u] 中的每个 v ，都存在一条位于节点 u 和节点 v 之间的无向边。
 * 该无向图同时具有以下属性：
 * 不存在自环（graph[u] 不包含 u）。
 * 不存在平行边（graph[u] 不包含重复值）。
 * 如果 v 在 graph[u] 内，那么 u 也应该在 graph[v] 内（该图是无向图）
 * 这个图可能不是连通图，也就是说两个节点 u 和 v 之间可能不存在一条连通彼此的路径。
 * 二分图 定义：如果能将一个图的节点集合分割成两个独立的子集 A 和 B ，并使图中的每一条边的两个节点一个来自 A 集合，一个来自 B 集合，就将这个图称为 二分图 。
 * 如果图是二分图，返回 true ；否则，返回 false 。
 * graph.length == n
 * 1 <= n <= 100
 * 0 <= graph[u].length < n
 * 0 <= graph[u][i] <= n - 1
 * graph[u] 不会包含 u
 * graph[u] 的所有值 互不相同
 * 如果 graph[u] 包含 v，那么 graph[v] 也会包含 u
 */
public class Solution {

    public class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
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
            if (size[fx] >= size[fy]) {
                size[fx] += size[fy];
                parent[fy] = fx;
            } else {
                size[fy] += size[fx];
                parent[fx] = fy;
            }
        }

        private boolean isSameSet(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            return fx == fy;
        }
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            int[] v = graph[i];
            for (int j = 1; j < v.length; j++) {
                unionFind.union(v[0], v[j]);
            }
            if (v.length > 0 && unionFind.isSameSet(i, v[0])) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartite1(int[][] graph) {
        int n = graph.length;
        //visited[i]：值为0表示未访问，-1或者1表示两种不同的颜色
        int[] visited = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (visited[i] != 0) {
                continue;
            }
            queue.offer(i);
            visited[i] = 1;
            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int v : graph[u]) {
                    if (visited[u] == visited[v]) {
                        return false;
                    }
                    if (visited[v] == 0) {
                        visited[v] = -visited[u];
                        queue.offer(v);
                    }
                }
            }
        }
        return true;
    }

    public boolean isBipartite2(int[][] graph) {
        int n = graph.length;
        int[] visited = new int[n];
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0 && !dfs(graph, i, 1, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int u, int color, int[] visited) {
        if (visited[u] != 0) {
            return visited[u] == color;
        }
        visited[u] = color;
        for (int v : graph[u]) {
            if (!dfs(graph, v, -color, visited)) {
                return false;
            }
        }
        return true;
    }

}
