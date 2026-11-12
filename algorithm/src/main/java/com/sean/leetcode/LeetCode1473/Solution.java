package com.sean.leetcode.LeetCode1473;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-12 17:39
 * @Description: https://leetcode.cn/problems/paint-house-iii
 * 1473. 粉刷房子 III
 * 在一个小城市里，有 m 个房子排成一排，你需要给每个房子涂上 n 种颜色之一（颜色编号为 1 到 n ）。
 * 有的房子去年夏天已经涂过颜色了，所以这些房子不可以被重新涂色。
 * 我们将连续相同颜色尽可能多的房子称为一个街区。
 * （比方说 houses = [1,2,2,3,3,2,1,1] ，它包含 5 个街区  [{1}, {2,2}, {3,3}, {2}, {1,1}] 。）
 * 给你一个数组 houses ，一个 m * n 的矩阵 cost 和一个整数 target ，其中：
 * houses[i]：是第 i 个房子的颜色，0 表示这个房子还没有被涂色。
 * cost[i][j]：是将第 i 个房子涂成颜色 j+1 的花费。
 * 请你返回房子涂色方案的最小总花费，使得每个房子都被涂色后，恰好组成 target 个街区。
 * 如果没有可用的涂色方案，请返回 -1 。
 * m == houses.length == cost.length
 * n == cost[i].length
 * 1 <= m <= 100
 * 1 <= n <= 20
 * 1 <= target <= m
 * 0 <= houses[i] <= n
 * 1 <= cost[i][j] <= 10^4
 */
public class Solution {

    private static final int INF = 0x3f3f3f3f;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        //f[i][j][k]:考虑前i间房子，且第i间房子的颜色编号为j,前i间房子形成的分区数量为k的所有方案中的最小上色成本
        int[][][] f = new int[m + 1][n + 1][target + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                f[i][j][0] = INF;
            }
        }
        for (int i = 1; i <= m; i++) {
            int color = houses[i - 1];
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= target; k++) {
                    //分区数量大于房子数量，无效
                    if (k > i) {
                        f[i][j][k] = INF;
                        continue;
                    }
                    //第i间房间已经上色
                    if (color != 0) {
                        //与本来的颜色相同
                        if (j == color) {
                            int min = INF;
                            for (int p = 1; p <= n; p++) {
                                if (p != j) {
                                    min = Math.min(min, f[i - 1][p][k - 1]);
                                }
                            }
                            f[i][j][k] = Math.min(f[i - 1][j][k], min);
                        } else {
                            f[i][j][k] = INF;
                        }

                    } else {//第i间房间尚未上色
                        int min = INF;
                        for (int p = 1; p <= n; p++) {
                            if (p != j) {
                                min = Math.min(min, f[i - 1][p][k - 1]);
                            }
                        }
                        f[i][j][k] = Math.min(min, f[i - 1][j][k]) + cost[i - 1][j - 1];
                    }
                }
            }
        }
        int res = INF;
        for (int i = 1; i <= n; i++) {
            res = Math.min(res, f[m][i][target]);
        }
        return res == INF ? -1 : res;
    }

}
