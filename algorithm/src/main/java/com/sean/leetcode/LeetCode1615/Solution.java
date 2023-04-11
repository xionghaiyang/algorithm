package com.sean.leetcode.LeetCode1615;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-15 08:13
 * @Description: https://leetcode.cn/problems/maximal-network-rank/
 * 1615. 最大网络秩
 * n 座城市和一些连接这些城市的道路 roads 共同组成一个基础设施网络。
 * 每个 roads[i] = [ai, bi] 都表示在城市 ai 和 bi 之间有一条双向道路。
 * 两座不同城市构成的 城市对 的 网络秩 定义为：与这两座城市 直接 相连的道路总数。
 * 如果存在一条道路直接连接这两座城市，则这条道路只计算 一次 。
 * 整个基础设施网络的 最大网络秩 是所有不同城市对中的 最大网络秩 。
 * 给你整数 n 和数组 roads，返回整个基础设施网络的 最大网络秩 。
 */
public class Solution {

    public int maximalNetworkRank1(int n, int[][] roads) {
        boolean[][] connect = new boolean[n][n];
        int[] degree = new int[n];
        for (int[] v : roads) {
            connect[v[0]][v[1]] = true;
            connect[v[1]][v[0]] = true;
            degree[v[0]]++;
            degree[v[1]]++;
        }
        int maxRank = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int rank = degree[i] + degree[j] - (connect[i][j] ? 1 : 0);
                maxRank = Math.max(maxRank, rank);
            }
        }
        return maxRank;
    }

    public int maximalNetworkRank(int n, int[][] roads) {
        boolean[][] connect = new boolean[n][n];
        int[] degree = new int[n];
        for (int[] road : roads) {
            int x = road[0];
            int y = road[1];
            connect[x][y] = true;
            connect[y][x] = true;
            degree[x]++;
            degree[y]++;
        }
        int first = -1;
        int second = -2;
        List<Integer> firstArr = new ArrayList<>();
        List<Integer> secondArr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] > first) {
                second = first;
                secondArr = new ArrayList<>(firstArr);
                first = degree[i];
                firstArr.clear();
                firstArr.add(i);
            } else if (degree[i] == first) {
                firstArr.add(i);
            } else if (degree[i] > second) {
                secondArr.clear();
                second = degree[i];
                secondArr.add(i);
            } else if (degree[i] == second) {
                secondArr.add(i);
            }
        }
        if (firstArr.size() == 1) {
            int u = firstArr.get(0);
            for (int v : secondArr) {
                if (!connect[u][v]) {
                    return first + second;
                }
            }
            return first + second - 1;
        } else {
            int m = roads.length;
            if (firstArr.size() * (firstArr.size() - 1) / 2 > m) {
                return first * 2;
            }
            for (int u : firstArr) {
                for (int v : firstArr) {
                    if (u != v && !connect[u][v]) {
                        return first * 2;
                    }
                }
            }
            return first * 2 - 1;
        }
    }

}
