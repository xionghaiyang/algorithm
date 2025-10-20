package com.sean.leetcode.LeetCodeInterview0401;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-10-20 18:36
 * @Description https://leetcode.cn/problems/route-between-nodes-lcci
 * 面试题 04.01. 节点间通路
 * 节点间通路。
 * 给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
 * 节点数量n在[0, 1e^5]范围内。
 * 节点编号大于等于 0 小于 n。
 * 图中可能存在自环和平行边。
 */
public class Solution {

    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] arr : graph) {
            int u = arr[0], v = arr[1];
            g[u].add(v);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        boolean[] visited = new boolean[n];
        visited[start] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            if (u == target) {
                return true;
            }
            for (int v : g[u]) {
                if (!visited[v]) {
                    queue.offer(v);
                    visited[v] = true;
                }
            }
        }
        return false;
    }

}
