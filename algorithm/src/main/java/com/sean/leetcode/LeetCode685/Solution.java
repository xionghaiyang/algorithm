package com.sean.leetcode.LeetCode685;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-09-07 12:25
 * @Description https://leetcode.cn/problems/redundant-connection-ii
 * 685. 冗余连接 II
 * 在本问题中，有根树指满足以下条件的 有向 图。
 * 该树只有一个根节点，所有其他节点都是该根节点的后继。
 * 该树除了根节点之外的每一个节点都有且只有一个父节点，而根节点没有父节点。
 * 输入一个有向图，该图由一个有着 n 个节点（节点值不重复，从 1 到 n）的树及一条附加的有向边构成。
 * 附加的边包含在 1 到 n 中的两个不同顶点间，这条附加的边不属于树中已存在的边。
 * 结果图是一个以边组成的二维数组 edges 。
 * 每个元素是一对 [ui, vi]，用以表示 有向 图中连接顶点 ui 和顶点 vi 的边，其中 ui 是 vi 的一个父节点。
 * 返回一条能删除的边，使得剩下的图是有 n 个节点的有根树。
 * 若有多个答案，返回最后出现在给定二维数组的答案。
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 */
public class Solution {

    public class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int n;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            this.n = n;
            init();
        }

        public void init() {
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
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

        public boolean isSameSet(int x, int y) {
            return find(x) == find(y);
        }
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        //对于树来说入度是1，在树中加了一条有向边之后可能的情况如下:
        //1.存在节点入度为2
        //2.存在有向环
        int n = edges.length;
        //入度
        int[] inDegree = new int[n + 1];
        //入度为2的集合
        List<Integer> list = new ArrayList<>();
        UnionFind unionFind = new UnionFind(n + 1);
        for (int[] edge : edges) {
            inDegree[edge[1]]++;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (inDegree[edges[i][1]] == 2) {
                list.add(i);
            }
        }
        if (!list.isEmpty()) {
            //入度为2是由两条边引起的，要么删靠后那条要么删靠前那条，优先尝试删靠后那条
            if (removeEdge(edges, list.get(0), unionFind)) {
                return edges[list.get(0)];
            }
            return edges[list.get(1)];
        }
        //找到有向环删除构成环的那条边
        return removeCycleEdge(edges, unionFind);
    }

    private boolean removeEdge(int[][] edges, Integer delEdge, UnionFind unionFind) {
        unionFind.init();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            if (i != delEdge) {
                //有环
                if (unionFind.isSameSet(edge[0], edge[1])) {
                    return false;
                }
                unionFind.union(edge[0], edge[1]);
            }
        }
        return true;
    }

    private int[] removeCycleEdge(int[][] edges, UnionFind unionFind) {
        unionFind.init();
        for (int[] edge : edges) {
            //如果u和v的父节点一致，那么加入边[u,v]则会产生环
            if (unionFind.isSameSet(edge[0], edge[1])) {
                return edge;
            }
            unionFind.union(edge[0], edge[1]);
        }
        return null;
    }

}
