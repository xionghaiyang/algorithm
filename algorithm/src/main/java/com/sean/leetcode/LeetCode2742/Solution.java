package com.sean.leetcode.LeetCode2742;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-06-28 15:40
 * @Description https://leetcode.cn/problems/painting-the-walls
 * 2742. 给墙壁刷油漆
 * 给你两个长度为 n 下标从 0 开始的整数数组 cost 和 time ，分别表示给 n 堵不同的墙刷油漆需要的开销和时间。
 * 你有两名油漆匠：
 * 一位需要 付费 的油漆匠，刷第 i 堵墙需要花费 time[i] 单位的时间，开销为 cost[i] 单位的钱。
 * 一位 免费 的油漆匠，刷 任意 一堵墙的时间为 1 单位，开销为 0 。
 * 但是必须在付费油漆匠 工作 时，免费油漆匠才会工作。
 * 请你返回刷完 n 堵墙最少开销为多少。
 * 1 <= cost.length <= 500
 * cost.length == time.length
 * 1 <= cost[i] <= 10^6
 * 1 <= time[i] <= 500
 */
public class Solution {

    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        //memo[i][j]:考虑前i个物品，剩余还需要凑出j个体积，此时的最小价值和
        int[][] memo = new int[n][n + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return process(cost, time, memo, n - 1, n);
    }

    private int process(int[] cost, int[] time, int[][] memo, int i, int j) {
        if (j <= 0) {
            return 0;
        }
        if (i < 0) {
            return Integer.MAX_VALUE / 2;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        return memo[i][j] = Math.min(process(cost, time, memo, i - 1, j - time[i] - 1) + cost[i], process(cost, time, memo, i - 1, j));
    }

    public int paintWalls1(int[] cost, int[] time) {
        int n = cost.length;
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
        for (int i = 0; i < n; i++) {
            int c = cost[i], t = time[i] + 1;
            for (int j = n; j > 0; j--) {
                f[j] = Math.min(f[j], f[Math.max(j - t, 0)] + c);
            }
        }
        return f[n];
    }

}
