package com.sean.leetcode.LeetCode3615;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-07-17 12:08
 * @Description https://leetcode.cn/problems/longest-palindromic-path-in-graph
 * 3615. 图中的最长回文路径
 * 给你一个整数 n 和一个包含 n 个节点的 无向图 ，节点编号从 0 到 n - 1，以及一个二维数组 edges，
 * 其中 edges[i] = [ui, vi] 表示节点 ui 和节点 vi 之间有一条边。
 * 同时给你一个长度为 n 的字符串 label，其中 label[i] 是与节点 i 关联的字符。
 * 你可以从任意节点开始，移动到任意相邻节点，每个节点 最多 访问一次。
 * 返回通过访问一条路径，路径中 不包含重复 节点，所能形成的 最长回文串 的长度。
 * 回文串 是指正着读和反着读相同的字符串。
 * 1 <= n <= 14
 * n - 1 <= edges.length <= n * (n - 1) / 2
 * edges[i] == [ui, vi]
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * label.length == n
 * label 只包含小写英文字母。
 * 不存在重复边。
 */
public class Solution {

    public int maxLen(int n, int[][] edges, String label) {
        char[] s = label.toCharArray();
        int[] cnt = new int[26];
        for (char c : s) {
            cnt[c - 'a']++;
        }
        int odd = 0;
        for (int c : cnt) {
            odd += c % 2;
        }
        int max = n - Math.max(odd - 1, 0);
        if (edges.length == n * (n - 1) / 2) {
            return max;
        }
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            g[u].add(v);
            g[v].add(u);
        }
        int[][][] memo = new int[n][n][1 << n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        int res = 0;
        for (int x = 0; x < n; x++) {
            res = Math.max(res, dfs(x, x, 1 << x, g, s, memo) + 1);
            if (res == max) {
                return max;
            }
            for (int y : g[x]) {
                //保证x<y，减少状态个数和计算量
                if (x < y && s[x] == s[y]) {
                    res = Math.max(res, dfs(x, y, (1 << x) | (1 << y), g, s, memo) + 2);
                    if (res == max) {
                        return res;
                    }
                }
            }
        }
        return res;
    }

    //计算从x和y向两侧扩展，最多还能访问多少个节点(不算x和y)
    private int dfs(int x, int y, int visited, List<Integer>[] g, char[] label, int[][][] memo) {
        if (memo[x][y][visited] != -1) {
            return memo[x][y][visited];
        }
        int res = 0;
        for (int v : g[x]) {
            if ((visited & (1 << v)) > 0) {
                continue;
            }
            for (int w : g[y]) {
                if ((visited & (1 << w)) == 0 && w != v && label[w] == label[v]) {
                    //保证v<w，减少状态个数和计算量
                    res = Math.max(res, dfs(Math.min(v, w), Math.max(v, w), visited | (1 << v) | (1 << w), g, label, memo) + 2);
                }
            }
        }
        memo[x][y][visited] = res;
        return res;
    }

}
