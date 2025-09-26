package com.sean.leetcode.LeetCode1938;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-09-26 17:32
 * @Description https://leetcode.cn/problems/maximum-genetic-difference-query
 * 1938. 查询最大基因差
 * 给你一棵 n 个节点的有根树，节点编号从 0 到 n - 1 。
 * 每个节点的编号表示这个节点的 独一无二的基因值 （也就是说节点 x 的基因值为 x）。
 * 两个基因值的 基因差 是两者的 异或和 。
 * 给你整数数组 parents ，其中 parents[i] 是节点 i 的父节点。
 * 如果节点 x 是树的 根 ，那么 parents[x] == -1 。
 * 给你查询数组 queries ，其中 queries[i] = [nodei, vali] 。
 * 对于查询 i ，请你找到 vali 和 pi 的 最大基因差 ，其中 pi 是节点 nodei 到根之间的任意节点（包含 nodei 和根节点）。
 * 更正式的，你想要最大化 vali XOR pi 。
 * 请你返回数组 ans ，其中 ans[i] 是第 i 个查询的答案。
 * 2 <= parents.length <= 10^5
 * 对于每个 不是 根节点的 i ，有 0 <= parents[i] <= parents.length - 1 。
 * parents[root] == -1
 * 1 <= queries.length <= 3 * 10^4
 * 0 <= nodei <= parents.length - 1
 * 0 <= vali <= 2 * 10^5
 */
public class Solution {

    public class Trie {
        private static final int HIGHEST_BIT = 17;
        private Trie[] children;
        private int cnt;

        public Trie() {
            children = new Trie[2];
            cnt = 0;
        }

        public void insert(int num) {
            Trie node = this;
            for (int i = HIGHEST_BIT; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[bit] == null) {
                    node.children[bit] = new Trie();
                }
                node = node.children[bit];
                node.cnt++;
            }
        }

        public int getMaxXor(int num) {
            int xor = 0;
            Trie node = this;
            for (int i = HIGHEST_BIT; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[bit ^ 1] != null && node.children[bit ^ 1].cnt > 0) {
                    node = node.children[bit ^ 1];
                    xor = (xor << 1) + 1;
                } else {
                    node = node.children[bit];
                    xor <<= 1;
                }
            }
            return xor;
        }

        public void remove(int num) {
            Trie node = this;
            for (int i = HIGHEST_BIT; i >= 0; i--) {
                int bit = (num >> i) & 1;
                node = node.children[bit];
                node.cnt--;
            }
        }
    }

    private List<Integer>[] g;
    private List<int[]>[] nodeQueries;
    private Trie trie = new Trie();
    private int[] res;

    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        int n = parents.length;
        g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (parents[i] == -1) {
                root = i;
            } else {
                g[parents[i]].add(i);
            }
        }
        int m = queries.length;
        nodeQueries = new ArrayList[n];
        Arrays.setAll(nodeQueries, e -> new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int[] query = queries[i];
            int node = query[0], val = query[1];
            nodeQueries[node].add(new int[]{i, val});
        }
        res = new int[m];
        dfs(root);
        return res;
    }

    public void dfs(int node) {
        trie.insert(node);
        for (int[] query : nodeQueries[node]) {
            int index = query[0], val = query[1];
            res[index] = trie.getMaxXor(val);
        }
        for (int child : g[node]) {
            dfs(child);
        }
        trie.remove(node);
    }

}
