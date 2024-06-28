package com.sean.leetcode.LeetCode1976;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-03-05 14:51
 * @Description: https://leetcode.cn/problems/number-of-ways-to-arrive-at-destination/
 * 1976. 到达目的地的方案数
 * 你在一个城市里，城市由 n 个路口组成，路口编号为 0 到 n - 1 ，某些路口之间有 双向 道路。
 * 输入保证你可以从任意路口出发到达其他任意路口，且任意两个路口之间最多有一条路。
 * 给你一个整数 n 和二维整数数组 roads ，其中 roads[i] = [ui, vi, timei] 表示在路口 ui 和 vi 之间有一条需要花费 timei 时间才能通过的道路。
 * 你想知道花费 最少时间 从路口 0 出发到达路口 n - 1 的方案数。
 * 请返回花费 最少时间 到达目的地的 路径数目 。
 * 由于答案可能很大，将结果对 10^9 + 7 取余 后返回。
 */
public class Solution {

    public int countPaths(int n, int[][] roads) {
        int mod = 1_000_000_007;
        List<int[]>[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int time = road[2];
            g[u].add(new int[]{v, time});
            g[v].add(new int[]{u, time});
        }
        long[] dis = new long[n];
        Arrays.fill(dis, Long.MAX_VALUE);
        int[] ways = new int[n];
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.offer(new long[]{0, 0});
        dis[0] = 0;
        ways[0] = 1;
        while (!pq.isEmpty()) {
            long[] arr = pq.poll();
            int u = (int) arr[0];
            long t = arr[1];
            if (t > dis[u]) {
                continue;
            }
            for (int[] next : g[u]) {
                int v = next[0];
                long time = (long) next[1];
                if (t + time < dis[v]) {
                    dis[v] = t + time;
                    ways[v] = ways[u];
                    pq.offer(new long[]{v, t + time});
                } else if (t + time == dis[v]) {
                    ways[v] = (ways[u] + ways[v]) % mod;
                }
            }
        }
        return ways[n - 1];
    }

}
