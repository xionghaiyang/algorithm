package com.sean.leetcode.LeetCode2846;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-26 14:31
 * @Description: https://leetcode.cn/problems/minimum-edge-weight-equilibrium-queries-in-a-tree/
 * 2846. 边权重均等查询
 * 现有一棵由 n 个节点组成的无向树，节点按从 0 到 n - 1 编号。
 * 给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，
 * 其中 edges[i] = [ui, vi, wi] 表示树中存在一条位于节点 ui 和节点 vi 之间、权重为 wi 的边。
 * 另给你一个长度为 m 的二维整数数组 queries ，其中 queries[i] = [ai, bi] 。
 * 对于每条查询，请你找出使从 ai 到 bi 路径上每条边的权重相等所需的 最小操作次数 。
 * 在一次操作中，你可以选择树上的任意一条边，并将其权重更改为任意值。
 * 注意：
 * 查询之间 相互独立 的，这意味着每条新的查询时，树都会回到 初始状态 。
 * 从 ai 到 bi的路径是一个由 不同 节点组成的序列，从节点 ai 开始，到节点 bi 结束，且序列中相邻的两个节点在树中共享一条边。
 * 返回一个长度为 m 的数组 answer ，其中 answer[i] 是第 i 条查询的答案。
 */
public class Solution {

    static final int W = 26;
    private Map<Integer, Integer>[] neighbors;
    private List<int[]>[] queryArr;
    //count[i][j]表示节点i到根节点0的路径上权重为j的边数量
    private int[][] count;
    private boolean[] visited;
    private int[] uf;
    //节点ai和bi的最近的共同祖先
    private int[] lca;

    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        int m = queries.length;
        neighbors = new Map[n];
        for (int i = 0; i < n; i++) {
            neighbors[i] = new HashMap<>();
        }
        for (int[] edge : edges) {
            neighbors[edge[0]].put(edge[1], edge[2]);
            neighbors[edge[1]].put(edge[0], edge[2]);
        }
        queryArr = new List[n];
        for (int i = 0; i < n; i++) {
            queryArr[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            queryArr[queries[i][0]].add(new int[]{queries[i][1], i});
            queryArr[queries[i][1]].add(new int[]{queries[i][0], i});
        }
        count = new int[n][W + 1];
        visited = new boolean[n];
        uf = new int[n];
        lca = new int[m];
        tarjan(0, -1);
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int totalCount = 0, maxCount = 0;
            for (int j = 1; j <= W; j++) {
                int t = count[queries[i][0]][j] + count[queries[i][1]][j] - 2 * count[lca[i]][j];
                maxCount = Math.max(maxCount, t);
                totalCount += t;
            }
            res[i] = totalCount - maxCount;
        }
        return res;
    }

    private void tarjan(int node, int parent) {
        if (parent != -1) {
            System.arraycopy(count[parent], 0, count[node], 0, W + 1);
            count[node][neighbors[node].get(parent)]++;
        }
        uf[node] = node;
        for (int child : neighbors[node].keySet()) {
            if (child == parent) {
                continue;
            }
            tarjan(child, node);
            uf[child] = node;
        }
        for (int[] pair : queryArr[node]) {
            int node1 = pair[0];
            int index = pair[1];
            if (node != node1 && !visited[node1]) {
                continue;
            }
            lca[index] = find(node1);
        }
        visited[node] = true;
    }

    private int find(int i) {
        if (uf[i] == i) {
            return i;
        }
        uf[i] = find(uf[i]);
        return uf[i];
    }

}
