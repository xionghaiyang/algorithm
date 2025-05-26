package com.sean.leetcode.LeetCode1857;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-05-26 08:29
 * @Description https://leetcode.cn/problems/largest-color-value-in-a-directed-graph
 * 1857. 有向图中最大颜色值
 * 给你一个 有向图 ，它含有 n 个节点和 m 条边。
 * 节点编号从 0 到 n - 1 。
 * 给你一个字符串 colors ，其中 colors[i] 是小写英文字母，表示图中第 i 个节点的 颜色 （下标从 0 开始）。
 * 同时给你一个二维数组 edges ，其中 edges[j] = [aj, bj] 表示从节点 aj 到节点 bj 有一条 有向边 。
 * 图中一条有效 路径 是一个点序列 x1 -> x2 -> x3 -> ... -> xk ，对于所有 1 <= i < k ，从 xi 到 xi+1 在图中有一条有向边。
 * 路径的 颜色值 是路径中 出现次数最多 颜色的节点数目。
 * 请你返回给定图中有效路径里面的 最大颜色值 。
 * 如果图中含有环，请返回 -1 。
 * n == colors.length
 * m == edges.length
 * 1 <= n <= 10^5
 * 0 <= m <= 10^5
 * colors 只含有小写英文字母。
 * 0 <= aj, bj < n
 */
public class Solution {

    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        int[] deg = new int[n];
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            if (x == y) {
                return -1;
            }
            g[x].add(y);
            deg[y]++;
        }
        int res = 0;
        int visited = 0;
        int[][] f = new int[n][26];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (deg[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            visited++;
            f[u][colors.charAt(u) - 'a']++;
            res = Math.max(res, f[u][colors.charAt(u) - 'a']);
            for (int v : g[u]) {
                for (int i = 0; i < 26; i++) {
                    f[v][i] = Math.max(f[v][i], f[u][i]);
                }
                if (--deg[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return visited < n ? -1 : res;
    }

}
