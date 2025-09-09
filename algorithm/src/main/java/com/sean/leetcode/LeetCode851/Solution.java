package com.sean.leetcode.LeetCode851;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-09-09 17:04
 * @Description https://leetcode.cn/problems/loud-and-rich
 * 851. 喧闹和富有
 * 有一组 n 个人作为实验对象，从 0 到 n - 1 编号，其中每个人都有不同数目的钱，以及不同程度的安静值（quietness）。
 * 为了方便起见，我们将编号为 x 的人简称为 "person x "。
 * 给你一个数组 richer ，其中 richer[i] = [ai, bi] 表示 person ai 比 person bi 更有钱。
 * 另给你一个整数数组 quiet ，其中 quiet[i] 是 person i 的安静值。
 * richer 中所给出的数据 逻辑自洽（也就是说，在 person x 比 person y 更有钱的同时，不会出现 person y 比 person x 更有钱的情况 ）。
 * 现在，返回一个整数数组 answer 作为答案，其中 answer[x] = y 的前提是，在所有拥有的钱肯定不少于 person x 的人中，person y 是最不安静的人（也就是安静值 quiet[y] 最小的人）。
 * n == quiet.length
 * 1 <= n <= 500
 * 0 <= quiet[i] < n
 * quiet 的所有值 互不相同
 * 0 <= richer.length <= n * (n - 1) / 2
 * 0 <= ai, bi < n
 * ai != bi
 * richer 中的所有数对 互不相同
 * 对 richer 的观察在逻辑上是一致的
 */
public class Solution {

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] arr : richer) {
            int a = arr[0], b = arr[1];
            g[b].add(a);
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            dfs(g, quiet, i, res);
        }
        return res;
    }

    private void dfs(List<Integer>[] g, int[] quiet, int u, int[] res) {
        if (res[u] != -1) {
            return;
        }
        res[u] = u;
        for (int v : g[u]) {
            dfs(g, quiet, v, res);
            if (quiet[res[v]] < quiet[res[u]]) {
                res[u] = res[v];
            }
        }
    }

    public int[] loudAndRich1(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        int[] inDegree = new int[n];
        for (int[] r : richer) {
            g[r[0]].add(r[1]);
            inDegree[r[1]]++;
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = i;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : g[u]) {
                if (quiet[res[u]] < quiet[res[v]]) {
                    res[v] = res[u];
                }
                if (--inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return res;
    }

    public int[] loudAndRich2(int[][] richer, int[] quiet) {
        int n = quiet.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = i;
        }
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int[] r : richer) {
                int r0 = r[0], r1 = r[1];
                if (quiet[res[r0]] < quiet[res[r1]]) {
                    res[r1] = res[r0];
                    flag = true;
                }
            }
        }
        return res;
    }

}
