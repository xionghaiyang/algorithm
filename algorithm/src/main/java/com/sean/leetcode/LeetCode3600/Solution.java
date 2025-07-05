package com.sean.leetcode.LeetCode3600;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-07-05 18:50
 * @Description https://leetcode.cn/problems/maximize-spanning-tree-stability-with-upgrades
 * 3600. 升级后最大生成树稳定性
 * 给你一个整数 n，表示编号从 0 到 n - 1 的 n 个节点，以及一个 edges 列表，其中 edges[i] = [ui, vi, si, musti]：
 * ui 和 vi 表示节点 ui 和 vi 之间的一条无向边。
 * si 是该边的强度。
 * musti 是一个整数（0 或 1）。
 * 如果 musti == 1，则该边 必须 包含在生成树中，且 不能升级 。
 * 你还有一个整数 k，表示你可以执行的最多 升级 次数。
 * 每次升级会使边的强度 翻倍 ，且每条可升级边（即 musti == 0）最多只能升级一次。
 * 一个生成树的 稳定性 定义为其中所有边的 最小 强度。
 * 返回任何有效生成树可能达到的 最大 稳定性。
 * 如果无法连接所有节点，返回 -1。
 * 注意： 图的一个 生成树（spanning tree）是该图中边的一个子集，它满足以下条件：
 * 将所有节点连接在一起（即图是 连通的 ）。
 * 不 形成任何环。
 * 包含 恰好 n - 1 条边，其中 n 是图中节点的数量。
 * 2 <= n <= 10^5
 * 1 <= edges.length <= 10^5
 * edges[i] = [ui, vi, si, musti]
 * 0 <= ui, vi < n
 * ui != vi
 * 1 <= si <= 10^5
 * musti 是 0 或 1。
 * 0 <= k <= n
 * 没有重复的边。
 */
public class Solution {

    public class UnionFind {
        int[] parent;
        int[] help;
        int[] size;
        public int set;

        public UnionFind(int n) {
            parent = new int[n];
            help = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            set = n;
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

        public boolean union(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx == fy) {
                return false;
            }
            if (size[fx] > size[fy]) {
                parent[fy] = fx;
                size[fx] += size[fy];
            } else {
                parent[fx] = fy;
                size[fy] += size[fx];
            }
            set--;
            return true;
        }

        public int getSet() {
            return set;
        }
    }

    public int maxStability(int n, int[][] edges, int k) {
        //必选边并查集
        UnionFind mustUnionFind = new UnionFind(n);
        //全图并查集
        UnionFind allUnionFind = new UnionFind(n);
        int minS = Integer.MAX_VALUE, maxS = 0;
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], s = edge[2], must = edge[3];
            //必选边成环
            if (must > 0 && !mustUnionFind.union(u, v)) {
                return -1;
            }
            allUnionFind.union(u, v);
            minS = Math.min(minS, s);
            maxS = Math.max(maxS, s);
        }
        //图不互通
        if (allUnionFind.getSet() > 1) {
            return -1;
        }
        int left = minS, right = maxS * 2 + 1;
        while (left + 1 < right) {
            int mid = left + ((right - left) >> 1);
            if (check(mid, n, edges, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean check(int low, int n, int[][] edges, int k) {
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], s = edge[2], must = edge[3];
            //必选边的边权太小
            if (must > 0 && s < low) {
                return false;
            }
            if (must > 0 || s >= low) {
                unionFind.union(u, v);
            }
        }
        for (int[] edge : edges) {
            if (k == 0 || unionFind.getSet() == 1) {
                break;
            }
            int u = edge[0], v = edge[1], s = edge[2], must = edge[3];
            if (must == 0 && s < low && s * 2 >= low && unionFind.union(u, v)) {
                k--;
            }
        }
        return unionFind.getSet() == 1;
    }

    public int maxStability1(int n, int[][] edges, int k) {
        UnionFind mustUnionFind = new UnionFind(n);
        UnionFind allUnionFind = new UnionFind(n);
        int minS = Integer.MAX_VALUE;
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], s = edge[2], must = edge[3];
            if (must > 0) {
                //必选边成环
                if (!mustUnionFind.union(u, v)) {
                    return -1;
                }
                minS = Math.min(minS, s);
            }
            allUnionFind.union(u, v);
        }
        //图不互通
        if (allUnionFind.getSet() > 1) {
            return -1;
        }
        //只需选必选边
        if (mustUnionFind.getSet() == 1) {
            return minS;
        }
        Arrays.sort(edges, (a, b) -> b[2] - a[2]);
        List<Integer> list = new ArrayList<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], s = edge[2], must = edge[3];
            if (must == 0 && mustUnionFind.union(u, v)) {
                list.add(s);
            }
        }
        //答案为如下三者的最小值：
        //1.must = 1中的最小的边权
        //2.list中最小边权 * 2
        //3.list中第k+1小边权
        int m = list.size();
        int res = Math.min(minS, list.get(m - 1) * 2);
        if (k < m) {
            res = Math.min(res, list.get(m - 1 - k));
        }
        return res;
    }

}
