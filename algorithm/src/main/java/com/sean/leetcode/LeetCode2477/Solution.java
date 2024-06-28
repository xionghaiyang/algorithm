package com.sean.leetcode.LeetCode2477;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-05 13:58
 * @Description: https://leetcode.cn/problems/minimum-fuel-cost-to-report-to-the-capital/
 * 2477. 到达首都的最少油耗
 * 给你一棵 n 个节点的树（一个无向、连通、无环图），
 * 每个节点表示一个城市，
 * 编号从 0 到 n - 1 ，
 * 且恰好有 n - 1 条路。
 * 0 是首都。
 * 给你一个二维整数数组 roads ，其中 roads[i] = [ai, bi] ，表示城市 ai 和 bi 之间有一条 双向路 。
 * 每个城市里有一个代表，他们都要去首都参加一个会议。
 * 每座城市里有一辆车。
 * 给你一个整数 seats 表示每辆车里面座位的数目。
 * 城市里的代表可以选择乘坐所在城市的车，或者乘坐其他城市的车。
 * 相邻城市之间一辆车的油耗是一升汽油。
 * 请你返回到达首都最少需要多少升汽油。
 */
public class Solution {

    private long res;

    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length + 1;
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] road : roads) {
            int x = road[0];
            int y = road[1];
            g[x].add(y);
            g[y].add(x);
        }
        dfs(0, -1, g, seats);
        return res;
    }

    private int dfs(int x, int fa, List<Integer>[] g, int seats) {
        int size = 1;
        for (int y : g[x]) {
            if (y != fa) {
                size += dfs(y, x, g, seats);
            }
        }
        if (x > 0) {
            res += (size - 1) / seats + 1;
        }
        return size;
    }

}
