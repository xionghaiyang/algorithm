package com.sean.leetcode.LeetCode1000;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-04-04 08:19
 * @Description: https://leetcode.cn/problems/minimum-cost-to-merge-stones/
 * 1000. 合并石头的最低成本
 * 有 N 堆石头排成一排，第 i 堆中有 stones[i] 块石头。
 * 每次移动（move）需要将连续的 K 堆石头合并为一堆，而这个移动的成本为这 K 堆石头的总数。
 * 找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。
 */
public class Solution {

    private int k;
    private int[][][] dp;
    private int[] preSum;
    private static final int INF = 0x3f3f3f3f;

    public int mergeStones1(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) {
            return -1;
        }
        this.k = k;
        this.dp = new int[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        this.preSum = new int[n];
        int s = 0;
        for (int i = 0; i < n; i++) {
            dp[i][i][1] = 0;
            s += stones[i];
            preSum[i] = s;
        }
        return get(0, n - 1, 1);
    }

    private int get(int left, int right, int t) {
        if (dp[left][right][t] != -1) {
            return dp[left][right][t];
        }
        if (t > right - left + 1) {
            return INF;
        }
        if (t == 1) {
            int res = get(left, right, k);
            if (res == INF) {
                dp[left][right][t] = INF;
            }
            dp[left][right][t] = res + (preSum[right] - (left == 0 ? 0 : preSum[left - 1]));
            return dp[left][right][t];
        }
        int res = INF;
        for (int i = left; i < right; i += (k - 1)) {
            res = Math.min(res, get(left, i, 1) + get(i + 1, right, t - 1));
        }
        dp[left][right][t] = res;
        return dp[left][right][t];
    }

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) {
            return -1;
        }
        int INF = 0x3f3f3f3f;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }
        int[] sum = new int[n];
        int s = 0;
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
            s += stones[i];
            sum[i] = s;
        }
        for (int len = 2; len <= n; len++) {
            for (int left = 0; left < n && left + len - 1 < n; left++) {
                int right = left + len - 1;
                for (int p = left; p < right; p += (k - 1)) {
                    dp[left][right] = Math.min(dp[left][right], dp[left][p] + dp[p + 1][right]);
                }
                if ((right - left) % (k - 1) == 0) {
                    dp[left][right] += sum[right] - (left == 0 ? 0 : sum[left - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

}
