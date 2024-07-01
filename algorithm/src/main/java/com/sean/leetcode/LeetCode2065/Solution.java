package com.sean.leetcode.LeetCode2065;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2024-07-01 07:30
 * @Description https://leetcode.cn/problems/maximum-path-quality-of-a-graph/
 * 2065. 最大化一张图中的路径价值
 * 给你一张 无向 图，图中有 n 个节点，节点编号从 0 到 n - 1 （都包括）。
 * 同时给你一个下标从 0 开始的整数数组 values ，其中 values[i] 是第 i 个节点的 价值 。
 * 同时给你一个下标从 0 开始的二维整数数组 edges ，其中 edges[j] = [uj, vj, timej] 表示节点 uj 和 vj 之间有一条需要 timej 秒才能通过的无向边。
 * 最后，给你一个整数 maxTime 。
 * 合法路径 指的是图中任意一条从节点 0 开始，最终回到节点 0 ，且花费的总时间 不超过 maxTime 秒的一条路径。
 * 你可以访问一个节点任意次。
 * 一条合法路径的 价值 定义为路径中 不同节点 的价值 之和 （每个节点的价值 至多 算入价值总和中一次）。
 * 请你返回一条合法路径的 最大 价值。
 * 注意：每个节点 至多 有 四条 边与之相连。
 */
public class Solution {

    private List<int[]>[] g;
    private boolean[] visited;
    private int[] values;
    private int res = 0;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        this.values = values;
        g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int time = edge[2];
            g[u].add(new int[]{v, time});
            g[v].add(new int[]{u, time});
        }
        visited = new boolean[n];
        visited[0] = true;
        process(0, maxTime, values[0]);
        return res;
    }

    private void process(int u, int maxTime, int value) {
        if (u == 0) {
            res = Math.max(res, value);
        }
        for (int[] edge : g[u]) {
            int v = edge[0];
            int time = edge[1];
            if (maxTime >= time) {
                if (visited[v]) {
                    process(v, maxTime - time, value);
                } else {
                    visited[v] = true;
                    process(v, maxTime - time, value + values[v]);
                    visited[v] = false;
                }
            }
        }
    }

}
