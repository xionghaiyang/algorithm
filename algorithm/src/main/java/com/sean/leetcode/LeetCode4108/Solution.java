package com.sean.leetcode.LeetCode4108;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-08 18:23
 * @Description: https://leetcode.cn/problems/minimum-cost-to-make-red-nodes-connected
 * 4108. 使红色节点连通的最小代价
 * 给你一个整数 n，表示一棵有 n 个节点的树，节点编号从 0 到 n - 1，并且树以节点 0 为 根节点。
 * 这棵树由一个长度为 n - 1 的二维数组 edges 表示，其中 edges[i] = [ui, vi] 表示节点 ui 和 vi 之间存在一条 无向 边。
 * 另给你一个长度为 n 的字符串 colors，其中 colors[i] 表示节点 i 的颜色：'R' 表示红色，'B' 表示蓝色。
 * 你可以 翻转 任意节点的颜色：从 'R' 变为 'B'，或从 'B' 变为 'R'。
 * 翻转节点 u 的代价等于以节点 u 为根的子树的大小。
 * 你的目标是通过翻转颜色，使所有 红色 节点在树中形成 一个连通分量 。
 * 蓝色节点可以不连通。
 * 没有红色节点的配置也视为有效。
 * 返回实现这一目标所需的 最小总代价 。
 * 在有根树中，节点 v 的子树是由所有到根节点的路径经过 v 的节点组成的集合。
 * 1 <= n <= 10^5
 * n == colors.length
 * colors[i] 为 'R' 或 'B'。
 * edges.length == n - 1
 * edges[i] = [ui, vi]
 * 0 <= ui, vi <= n - 1
 * 输入保证 edges 表示一棵有效的树。
 */
public class Solution {

    //先把所有节点都变成蓝色，计算对应的总代价base,即所有红色节点的size之和
    private long base = 0;
    //然后计算把哪些节点变成红色，可以让base减少的尽量多
    private long max = 0;

    public long minCost(int n, String colors, int[][] edges) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            g[u].add(v);
            g[v].add(u);
        }
        dfs(g, colors.toCharArray(), 0, -1);
        return base - max;
    }

    private long[] dfs(List<Integer>[] g, char[] colors, int u, int p) {
        //子树u的大小
        int size = 1;
        //子树u能得到的最大点权和
        long f = 0;
        for (int v : g[u]) {
            if (v != p) {
                long[] res = dfs(g, colors, v, u);
                size += res[0];
                f += Math.max(res[1], 0);
            }
        }
        if (colors[u] == 'R') {
            //将u变成蓝色的代价
            base += size;
            //把u恢复成红色，可以让base减少size
            f += size;
        } else {
            //把u变成红色，会让base增加size
            f -= size;
        }
        max = Math.max(max, f);
        return new long[]{size, f};
    }

}
