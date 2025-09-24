package com.sean.leetcode.LeetCodeLCP07;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2025-09-24 13:23
 * @Description https://leetcode.cn/problems/chuan-di-xin-xi
 * LCP 07. 传递信息
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。
 * 传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。
 * 返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 * 2 <= n <= 10
 * 1 <= k <= 5
 * 1 <= relation.length <= 90, 且 relation[i].length == 2
 * 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
 */
public class Solution {

    public int numWays(int n, int[][] relation, int k) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] edge : relation) {
            g[edge[0]].add(edge[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (k > 0 && !queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int u = queue.poll();
                for (int v : g[u]) {
                    queue.offer(v);
                }
            }
            k--;
        }
        int res = 0;
        if (k == 0) {
            while (!queue.isEmpty()) {
                if (queue.poll() == n - 1) {
                    res++;
                }
            }
        }
        return res;
    }

    public int numWays1(int n, int[][] relation, int k) {
        int[][] dp = new int[k + 1][n];
        dp[0][0] = 1;
        for (int i = 0; i < k; i++) {
            for (int[] edge : relation) {
                int u = edge[0], v = edge[1];
                dp[i + 1][v] += dp[i][u];
            }
        }
        return dp[k][n - 1];
    }

    public int numWays2(int n, int[][] relation, int k) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < k; i++) {
            int[] next = new int[n];
            for (int[] edge : relation) {
                int u = edge[0], v = edge[1];
                next[v] += dp[u];
            }
            dp = next;
        }
        return dp[n - 1];
    }

}
