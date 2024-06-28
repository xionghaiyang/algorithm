package com.sean.leetcode.LeetCode928;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-17 09:07
 * @Description: https://leetcode.cn/problems/minimize-malware-spread-ii/
 * 928. 尽量减少恶意软件的传播 II
 * 给定一个由 n 个节点组成的网络，用 n x n 个邻接矩阵 graph 表示。
 * 在节点网络中，只有当 graph[i][j] = 1 时，节点 i 能够直接连接到另一个节点 j。
 * 一些节点 initial 最初被恶意软件感染。
 * 只要两个节点直接连接，且其中至少一个节点受到恶意软件的感染，那么两个节点都将被恶意软件感染。
 * 这种恶意软件的传播将继续，直到没有更多的节点可以被这种方式感染。
 * 假设 M(initial) 是在恶意软件停止传播之后，整个网络中感染恶意软件的最终节点数。
 * 我们可以从 initial 中删除一个节点，并完全移除该节点以及从该节点到任何其他节点的任何连接。
 * 请返回移除后能够使 M(initial) 最小化的节点。
 * 如果有多个节点满足条件，返回索引 最小的节点 。
 */
public class Solution {

    int[][] graph;
    int n;
    int[] clean;

    public int minMalwareSpread(int[][] graph, int[] initial) {
        this.graph = graph;
        n = graph.length;
        //构建一个集合，代表不在initial中的节点（值为1）
        clean = new int[n];
        Arrays.fill(clean, 1);
        for (int x : initial) {
            clean[x] = 0;
        }
        //initial中的节点x会感染哪些不在initial中的节点y
        //换句话说不在initial中的节点y会被哪些再initial中的节点x感染
        ArrayList<Integer>[] inflectBy = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            inflectBy[i] = new ArrayList<>();
        }
        for (int x : initial) {
            Set<Integer> set = new HashSet<>();
            dfs(x, set);
            for (int y : set) {
                inflectBy[y].add(x);
            }
        }
        //哪些不在initial中的节点y只被一个在initial中的节点x感染（initial中的节点x会感染几个节点）
        int[] contribution = new int[n];
        for (int y = 0; y < n; y++) {
            if (inflectBy[y].size() == 1) {
                contribution[inflectBy[y].get(0)]++;
            }
        }
        Arrays.sort(initial);
        int res = initial[0];
        int resSize = -1;
        for (int x : initial) {
            int score = contribution[x];
            if (score > resSize) {
                res = x;
                resSize = score;
            }
        }
        return res;
    }

    private void dfs(int x, Set<Integer> set) {
        for (int y = 0; y < n; y++) {
            if (graph[x][y] == 1 && clean[y] == 1 && !set.contains(y)) {
                set.add(y);
                dfs(y, set);
            }
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
            sets = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            int i = 0;
            while (parent[x] != x) {
                help[i++] = x;
                x = parent[x];
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
                    parent[fy] = parent[fx];
                    size[fx] += size[fy];
                } else {
                    parent[fx] = parent[fy];
                    size[fy] += size[fx];
                }
            }
        }

        public int sets() {
            return sets;
        }

        public int sets(int x) {
            return size[find(x)];
        }

    }

    public int minMalwareSpread1(int[][] graph, int[] initial) {
        int n = graph.length;
        UnionFind unionFind = new UnionFind(n);
        //如果一个节点u不在initial中，则clean[u] == 1
        int[] clean = new int[n];
        Arrays.fill(clean, 1);
        for (int x : initial) {
            clean[x] = 0;
        }
        for (int u = 0; u < n; u++) {
            if (clean[u] == 1) {
                for (int v = 0; v < n; v++) {
                    if (clean[v] == 1 && graph[u][v] == 1) {
                        unionFind.union(u, v);
                    }
                }
            }
        }
        int[] count = new int[n];
        HashMap<Integer, Set<Integer>> nodeToCompo = new HashMap<>();
        for (int u : initial) {
            Set<Integer> components = new HashSet<>();
            for (int v = 0; v < n; v++) {
                if (clean[v] == 1 && graph[u][v] == 1) {
                    components.add(unionFind.find(v));
                }
            }
            nodeToCompo.put(u, components);
            for (int c : components) {
                count[c]++;
            }
        }
        int res = -1;
        int resSize = -1;
        for (int u : nodeToCompo.keySet()) {
            Set<Integer> components = nodeToCompo.get(u);
            int score = 0;
            for (int c : components) {
                if (count[c] == 1) {
                    score += unionFind.sets(c);
                }
            }
            if (score > resSize || (score == resSize && u < res)) {
                resSize = score;
                res = u;
            }
        }
        return res;
    }


}
