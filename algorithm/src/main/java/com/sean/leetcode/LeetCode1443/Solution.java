package com.sean.leetcode.LeetCode1443;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-09-02 18:23
 * @Description https://leetcode.cn/problems/minimum-time-to-collect-all-apples-in-a-tree
 * 1443. 收集树上所有苹果的最少时间
 * 给你一棵有 n 个节点的无向树，节点编号为 0 到 n-1 ，它们中有一些节点有苹果。
 * 通过树上的一条边，需要花费 1 秒钟。
 * 你从 节点 0 出发，请你返回最少需要多少秒，可以收集到所有苹果，并回到节点 0 。
 * 无向树的边由 edges 给出，其中 edges[i] = [fromi, toi] ，表示有一条边连接 from 和 toi 。
 * 除此以外，还有一个布尔数组 hasApple ，其中 hasApple[i] = true 代表节点 i 有一个苹果，否则，节点 i 没有苹果。
 * 1 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai < bi <= n - 1
 * hasApple.length == n
 */
public class Solution {

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            g[u].add(v);
            g[v].add(u);
        }
        return process(0, g, hasApple, new boolean[n]);
    }

    private int process(int u, List<Integer>[] g, List<Boolean> hasApple, boolean[] visited) {
        visited[u] = true;
        int res = 0;
        for (int v : g[u]) {
            if (visited[v]) {
                continue;
            }
            res += process(v, g, hasApple, visited);
            if (hasApple.get(v)) {
                hasApple.set(u, true);
                res += 2;
            }
        }
        return res;
    }

}
