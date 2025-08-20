package com.sean.leetcode.LeetCode743;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-08-20 17:52
 * @Description https://leetcode.cn/problems/network-delay-time
 * 743. 网络延迟时间
 * 有 n 个网络节点，标记为 1 到 n。
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。
 * times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点 K 发出一个信号。
 * 需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 */
public class Solution {

    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(g[i], INF);
        }
        for (int[] time : times) {
            int u = time[0], v = time[1], w = time[2];
            g[u - 1][v - 1] = w;
        }
        int maxDis = 0;
        int[] dis = new int[n];
        Arrays.fill(dis, INF);
        dis[k - 1] = 0;
        boolean[] done = new boolean[n];
        while (true) {
            int x = -1;
            for (int i = 0; i < n; i++) {
                if (!done[i] && (x < 0 || dis[i] < dis[x])) {
                    x = i;
                }
            }
            if (x < 0) {
                return maxDis;
            }
            if (dis[x] == INF) {
                return -1;
            }
            maxDis = dis[x];
            done[x] = true;
            for (int y = 0; y < n; y++) {
                dis[y] = Math.min(dis[y], dis[x] + g[x][y]);
            }
        }
    }

    public int networkDelayTime1(int[][] times, int n, int k) {
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] time : times) {
            int u = time[0], v = time[1], w = time[2];
            g[u - 1].add(new int[]{v - 1, w});
        }
        int maxDis = 0;
        int left = n;
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[k - 1] = 0;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.offer(new int[]{0, k - 1});
        while (!heap.isEmpty()) {
            int[] arr = heap.poll();
            int disu = arr[0], u = arr[1];
            if (disu > dis[u]) {
                continue;
            }
            maxDis = disu;
            left--;
            for (int[] edge : g[u]) {
                int v = edge[0], newDis = disu + edge[1];
                if (newDis < dis[v]) {
                    dis[v] = newDis;
                    heap.offer(new int[]{newDis, v});
                }
            }
        }
        return left == 0 ? maxDis : -1;
    }

}
