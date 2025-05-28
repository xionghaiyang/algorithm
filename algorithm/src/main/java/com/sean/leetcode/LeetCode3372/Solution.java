package com.sean.leetcode.LeetCode3372;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-05-28 07:11
 * @Description https://leetcode.cn/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i
 * 3372. 连接两棵树后最大目标节点数目 I
 * 有两棵 无向 树，分别有 n 和 m 个树节点。
 * 两棵树中的节点编号分别为[0, n - 1] 和 [0, m - 1] 中的整数。
 * 给你两个二维整数 edges1 和 edges2 ，长度分别为 n - 1 和 m - 1 ，
 * 其中 edges1[i] = [ai, bi] 表示第一棵树中节点 ai 和 bi 之间有一条边，
 * edges2[i] = [ui, vi] 表示第二棵树中节点 ui 和 vi 之间有一条边。
 * 同时给你一个整数 k 。
 * 如果节点 u 和节点 v 之间路径的边数小于等于 k ，那么我们称节点 u 是节点 v 的 目标节点 。
 * 注意 ，一个节点一定是它自己的 目标节点 。
 * 请你返回一个长度为 n 的整数数组 answer ，answer[i] 表示将第一棵树中的一个节点与第二棵树中的一个节点连接一条边后，
 * 第一棵树中节点 i 的 目标节点 数目的 最大值 。
 * 注意 ，每个查询相互独立。
 * 意味着进行下一次查询之前，你需要先把刚添加的边给删掉。
 * 2 <= n, m <= 1000
 * edges1.length == n - 1
 * edges2.length == m - 1
 * edges1[i].length == edges2[i].length == 2
 * edges1[i] = [ai, bi]
 * 0 <= ai, bi < n
 * edges2[i] = [ui, vi]
 * 0 <= ui, vi < m
 * 输入保证 edges1 和 edges2 都表示合法的树。
 * 0 <= k <= 1000
 */
public class Solution {

    //对于answer[i]来说，新添加的边的一个端点必然是i。
    //因为用其他节点当作端点，只会让第二棵树的节点到i距离变得更远，节点i的目标节点数目就会变得更小
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        //暴力枚举第二棵树的节点j,计算距离j不超过k-1的节点个数cntj的最大值max2
        int max2 = 0;
        if (k > 0) {
            List<Integer>[] g = buildTree(edges2);
            for (int j = 0; j < edges2.length + 1; j++) {
                max2 = Math.max(max2, dfs(j, -1, 0, g, k - 1));
            }
        }
        List<Integer>[] g = buildTree(edges1);
        int[] res = new int[edges1.length + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = dfs(i, -1, 0, g, k) + max2;
        }
        return res;
    }

    private List<Integer>[] buildTree(int[][] edges) {
        List<Integer>[] g = new ArrayList[edges.length + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            g[x].add(y);
            g[y].add(x);
        }
        return g;
    }

    private int dfs(int x, int fa, int d, List<Integer>[] g, int k) {
        if (d > k) {
            return 0;
        }
        int res = 1;
        for (int y : g[x]) {
            if (y != fa) {
                res += dfs(y, x, d + 1, g, k);
            }
        }
        return res;
    }

}
