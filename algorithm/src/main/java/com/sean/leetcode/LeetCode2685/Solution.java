package com.sean.leetcode.LeetCode2685;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-11 05:57
 * @Description: https://leetcode.cn/problems/count-the-number-of-complete-components
 * 2685. 统计完全连通分量的数量
 * 给你一个整数 n 。
 * 现有一个包含 n 个顶点的 无向 图，顶点按从 0 到 n - 1 编号。
 * 给你一个二维整数数组 edges 其中 edges[i] = [ai, bi] 表示顶点 ai 和 bi 之间存在一条 无向 边。
 * 返回图中 完全连通分量 的数量。
 * 如果在子图中任意两个顶点之间都存在路径，并且子图中没有任何一个顶点与子图外部的顶点共享边，则称其为 连通分量 。
 * 如果连通分量中每对节点之间都存在一条边，则称其为 完全连通分量 。
 * 1 <= n <= 50
 * 0 <= edges.length <= n * (n - 1) / 2
 * edges[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * 不存在重复的边
 */
public class Solution {

    public class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] cnt;
        private int[] help;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            cnt = new int[n];
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
                cnt[fx]++;
                return;
            }
            if (size[fx] >= size[fy]) {
                size[fx] += size[fy];
                parent[fy] = fx;
                cnt[fx] += cnt[fy] + 1;
            } else {
                size[fy] += size[fx];
                parent[fx] = fy;
                cnt[fy] += cnt[fx] + 1;
            }
        }

        public boolean check(int x) {
            int fx = find(x);
            int n = size[fx], c = cnt[fx];
            return c == n * (n - 1) / 2;
        }
    }

    public int countCompleteComponents(int n, int[][] edges) {
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            unionFind.union(u, v);
        }
        int res = 0;
        Set<Integer> checked = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int fi = unionFind.find(i);
            if (!checked.contains(fi)) {
                checked.add(fi);
                if (unionFind.check(fi)) {
                    res++;
                }
            }
        }
        return res;
    }

}
