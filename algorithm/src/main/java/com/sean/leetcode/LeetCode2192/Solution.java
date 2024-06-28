package com.sean.leetcode.LeetCode2192;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-04-04 19:37
 * @Description: https://leetcode.cn/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/?envType=daily-question&envId=2024-04-04
 * 2192. 有向无环图中一个节点的所有祖先
 * 给你一个正整数 n ，它表示一个 有向无环图 中节点的数目，节点编号为 0 到 n - 1 （包括两者）。
 * 给你一个二维整数数组 edges ，其中 edges[i] = [fromi, toi] 表示图中一条从 fromi 到 toi 的单向边。
 * 请你返回一个数组 answer，其中 answer[i]是第 i 个节点的所有 祖先 ，这些祖先节点 升序 排序。
 * 如果 u 通过一系列边，能够到达 v ，那么我们称节点 u 是节点 v 的 祖先 节点。
 */
public class Solution {

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        Set<Integer>[] sets = new Set[n];
        for (int i = 0; i < n; i++) {
            sets[i] = new HashSet<>();
        }
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        int[] ind = new int[n];
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            g[from].add(to);
            ind[to]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (ind[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : g[u]) {
                sets[v].add(u);
                for (int i : sets[u]) {
                    sets[v].add(i);
                }
                ind[v]--;
                if (ind[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>(sets[i]));
            Collections.sort(res.get(i));
        }
        return res;
    }

}
