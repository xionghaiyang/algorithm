package com.sean.leetcode.LeetCode1466;


import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-07 15:19
 * @Description: https://leetcode.cn/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
 * 1466. 重新规划路线
 * n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。
 * 因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。
 * 去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
 * 路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
 * 今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
 * 请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
 * 题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
 */
public class Solution {

    public int minReorder(int n, int[][] connections) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int x = connections[i][0];
            int y = connections[i][1];
            g[x].add(i);
            g[y].add(i);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int res = 0;
        boolean[] visited = new boolean[n];
        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int index : g[x]) {
                if (visited[index]) {
                    continue;
                }
                visited[index] = true;
                int a = connections[index][0];
                int b = connections[index][1];
                res += (a == x ? 1 : 0);
                a = (a == x ? b : a);
                queue.offer(a);
            }
        }
        return res;
    }

}
