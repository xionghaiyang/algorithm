package com.sean.leetcode.LeetCode4085;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-19 19:48
 * @Description: https://leetcode.cn/problems/find-root-of-a-colored-tree
 * 4085. 找到颜色树的根
 * 给你一棵含有 n 个节点的无向 树，节点编号从 0 到 n - 1，用一个长度为 n - 1 的二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示节点 ui 和 vi 之间存在一条无向边。
 * 同时给你一个长度为 n 的字符串 colors，其中 colors[i] 是 'R'、'B' 或 'W' 之一，表示节点 i 的颜色。
 * 一个被选作 根节点 的节点，在以它为起点将树定向（即从根向外定向）之后，必须同时满足以下两个 条件：
 * 二叉树性质：每个节点最多有两个子节点。
 * 分层颜色模式：处于相同深度的所有节点都具有相同的颜色。
 * 从深度 0 开始，这些颜色遵循以下三种循环序列之一：
 * R → B → W → R → B → W → ...
 * B → W → R → B → W → R → ...
 * W → R → B → W → R → B → ...
 * 返回满足这两个条件的任意根节点的 下标，如果不存在这样的根节点，则返回 -1。
 * 树 是一个无环的连通无向图。
 * 节点的 深度 是从根节点到该节点的路径上的边数。
 * 根节点的深度为 0。
 * 一个节点的 子节点 是指与其相邻且深度大 1 的节点。
 * 节点的 度 是指与该节点相连的边数。
 * 2 <= n <= 10^5
 * edges.length == n - 1
 * edges[i] = [ui, vi]
 * 0 <= ui, vi < n
 * colors.length == n
 * colors[i] 是 'R'、'B' 或 'W' 之一。
 * 输入图是一棵树，且每个节点的度最多为三。
 */
public class Solution {

    private static final int[] map = new int[128];

    static {
        map['W'] = 0;
        map['R'] = 1;
        map['B'] = 2;
    }

    public int findColoredRoot(int[][] edges, String colors) {
        char[] str = colors.toCharArray();
        int n = str.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            g[u].add(v);
            g[v].add(u);
        }
        int res = -1;
        for (int x = 0; x < n; x++) {
            List<Integer> to = g[x];
            if (to.size() > 3) {
                return -1;
            }
            int[] cnt = new int[3];
            for (int y : to) {
                cnt[map[str[y]]]++;
            }
            int colorX = map[str[x]];
            //不能有邻居和x颜色相同，或者x的父节点的颜色个数超过1
            if (cnt[colorX] > 0 || cnt[(colorX + 2) % 3] > 1) {
                return -1;
            }
            //判断x能否是根
            if (to.size() < 3 && cnt[(colorX + 2) % 3] == 0) {
                res = x;
            }
        }
        return res;
    }

}
