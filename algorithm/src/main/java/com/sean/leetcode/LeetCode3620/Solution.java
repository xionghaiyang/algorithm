package com.sean.leetcode.LeetCode3620;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-07-21 11:26
 * @Description https://leetcode.cn/problems/network-recovery-pathways
 * 3620. 恢复网络路径
 * 给你一个包含 n 个节点（编号从 0 到 n - 1）的有向无环图。
 * 图由长度为 m 的二维数组 edges 表示，其中 edges[i] = [ui, vi, costi] 表示从节点 ui 到节点 vi 的单向通信，恢复成本为 costi。
 * 一些节点可能处于离线状态。
 * 给定一个布尔数组 online，其中 online[i] = true 表示节点 i 在线。
 * 节点 0 和 n - 1 始终在线。
 * 从 0 到 n - 1 的路径如果满足以下条件，那么它是 有效 的：
 * 路径上的所有中间节点都在线。
 * 路径上所有边的总恢复成本不超过 k。
 * 对于每条有效路径，其 分数 定义为该路径上的最小边成本。
 * 返回所有有效路径中的 最大 路径分数（即最大 最小 边成本）。
 * 如果没有有效路径，则返回 -1。
 * n == online.length
 * 2 <= n <= 5 * 10^4
 * 0 <= m == edges.length <= min(10^5, n * (n - 1) / 2)
 * edges[i] = [ui, vi, costi]
 * 0 <= ui, vi < n
 * ui != vi
 * 0 <= costi <= 10^9
 * 0 <= k <= 5 * 10^13
 * online[i] 是 true 或 false，且 online[0] 和 online[n - 1] 均为 true。
 * 给定的图是一个有向无环图。
 */
public class Solution {

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        int maxCost = -1;
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], cost = edge[2];
            if (online[u] && online[v]) {
                g[u].add(new int[]{v, cost});
                if (u == 0) {
                    maxCost = Math.max(maxCost, cost);
                }
            }
        }
        long[] memo = new long[n];
        int res = -1, left = 0, right = maxCost;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            Arrays.fill(memo, -1);
            if (process(0, mid, g, memo) <= k) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    private long process(int u, int lower, List<int[]>[] g, long[] memo) {
        if (u == g.length - 1) {
            return 0;
        }
        if (memo[u] != -1) {
            return memo[u];
        }
        long res = Long.MAX_VALUE / 2;
        for (int[] arr : g[u]) {
            int v = arr[0], cost = arr[1];
            if (cost >= lower) {
                res = Math.min(res, process(v, lower, g, memo) + cost);
            }
        }
        memo[u] = res;
        return res;
    }

}
