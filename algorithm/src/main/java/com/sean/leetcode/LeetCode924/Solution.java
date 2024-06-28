package com.sean.leetcode.LeetCode924;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-16 19:43
 * @Description: https://leetcode.cn/problems/minimize-malware-spread/
 * 924. 尽量减少恶意软件的传播
 * 给出了一个由 n 个节点组成的网络，用 n × n 个邻接矩阵图 graph 表示。
 * 在节点网络中，当 graph[i][j] = 1 时，表示节点 i 能够直接连接到另一个节点 j。
 * 一些节点 initial 最初被恶意软件感染。
 * 只要两个节点直接连接，且其中至少一个节点受到恶意软件的感染，那么两个节点都将被恶意软件感染。
 * 这种恶意软件的传播将继续，直到没有更多的节点可以被这种方式感染。
 * 假设 M(initial) 是在恶意软件停止传播之后，整个网络中感染恶意软件的最终节点数。
 * 如果从 initial 中移除某一节点能够最小化 M(initial)， 返回该节点。
 * 如果有多个节点满足条件，就返回索引最小的节点。
 * 请注意，如果某个节点已从受感染节点的列表 initial 中删除，它以后仍有可能因恶意软件传播而受到感染。
 */
public class Solution {

    class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sets;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            int i = 0;
            while (parent[x] != x) {
                x = parent[x];
                help[i++] = x;
            }
            for (i--; i >= 0; i--) {
                parent[help[i]] = x;
            }
            return x;
        }

        public void union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy) {
                sets--;
                if (size[fx] >= size[fy]) {
                    size[fx] += size[fy];
                    parent[fy] = parent[fx];
                } else {
                    size[fy] += size[fx];
                    parent[fx] = parent[fy];
                }
            }
        }

        public int set() {
            return sets;
        }

        public int set(int x) {
            return size[find(x)];
        }

    }

    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        int[] counts = new int[n];
        for (int i : initial) {
            counts[unionFind.find(i)]++;
        }
        Arrays.sort(initial);
        int res = -1;
        int max = 0;
        for (int i : initial) {
            if (counts[unionFind.find(i)] == 1 && unionFind.set(i) > max) {
                max = Math.max(max, unionFind.set(i));
                res = i;
            }
        }
        return res != -1 ? res : initial[0];
    }

    public int minMalwareSpread1(int[][] graph, int[] initial) {
        int n = graph.length;
        int[] ids = new int[n];
        Map<Integer, Integer> idToSize = new HashMap<>();
        int id = 0;
        for (int i = 0; i < n; i++) {
            if (ids[i] == 0) {
                id++;
                int size = 1;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                ids[i] = id;
                while (!queue.isEmpty()) {
                    int u = queue.poll();
                    for (int v = 0; v < n; v++) {
                        if (ids[v] == 0 && graph[u][v] == 1) {
                            size++;
                            queue.offer(v);
                            ids[v] = id;
                        }
                    }
                }
                idToSize.put(id, size);
            }
        }
        Map<Integer, Integer> idToInitials = new HashMap<>();
        for (int u : initial) {
            idToInitials.put(ids[u], idToInitials.getOrDefault(ids[u], 0) + 1);
        }
        int res = n + 1, resRemoved = 0;
        for (int u : initial) {
            int removed = (idToInitials.get(ids[u]) == 1 ? idToSize.get(ids[u]) : 0);
            if (removed > resRemoved || (removed == resRemoved && u < res)) {
                res = u;
                resRemoved = removed;
            }
        }
        return res;
    }

}
