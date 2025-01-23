package com.sean.leetcode.LeetCode2920;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-01-23 19:36
 * @Description https://leetcode.cn/problems/maximum-points-after-collecting-coins-from-all-nodes/
 * 2920. 收集所有金币可获得的最大积分
 * 有一棵由 n 个节点组成的无向树，以 0  为根节点，节点编号从 0 到 n - 1 。
 * 给你一个长度为 n - 1 的二维 整数 数组 edges ，其中 edges[i] = [ai, bi] 表示在树上的节点 ai 和 bi 之间存在一条边。
 * 另给你一个下标从 0 开始、长度为 n 的数组 coins 和一个整数 k ，其中 coins[i] 表示节点 i 处的金币数量。
 * 从根节点开始，你必须收集所有金币。
 * 要想收集节点上的金币，必须先收集该节点的祖先节点上的金币。
 * 节点 i 上的金币可以用下述方法之一进行收集：
 * 收集所有金币，得到共计 coins[i] - k 点积分。
 * 如果 coins[i] - k 是负数，你将会失去 abs(coins[i] - k) 点积分。
 * 收集所有金币，得到共计 floor(coins[i] / 2) 点积分。
 * 如果采用这种方法，节点 i 子树中所有节点 j 的金币数 coins[j] 将会减少至 floor(coins[j] / 2) 。
 * 返回收集 所有 树节点的金币之后可以获得的最大积分。
 * n == coins.length
 * 2 <= n <= 10^5
 * 0 <= coins[i] <= 10^4
 * edges.length == n - 1
 * 0 <= edges[i][0], edges[i][1] < n
 * 0 <= k <= 10^4
 */
public class Solution {

    public int maximumPoints(int[][] edges, int[] coins, int k) {
        int n = coins.length;
        List<List<Integer>> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            children.get(edge[0]).add(edge[1]);
            children.get(edge[1]).add(edge[0]);
        }
        int[][] memo = new int[n][14];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(0, -1, 0, coins, k, children, memo);
    }

    private int dfs(int node, int parent, int f, int[] coins, int k, List<List<Integer>> children, int[][] memo) {
        if (memo[node][f] != -1) {
            return memo[node][f];
        }
        int res0 = (coins[node] >> f) - k;
        int res1 = coins[node] >> (f + 1);
        for (int child : children.get(node)) {
            if (child == parent) {
                continue;
            }
            res0 += dfs(child, node, f, coins, k, children, memo);
            if (f + 1 < 14) {
                res1 += dfs(child, node, f + 1, coins, k, children, memo);
            }
        }
        memo[node][f] = Math.max(res0, res1);
        return memo[node][f];
    }

}
