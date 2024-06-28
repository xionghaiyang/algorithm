package com.sean.leetcode.LeetCode1483;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-06-12 10:04
 * @Description: https://leetcode.cn/problems/kth-ancestor-of-a-tree-node/
 * 1483. 树节点的第 K 个祖先
 * 给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。
 * 树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。
 * 树的根节点是编号为 0 的节点。
 * 树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。
 * 实现 TreeAncestor 类：
 * TreeAncestor（int n， int[] parent） 对树和父数组中的节点数初始化对象。
 * getKthAncestor(int node, int k) 返回节点 node 的第 k 个祖先节点。
 * 如果不存在这样的祖先节点，返回 -1 。
 */
public class TreeAncestor {

    static final int LOG = 16;
    private int[][] dp;

    public TreeAncestor(int n, int[] parent) {
        //dp[i][j]表示节点i的第2^j个祖先
        dp = new int[n][LOG];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = parent[i];
        }
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                if (dp[i][j - 1] != -1) {
                    dp[i][j] = dp[dp[i][j - 1]][j - 1];
                }
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        for (int j = 0; j < LOG; j++) {
            if (((k >> j) & 1) != 0) {
                node = dp[node][j];
                if (node == -1) {
                    return -1;
                }
            }
        }
        return node;
    }

}
