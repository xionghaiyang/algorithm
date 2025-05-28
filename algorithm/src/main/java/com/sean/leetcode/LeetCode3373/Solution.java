package com.sean.leetcode.LeetCode3373;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-05-29 05:51
 * @Description https://leetcode.cn/problems/maximize-the-number-of-target-nodes-after-connecting-trees-ii
 * 3373. 连接两棵树后最大目标节点数目 II
 * 有两棵 无向 树，分别有 n 和 m 个树节点。
 * 两棵树中的节点编号分别为[0, n - 1] 和 [0, m - 1] 中的整数。
 * 给你两个二维整数 edges1 和 edges2 ，长度分别为 n - 1 和 m - 1 ，
 * 其中 edges1[i] = [ai, bi] 表示第一棵树中节点 ai 和 bi 之间有一条边，edges2[i] = [ui, vi] 表示第二棵树中节点 ui 和 vi 之间有一条边。
 * 如果节点 u 和节点 v 之间路径的边数是偶数，那么我们称节点 u 是节点 v 的 目标节点 。
 * 注意 ，一个节点一定是它自己的 目标节点 。
 * 请你返回一个长度为 n 的整数数组 answer ，answer[i] 表示将第一棵树中的一个节点与第二棵树中的一个节点连接一条边后，第一棵树中节点 i 的 目标节点 数目的 最大值 。
 * 注意 ，每个查询相互独立。
 * 意味着进行下一次查询之前，你需要先把刚添加的边给删掉。
 * 2 <= n, m <= 10^5
 * edges1.length == n - 1
 * edges2.length == m - 1
 * edges1[i].length == edges2[i].length == 2
 * edges1[i] = [ai, bi]
 * 0 <= ai, bi < n
 * edges2[i] = [ui, vi]
 * 0 <= ui, vi < m
 * 输入保证 edges1 和 edges2 都表示合法的树。
 */
public class Solution {

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1, m = edges2.length + 1;
        int[] color1 = new int[n];
        int[] color2 = new int[m];
        int[] cnt1 = build(edges1, color1);
        int[] cnt2 = build(edges2, color2);
        int max2 = Math.max(cnt2[0], cnt2[1]);
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = cnt1[color1[i]] + max2;
        }
        return res;
    }

    private int[] build(int[][] edges, int[] color) {
        List<Integer>[] g = new ArrayList[edges.length + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            g[x].add(y);
            g[y].add(x);
        }
        int[] cnt = new int[2];
        dfs(0, -1, 0, g, color, cnt);
        return cnt;
    }

    private void dfs(int x, int fa, int d, List<Integer>[] g, int[] color, int[] cnt) {
        cnt[d]++;
        color[x] = d;
        for (int y : g[x]) {
            if (y != fa) {
                dfs(y, x, d ^ 1, g, color, cnt);
            }
        }
    }

}
