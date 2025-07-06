package com.sean.leetcode.LeetCode3604;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-07-06 18:17
 * @Description https://leetcode.cn/problems/minimum-time-to-reach-destination-in-directed-graph
 * 3604. 有向图中到达终点的最少时间
 * 给你一个整数 n 和一个 有向 图，图中有 n 个节点，编号从 0 到 n - 1。
 * 图由一个二维数组 edges 表示，其中 edges[i] = [ui, vi, starti, endi] 表示从节点 ui 到 vi 的一条边，该边 只能 在满足 starti <= t <= endi 的整数时间 t 使用。
 * 你在时间 0 从在节点 0 出发。
 * 在一个时间单位内，你可以：
 * 停留在当前节点不动，或者
 * 如果当前时间 t 满足 starti <= t <= endi，则从当前节点沿着出边的方向移动。
 * 返回到达节点 n - 1 所需的 最小 时间。
 * 如果不可能，返回 -1。
 * 1 <= n <= 10^5
 * 0 <= edges.length <= 10^5
 * edges[i] == [ui, vi, starti, endi]
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 0 <= starti <= endi <= 10^9
 */
public class Solution {

    public int minTime(int n, int[][] edges) {
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], start = edge[2], end = edge[3];
            g[u].add(new int[]{v, start, end});
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        int[] visitTime = new int[n];
        Arrays.fill(visitTime, Integer.MAX_VALUE);
        visitTime[0] = 0;
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int u = arr[0], t = arr[1];
            for (int[] next : g[u]) {
                int v = next[0], start = next[1], end = next[2];
                if (t < start) {
                    if (start + 1 < visitTime[v]) {
                        visitTime[v] = start + 1;
                        queue.offer(new int[]{v, start + 1});
                    }
                } else if (t <= end) {
                    if (t + 1 < visitTime[v]) {
                        visitTime[v] = t + 1;
                        queue.offer(new int[]{v, t + 1});
                    }
                }
            }
        }
        return visitTime[n - 1] == Integer.MAX_VALUE ? -1 : visitTime[n - 1];
    }

}
