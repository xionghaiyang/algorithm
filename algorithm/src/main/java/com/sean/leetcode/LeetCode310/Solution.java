package com.sean.leetcode.LeetCode310;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-17 18:34
 * @Description: https://leetcode.cn/problems/minimum-height-trees/
 * 310. 最小高度树
 * 树是一个无向图，其中任何两个顶点只通过一条路径连接。
 * 换句话说，一个任何没有简单环路的连通图都是一棵树。
 * 给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。
 * 给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。
 * 可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。
 * 在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。
 * 请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。
 * 树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
 */
public class Solution {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        //找到与节点0最远的节点x
        int x = findLongestNode(0, parent, adj);
        //找到与节点x最远的节点y
        int y = findLongestNode(x, parent, adj);
        //求出节点x到节点y的路径
        List<Integer> path = new ArrayList<>();
        parent[x] = -1;
        while (y != -1) {
            path.add(y);
            y = parent[y];
        }
        int m = path.size();
        if (m % 2 == 0) {
            res.add(path.get(m / 2 - 1));
        }
        res.add(path.get(m / 2));
        return res;
    }

    private int findLongestNode(int u, int[] parent, List<Integer>[] adj) {
        int n = adj.length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[n];
        queue.offer(u);
        visit[u] = true;
        int node = -1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            node = cur;
            for (int v : adj[cur]) {
                if (!visit[v]) {
                    visit[v] = true;
                    parent[v] = cur;
                    queue.offer(v);
                }
            }
        }
        return node;
    }

    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        //找到与节点0最远的节点x
        int x = findLongestNode1(0, parent, adj);
        //找到与节点x最远的节点y
        int y = findLongestNode1(x, parent, adj);
        //求出节点x到节点y的路径
        List<Integer> path = new ArrayList<>();
        parent[x] = -1;
        while (y != -1) {
            path.add(y);
            y = parent[y];
        }
        int m = path.size();
        if (m % 2 == 0) {
            res.add(path.get(m / 2 - 1));
        }
        res.add(path.get(m / 2));
        return res;
    }

    private int findLongestNode1(int u, int[] parent, List<Integer>[] adj) {
        int n = adj.length;
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[u] = 0;
        dfs(u, dist, parent, adj);
        int maxDist = 0;
        int node = -1;
        for (int i = 0; i < n; i++) {
            if (dist[i] > maxDist) {
                maxDist = dist[i];
                node = i;
            }
        }
        return node;
    }

    private void dfs(int u, int[] dist, int[] parent, List<Integer>[] adj) {
        for (int v : adj[u]) {
            if (dist[v] < 0) {
                dist[v] = dist[u] + 1;
                parent[v] = u;
                dfs(v, dist, parent, adj);
            }
        }
    }

    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        int[] degree = new int[n];
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }
        int remainNodes = n;
        while (remainNodes > 2) {
            int size = queue.size();
            remainNodes -= size;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int v : adj[cur]) {
                    degree[v]--;
                    if (degree[v] == 1) {
                        queue.offer(v);
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }
        return res;
    }

}
