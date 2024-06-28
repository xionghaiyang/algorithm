package com.sean.leetcode.LeetCode2742;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-06-28 15:40
 * @Description https://leetcode.cn/problems/painting-the-walls/
 * 2742. 给墙壁刷油漆
 * 给你两个长度为 n 下标从 0 开始的整数数组 cost 和 time ，分别表示给 n 堵不同的墙刷油漆需要的开销和时间。
 * 你有两名油漆匠：
 * 一位需要 付费 的油漆匠，刷第 i 堵墙需要花费 time[i] 单位的时间，开销为 cost[i] 单位的钱。
 * 一位 免费 的油漆匠，刷 任意 一堵墙的时间为 1 单位，开销为 0 。
 * 但是必须在付费油漆匠 工作 时，免费油漆匠才会工作。
 * 请你返回刷完 n 堵墙最少开销为多少。
 */
public class Solution {

    public int paintWalls(int[] cost, int[] time) {
        int n = cost.length;
        int[] f = new int[n * 2 + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[n] = 0;
        for (int i = 0; i < n; i++) {
            int[] g = new int[n * 2 + 1];
            Arrays.fill(g, Integer.MAX_VALUE / 2);
            for (int j = 0; j <= n * 2; j++) {
                //付费
                g[Math.min(j + time[i], n * 2)] = Math.min(g[Math.min(j + time[i], n * 2)], f[j] + cost[i]);
                //免费
                if (j > 0) {
                    g[j - 1] = Math.min(g[j - 1], f[j]);
                }
            }
            f = g;
        }
        int res = f[n];
        for (int i = n + 1; i <= n * 2; i++) {
            res = Math.min(res, f[i]);
        }
        return res;
    }

    public int paintWalls1(int[] cost, int[] time) {
        int n = cost.length;
        int[] f = new int[n + 2];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n + 1; j >= 0; j--) {
                f[Math.min(j + time[i], n) + 1] = Math.min(f[Math.min(j + time[i], n) + 1], f[j] + cost[i]);
            }
        }
        return Math.min(f[n], f[n + 1]);
    }

}
