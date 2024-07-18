package com.sean.leetcode.LeetCode3112;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-07-18 07:53
 * @Description https://leetcode.cn/problems/minimum-time-to-visit-disappearing-nodes/
 * 3112. 访问消失节点的最少时间
 * 给你一个二维数组 edges 表示一个 n 个点的无向图，
 * 其中 edges[i] = [ui, vi, lengthi] 表示节点 ui 和节点 vi 之间有一条需要 lengthi 单位时间通过的无向边。
 * 同时给你一个数组 disappear ，其中 disappear[i] 表示节点 i 从图中消失的时间点，在那一刻及以后，你无法再访问这个节点。
 * 注意，图有可能一开始是不连通的，两个节点之间也可能有多条边。
 * 请你返回数组 answer ，answer[i] 表示从节点 0 到节点 i 需要的 最少 单位时间。
 * 如果从节点 0 出发 无法 到达节点 i ，那么 answer[i] 为 -1 。
 */
public class Solution {

    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        int[] res = new int[n];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[0] = 0;
        List<int[]>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int length = edge[2];
            g[u].add(new int[]{v, length});
            g[v].add(new int[]{u, length});
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int u = arr[0];
            int time = arr[1];
            if (time != res[u]) {
                continue;
            }
            for (int[] edge : g[u]) {
                int v = edge[0];
                int length = edge[1];
                if (v != 0 && time + length < disappear[v] && time + length < res[v]) {
                    res[v] = time + length;
                    queue.offer(new int[]{v, time + length});
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (res[i] == Integer.MAX_VALUE) {
                res[i] = -1;
            }
        }
        return res;
    }

}
