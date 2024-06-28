package com.sean.leetcode.LeetCode815;

import java.util.*;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-26 15:14
 * @Description: https://leetcode.cn/problems/bus-routes/description/
 * 815. 公交路线
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会
 * 一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。
 * 期间仅可乘坐公交车。
 * 求出 最少乘坐的公交车数量 。
 * 如果不可能到达终点车站，返回 -1 。
 */
public class Solution {

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        int n = routes.length;
        boolean[][] edge = new boolean[n][n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int site : routes[i]) {
                List<Integer> list = map.getOrDefault(site, new ArrayList<>());
                for (int j : list) {
                    edge[i][j] = true;
                    edge[j][i] = true;
                }
                list.add(i);
                map.put(site, list);
            }
        }
        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        Queue<Integer> queue = new LinkedList<>();
        for (int bus : map.getOrDefault(source, new ArrayList<>())) {
            dis[bus] = 1;
            queue.offer(bus);
        }
        while (!queue.isEmpty()) {
            int i = queue.poll();
            for (int j = 0; j < n; j++) {
                if (edge[i][j] && dis[j] == -1) {
                    dis[j] = dis[i] + 1;
                    queue.offer(j);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int bus : map.getOrDefault(target, new ArrayList<>())) {
            if (dis[bus] != -1) {
                res = Math.min(res, dis[bus]);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
