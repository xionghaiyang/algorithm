package com.sean.leetcode.LeetCode2791;

import java.util.*;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-29 06:38
 * @Description: https://leetcode.cn/problems/count-paths-that-can-form-a-palindrome-in-a-tree
 * 2791. 树中可以形成回文的路径数
 * 给你一棵 树（即，一个连通、无向且无环的图），根 节点为 0 ，由编号从 0 到 n - 1 的 n 个节点组成。
 * 这棵树用一个长度为 n 、下标从 0 开始的数组 parent 表示，其中 parent[i] 为节点 i 的父节点，由于节点 0 为根节点，所以 parent[0] == -1 。
 * 另给你一个长度为 n 的字符串 s ，其中 s[i] 是分配给 i 和 parent[i] 之间的边的字符。
 * s[0] 可以忽略。
 * 找出满足 u < v ，且从 u 到 v 的路径上分配的字符可以 重新排列 形成 回文 的所有节点对 (u, v) ，并返回节点对的数目。
 * 如果一个字符串正着读和反着读都相同，那么这个字符串就是一个 回文 。
 * n == parent.length == s.length
 * 1 <= n <= 10^5
 * 对于所有 i >= 1 ，0 <= parent[i] <= n - 1 均成立
 * parent[0] == -1
 * parent 表示一棵有效的树
 * s 仅由小写英文字母组成
 */
public class Solution {

    public long countPalindromePaths(List<Integer> parent, String s) {
        int n = parent.size();
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            g[parent.get(i)].add(i);
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        return dfs(g, s.toCharArray(), cnt, 0, 0);
    }

    private long dfs(List<Integer>[] g, char[] s, Map<Integer, Integer> cnt, int u, int xor) {
        long res = 0;
        for (int v : g[u]) {
            int x = xor ^ (1 << (s[v] - 'a'));
            //x^x = 0
            res += cnt.getOrDefault(x, 0);
            for (int i = 0; i < 26; i++) {
                //x^(x^(1<<i)) = 1<<i
                res += cnt.getOrDefault(x ^ (1 << i), 0);
            }
            cnt.merge(x, 1, Integer::sum);
            res += dfs(g, s, cnt, v, x);
        }
        return res;
    }

}
