package com.sean.leetcode.LeetCode3715;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-10-13 17:36
 * @Description https://leetcode.cn/problems/sum-of-perfect-square-ancestors
 * 3715. 完全平方数的祖先个数总和
 * 给你一个整数 n，以及一棵以节点 0 为根、包含 n 个节点（编号从 0 到 n - 1）的无向树。
 * 该树由一个长度为 n - 1 的二维数组 edges 表示，其中 edges[i] = [ui, vi] 表示在节点 ui 与节点 vi 之间有一条无向边。
 * 同时给你一个整数数组 nums，其中 nums[i] 是分配给节点 i 的正整数。
 * 定义值 ti 为：节点 i 的 祖先 节点中，满足乘积 nums[i] * nums[ancestor] 为 完全平方数 的祖先个数。
 * 请返回所有节点 i（范围为 [1, n - 1]）的 ti 之和。
 * 说明：
 * 在有根树中，节点 i 的祖先是指从节点 i 到根节点 0 的路径上、不包括 i 本身的所有节点。
 * 完全平方数是可以表示为某个整数与其自身乘积的数，例如 1、4、9、16。
 * 1 <= n <= 10^5
 * edges.length == n - 1
 * edges[i] = [ui, vi]
 * 0 <= ui, vi <= n - 1
 * nums.length == n
 * 1 <= nums[i] <= 10^5
 * 输入保证 edges 表示一棵有效的树。
 */
public class Solution {

    private static final int MAX = 100_001;
    private static final int[] core = new int[MAX];
    private static boolean initialized = false;

    private void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        for (int i = 1; i < MAX; i++) {
            if (core[i] == 0) {
                for (int j = 1; i * j * j < MAX; j++) {
                    core[i * j * j] = i;
                }
            }
        }
    }

    public long sumOfAncestors(int n, int[][] edges, int[] nums) {
        init();
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            g[u].add(v);
            g[v].add(u);
        }
        return dfs(0, -1, g, nums, new HashMap<>());
    }

    private long dfs(int x, int p, List<Integer>[] g, int[] nums, Map<Integer, Integer> map) {
        int cr = core[nums[x]];
        int c = map.getOrDefault(cr, 0);
        long res = c;
        map.put(cr, c + 1);
        for (int y : g[x]) {
            if (y != p) {
                res += dfs(y, x, g, nums, map);
            }
        }
        map.put(cr, c);
        return res;
    }

}
