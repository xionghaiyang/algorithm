package com.sean.leetcode.LeetCode2925;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-09-01 20:24
 * @Description https://leetcode.cn/problems/maximum-score-after-applying-operations-on-a-tree
 * 2925. 在树上执行操作以后得到的最大分数
 * 有一棵 n 个节点的无向树，节点编号为 0 到 n - 1 ，根节点编号为 0 。
 * 给你一个长度为 n - 1 的二维整数数组 edges 表示这棵树，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 有一条边。
 * 同时给你一个长度为 n 下标从 0 开始的整数数组 values ，其中 values[i] 表示第 i 个节点的值。
 * 一开始你的分数为 0 ，每次操作中，你将执行：
 * 选择节点 i 。
 * 将 values[i] 加入你的分数。
 * 将 values[i] 变为 0 。
 * 如果从根节点出发，到任意叶子节点经过的路径上的节点值之和都不等于 0 ，那么我们称这棵树是 健康的 。
 * 你可以对这棵树执行任意次操作，但要求执行完所有操作以后树是 健康的 ，请你返回你可以获得的 最大分数 。
 * 2 <= n <= 2 * 10^4
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * values.length == n
 * 1 <= values[i] <= 10^9
 * 输入保证 edges 构成一棵合法的树。
 */
public class Solution {

    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int n = values.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        g[0].add(-1);
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            g[u].add(v);
            g[v].add(u);
        }
        long res = 0;
        for (int value : values) {
            res += value;
        }
        return res - process(0, -1, g, values);
    }

    private long process(int u, int p, List<Integer>[] g, int[] values) {
        if (g[u].size() == 1) {
            return values[u];
        }
        long loss = 0;
        for (int v : g[u]) {
            if (v != p) {
                loss += process(v, u, g, values);
            }
        }
        return Math.min(values[u], loss);
    }

}
