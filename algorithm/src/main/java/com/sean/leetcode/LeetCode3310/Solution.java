package com.sean.leetcode.LeetCode3310;

import java.util.*;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-05 16:44
 * @Description: https://leetcode.cn/problems/remove-methods-from-project
 * 3310. 移除可疑的方法
 * 你正在维护一个项目，该项目有 n 个方法，编号从 0 到 n - 1。
 * 给你两个整数 n 和 k，以及一个二维整数数组 invocations，其中 invocations[i] = [ai, bi] 表示方法 ai 调用了方法 bi。
 * 已知如果方法 k 存在一个已知的 bug。
 * 那么方法 k 以及它直接或间接调用的任何方法都被视为 可疑方法 ，我们需要从项目中移除这些方法。
 * 只有当一组方法没有被这组之外的任何方法调用时，这组方法才能被移除。
 * 返回一个数组，包含移除所有 可疑方法 后剩下的所有方法。
 * 你可以以任意顺序返回答案。
 * 如果无法移除 所有 可疑方法，则 不 移除任何方法。
 * 1 <= n <= 10^5
 * 0 <= k <= n - 1
 * 0 <= invocations.length <= 2 * 10^5
 * invocations[i] == [ai, bi]
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * invocations[i] != invocations[j]
 */
public class Solution {

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] invocation : invocations) {
            int a = invocation[0], b = invocation[1];
            g[a].add(b);
        }
        boolean[] isSuspicious = new boolean[n];
        dfs(k, g, isSuspicious);
        for (int[] invocation : invocations) {
            int a = invocation[0], b = invocation[1];
            if (!isSuspicious[a] && isSuspicious[b]) {
                List<Integer> res = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    res.add(i);
                }
                return res;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isSuspicious[i]) {
                res.add(i);
            }
        }
        return res;
    }

    private void dfs(int u, List<Integer>[] g, boolean[] isSuspicious) {
        isSuspicious[u] = true;
        for (int v : g[u]) {
            if (!isSuspicious[v]) {
                dfs(v, g, isSuspicious);
            }
        }
    }

}
