package com.sean.leetcode.LeetCode2492;

import java.util.*;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-04 08:01
 * @Description: https://leetcode.cn/problems/minimum-score-of-a-path-between-two-cities
 * 2492. 两个城市间路径的最小分数
 * 给你一个正整数 n ，表示总共有 n 个城市，城市从 1 到 n 编号。
 * 给你一个二维数组 roads ，其中 roads[i] = [ai, bi, distancei] 表示城市 ai 和 bi 之间有一条 双向 道路，道路距离为 distancei 。
 * 城市构成的图不一定是连通的。
 * 两个城市之间一条路径的 分数 定义为这条路径中道路的 最小 距离。
 * 返回城市 1 和城市 n 之间的所有路径的 最小 分数。
 * 注意：
 * 一条路径指的是两个城市之间的道路序列。
 * 一条路径可以 多次 包含同一条道路，你也可以沿着路径多次到达城市 1 和城市 n 。
 * 测试数据保证城市 1 和城市n 之间 至少 有一条路径。
 * 2 <= n <= 10^5
 * 1 <= roads.length <= 10^5
 * roads[i].length == 3
 * 1 <= ai, bi <= n
 * ai != bi
 * 1 <= distancei <= 104
 * 不会有重复的边。
 * 城市 1 和城市 n 之间至少有一条路径。
 */
public class Solution {

    private int res = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] road : roads) {
            int a = road[0] - 1, b = road[1] - 1, distance = road[2];
            g[a].add(new int[]{b, distance});
            g[b].add(new int[]{a, distance});
        }
        boolean[] visited = new boolean[n];
        dfs(g, visited, 0);
        return res;
    }

    private void dfs(List<int[]>[] g, boolean[] visited, int u) {
        visited[u] = true;
        for (int[] arr : g[u]) {
            int v = arr[0], distance = arr[1];
            res = Math.min(res, distance);
            if (!visited[v]) {
                dfs(g, visited, v);
            }
        }
    }

}
