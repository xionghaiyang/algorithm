package com.sean.leetcode.LeetCode1971;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-19 09:08
 * @Description: https://leetcode.cn/problems/find-if-path-exists-in-graph/
 * 1971. 寻找图中是否存在路径
 * 有一个具有 n 个顶点的 双向 图，其中每个顶点标记从 0 到 n - 1（包含 0 和 n - 1）。
 * 图中的边用一个二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示顶点 ui 和顶点 vi 之间的双向边。
 * 每个顶点对由 最多一条 边连接，并且没有顶点存在与自身相连的边。
 * 请你确定是否存在从顶点 source 开始，到顶点 destination 结束的 有效路径 。
 * 给你数组 edges 和整数 n、source 和 destination，如果从 source 到 destination 存在 有效路径 ，则返回 true，否则返回 false 。
 */
public class Solution {

    public boolean validPath1(int n, int[][] edges, int source, int destination) {
        List<Integer>[] list = new List[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            list[x].add(y);
            list[y].add(x);
        }
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        visited[source] = true;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            if (vertex == destination) {
                break;
            }
            for (int next : list[vertex]) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
        return visited[destination];
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<Integer>[] list = new List[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            list[x].add(y);
            list[y].add(x);
        }
        boolean[] visited = new boolean[n];
        return dfs(source, destination, list, visited);
    }

    private boolean dfs(int source, int destination, List<Integer>[] list, boolean[] visited) {
        if (source == destination) {
            return true;
        }
        visited[source] = true;
        for (int next : list[source]) {
            if (!visited[next] && dfs(next, destination, list, visited)) {
                return true;
            }
        }
        return false;
    }

}
