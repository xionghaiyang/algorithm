package com.sean.leetcode.LeetCode3970;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-30 06:17
 * @Description: https://leetcode.cn/problems/shortest-path-with-at-most-k-consecutive-identical-characters
 * 3970. 最多 K 个连续相同字符的最短路径
 * 给你一个整数 n，表示一个 有向加权 图中的节点数量，节点编号从 0 到 n - 1。
 * 该图由二维数组 edges 表示，其中 edges[i] = [ui, vi, wi] 表示一条从节点 ui 指向节点 vi、权重为 wi 的有向边。
 * 另给定一个长度为 n 的字符串 labels，其中 labels[i] 是分配给节点 i 的字符，以及一个整数 k。
 * 返回一条从节点 0 到节点 n - 1 的路径的 最小总边权 ，并要求该路径上所有节点标签按顺序 拼接 后，最多包含 k 个 连续相同 字符。
 * 如果不存在有效路径，返回 -1。
 * 1 <= n == labels.length <= 5 * 10^4
 * 0 <= edges.length <= 5 * 10^4
 * edges[i] == [ui, vi, wi]
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 1 <= wi <= 10^4
 * labels 由小写英文字母组成
 * 1 <= k <= 50
 */
public class Solution {

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        char[] str = labels.toCharArray();
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            g[u].add(new int[]{v, w});
        }
        int[][] dis = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.offer(new int[]{0, 0, 1});
        while (!heap.isEmpty()) {
            int[] arr = heap.poll();
            int d = arr[0], u = arr[1], cnt = arr[2];
            if (u == n - 1) {
                return d;
            }
            if (d > dis[u][cnt]) {
                continue;
            }
            for (int[] e : g[u]) {
                int v = e[0], w = e[1];
                int newCnt = str[u] == str[v] ? cnt + 1 : 1, newDis = d + w;
                if (newCnt <= k && newDis < dis[v][newCnt]) {
                    dis[v][newCnt] = newDis;
                    heap.offer(new int[]{newDis, v, newCnt});
                }
            }
        }
        return -1;
    }

}
