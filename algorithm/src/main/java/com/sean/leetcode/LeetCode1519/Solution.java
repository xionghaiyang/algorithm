package com.sean.leetcode.LeetCode1519;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-09-18 19:22
 * @Description https://leetcode.cn/problems/number-of-nodes-in-the-sub-tree-with-the-same-label
 * 1519. 子树中标签相同的节点数
 * 给你一棵树（即，一个连通的无环无向图），这棵树由编号从 0  到 n - 1 的 n 个节点组成，且恰好有 n - 1 条 edges 。
 * 树的根节点为节点 0 ，树上的每一个节点都有一个标签，也就是字符串 labels 中的一个小写字符（编号为 i 的 节点的标签就是 labels[i] ）
 * 边数组 edges 以 edges[i] = [ai, bi] 的形式给出，该格式表示节点 ai 和 bi 之间存在一条边。
 * 返回一个大小为 n 的数组，其中 ans[i] 表示第 i 个节点的子树中与节点 i 标签相同的节点数。
 * 树 T 中的子树是由 T 中的某个节点及其所有后代节点组成的树。
 * 1 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * labels.length == n
 * labels 仅由小写英文字母组成
 */
public class Solution {

    private int[] count;
    private int[] res;

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            g[u].add(v);
            g[v].add(u);
        }
        count = new int[26];
        res = new int[n];
        int[] ls = new int[n];
        for (int i = 0; i < n; i++) {
            ls[i] = labels.charAt(i) - 'a';
        }
        dfs(g, ls, 0, -1);
        return res;
    }

    private void dfs(List<Integer>[] g, int[] ls, int u, int p) {
        int preCnt = count[ls[u]]++;
        for (int v : g[u]) {
            if (v != p) {
                dfs(g, ls, v, u);
            }
        }
        res[u] = count[ls[u]] - preCnt;
    }

}
