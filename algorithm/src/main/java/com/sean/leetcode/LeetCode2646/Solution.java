package com.sean.leetcode.LeetCode2646;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-06 11:07
 * @Description: https://leetcode.cn/problems/minimize-the-total-price-of-the-trips/
 * 2646. 最小化旅行的价格总和
 * 现有一棵无向、无根的树，树中有 n 个节点，按从 0 到 n - 1 编号。
 * 给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。
 * 每个节点都关联一个价格。
 * 给你一个整数数组 price ，其中 price[i] 是第 i 个节点的价格。
 * 给定路径的 价格总和 是该路径上所有节点的价格之和。
 * 另给你一个二维整数数组 trips ，其中 trips[i] = [starti, endi] 表示您从节点 starti 开始第 i 次旅行，
 * 并通过任何你喜欢的路径前往节点 endi 。
 * 在执行第一次旅行之前，你可以选择一些 非相邻节点 并将价格减半。
 * 返回执行所有旅行的最小价格总和。
 */
public class Solution {

    private List<Integer>[] g;
    private int[] price;
    private int[] cnt;
    private int end;

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            g[from].add(to);
            g[to].add(from);
        }
        cnt = new int[n];
        for (int[] trip : trips) {
            end = trip[1];
            dfs(trip[0], -1);
        }
        this.price = price;
        int[] res = dp(0, -1);
        return Math.min(res[0], res[1]);
    }

    private boolean dfs(int x, int fa) {
        if (x == end) {
            cnt[x]++;
            //找到end
            return true;
        }
        for (int y : g[x]) {
            if (y != fa && dfs(y, x)) {
                //x是end的祖先节点，也就在路径上
                cnt[x]++;
                return true;
            }
        }
        //未找到end
        return false;
    }

    private int[] dp(int x, int fa) {
        //x价格不变
        int notHalve = price[x] * cnt[x];
        //x价格减半
        int halve = notHalve / 2;
        for (int y : g[x]) {
            if (y != fa) {
                //计算y价格不变/减半的最小价值总和
                int[] res = dp(y, x);
                //x价格不变，那么y价格可以不变，可以减半，取这两种情况的最小值
                notHalve += Math.min(res[0], res[1]);
                //x价格减半，那么y价格只能不变
                halve += res[0];
            }
        }
        return new int[]{notHalve, halve};
    }

}
