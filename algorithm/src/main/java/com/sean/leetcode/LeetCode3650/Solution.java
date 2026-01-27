package com.sean.leetcode.LeetCode3650;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author xionghaiyang
 * @Date 2026-01-27 11:34
 * @Description https://leetcode.cn/problems/minimum-cost-path-with-edge-reversals
 * 3650. 边反转的最小路径总成本
 * 给你一个包含 n 个节点的有向带权图，节点编号从 0 到 n - 1。
 * 同时给你一个数组 edges，其中 edges[i] = [ui, vi, wi] 表示一条从节点 ui 到节点 vi 的有向边，其成本为 wi。
 * 每个节点 ui 都有一个 最多可使用一次 的开关：当你到达 ui 且尚未使用其开关时，你可以对其一条入边 vi → ui 激活开关，将该边反转为 ui → vi 并 立即 穿过它。
 * 反转仅对那一次移动有效，使用反转边的成本为 2 * wi。
 * 返回从节点 0 到达节点 n - 1 的 最小 总成本。
 * 如果无法到达，则返回 -1。
 * 2 <= n <= 5 * 104
 * 1 <= edges.length <= 105
 * edges[i] = [ui, vi, wi]
 * 0 <= ui, vi <= n - 1
 * 1 <= wi <= 1000
 */
public class Solution {

    public int minCost(int n, int[][] edges) {
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            g[u].add(new int[]{v, w});
            g[v].add(new int[]{u, w * 2});
        }
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        dis[0] = 0;
        heap.offer(new int[]{0, 0});
        while (!heap.isEmpty()) {
            int[] arr = heap.poll();
            int disU = arr[0], u = arr[1];
            if (disU > dis[u]) {
                continue;
            }
            if (u == n - 1) {
                return disU;
            }
            for (int[] e : g[u]) {
                int v = e[0], w = e[1];
                int newDisV = disU + w;
                if (newDisV < dis[v]) {
                    dis[v] = newDisV;
                    heap.offer(new int[]{newDisV, v});
                }
            }
        }
        return -1;
    }

}
