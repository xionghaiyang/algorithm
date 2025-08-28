package com.sean.leetcode.LeetCode802;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-08-28 20:02
 * @Description https://leetcode.cn/problems/find-eventual-safe-states
 * 802. 找到最终的安全状态
 * 有一个有 n 个节点的有向图，节点按 0 到 n - 1 编号。
 * 图由一个 索引从 0 开始 的 2D 整数数组 graph表示， graph[i]是与节点 i 相邻的节点的整数数组，这意味着从节点 i 到 graph[i]中的每个节点都有一条边。
 * 如果一个节点没有连出的有向边，则该节点是 终端节点 。
 * 如果从该节点开始的所有可能路径都通向 终端节点（或另一个安全节点），则该节点为 安全节点。
 * 返回一个由图中所有 安全节点 组成的数组作为答案。
 * 答案数组中的元素应当按 升序 排列。
 * n == graph.length
 * 1 <= n <= 10^4
 * 0 <= graph[i].length <= n
 * 0 <= graph[i][j] <= n - 1
 * graph[i] 按严格递增顺序排列。
 * 图中可能包含自环。
 * 图中边的数目在范围 [1, 4 * 10^4] 内。
 */
public class Solution {

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        //color[i]:0表示点i未遍历过，1表示点i在环里面或者递归中，2表示点i是安全的
        int[] color = new int[n];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (process(graph, color, i)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean process(int[][] graph, int[] color, int x) {
        if (color[x] != 0) {
            return color[x] == 2;
        }
        color[x] = 1;
        for (int y : graph[x]) {
            if (!process(graph, color, y)) {
                return false;
            }
        }
        color[x] = 2;
        return true;
    }

    public List<Integer> eventualSafeNodes1(int[][] graph) {
        int n = graph.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        int[] inDeg = new int[n];
        for (int y = 0; y < n; y++) {
            for (int x : graph[y]) {
                g[x].add(y);
            }
            inDeg[y] = graph[y].length;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int y = queue.poll();
            for (int x : g[y]) {
                if (--inDeg[x] == 0) {
                    queue.offer(x);
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) {
                res.add(i);
            }
        }
        return res;
    }

}
