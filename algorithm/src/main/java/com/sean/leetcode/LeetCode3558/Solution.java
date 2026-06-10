package com.sean.leetcode.LeetCode3558;

import java.util.*;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-11 06:08
 * @Description: https://leetcode.cn/problems/number-of-ways-to-assign-edge-weights-i
 * 3558. 给边赋权值的方案数 I
 * 给你一棵 n 个节点的无向树，节点从 1 到 n 编号，树以节点 1 为根。
 * 树由一个长度为 n - 1 的二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示在节点 ui 和 vi 之间有一条边。
 * 一开始，所有边的权重为 0。
 * 你可以将每条边的权重设为 1 或 2。
 * 两个节点 u 和 v 之间路径的 代价 是连接它们路径上所有边的权重之和。
 * 选择任意一个 深度最大 的节点 x。
 * 返回从节点 1 到 x 的路径中，边权重之和为 奇数 的赋值方式数量。
 * 由于答案可能很大，返回它对 10^9 + 7 取模的结果。
 * 注意： 忽略从节点 1 到节点 x 的路径外的所有边。
 * 2 <= n <= 10^5
 * edges.length == n - 1
 * edges[i] == [ui, vi]
 * 1 <= ui, vi <= n
 * edges 表示一棵合法的树。
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            g[u].add(v);
            g[v].add(u);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        int k = -1;
        while (!queue.isEmpty()) {
            k++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int u = queue.poll();
                for (int v : g[u]) {
                    if (!visited[v]) {
                        visited[v] = true;
                        queue.offer(v);
                    }
                }
            }
        }
        return (int) pow(2, k - 1);
    }

    //x^k
    private long pow(long x, int k) {
        long res = 1;
        while (k > 0) {
            if (k % 2 > 0) {
                res = res * x % MOD;
            }
            k >>= 1;
            x = x * x % MOD;
        }
        return res;
    }

}
