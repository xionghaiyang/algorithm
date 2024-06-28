package com.sean.leetcode.LeetCode1377;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-05-24 10:02
 * @Description: https://leetcode.cn/problems/frog-position-after-t-seconds/
 * 1377. T 秒后青蛙的位置
 * 给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。
 * 青蛙从 顶点 1 开始起跳。规则如下：
 * 在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。
 * 青蛙无法跳回已经访问过的顶点。
 * 如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。
 * 如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。
 * 无向树的边用数组 edges 描述，其中 edges[i] = [ai, bi] 意味着存在一条直接连通 ai 和 bi 两个顶点的边。
 * 返回青蛙在 t 秒后位于目标顶点 target 上的概率。
 * 与实际答案相差不超过 10-5 的结果将被视为正确答案。
 */
public class Solution {

    public double frogPosition(int n, int[][] edges, int t, int target) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0] - 1).add(edge[1] - 1);
            graph.get(edge[1] - 1).add(edge[0] - 1);
        }
        boolean[] visited = new boolean[n];
        return dfs(graph, visited, 0, t, target - 1);
    }

    private double dfs(List<List<Integer>> graph, boolean[] visited, int cur, int t, int target) {
        int fm = cur == 0 ? graph.get(cur).size() : graph.get(cur).size() - 1;
        if (t == 0 || fm == 0) {
            return cur == target ? 1.0 : 0.0;
        }
        visited[cur] = true;
        double fz = 0.0;
        for (int i : graph.get(cur)) {
            if (!visited[i]) {
                fz += dfs(graph, visited, i, t - 1, target);
            }
        }
        return fz / fm;
    }

}
