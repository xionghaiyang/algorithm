package com.sean.leetcode.LeetCode1140;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-22 08:20
 * @Description: https://leetcode.cn/problems/stone-game-ii/
 * 1140. 石子游戏 II
 * 爱丽丝和鲍勃继续他们的石子游戏。
 * 许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。
 * 游戏以谁手中的石子最多来决出胜负。
 * 爱丽丝和鲍勃轮流进行，爱丽丝先开始。
 * 最初，M = 1。
 * 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
 * 游戏一直持续到所有石子都被拿走。
 * 假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。
 */
public class Solution {

    int[][][] dp;

    public int stoneGameII(int[] piles) {
        if (piles == null || piles.length == 0) {
            return 0;
        }
        int n = piles.length;
        dp = new int[n + 1][n + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return process1(piles, 0, n, 1);
    }

    //爱丽丝先手情况下可以得到石头的最大数量
    private int process1(int[] piles, int i, int n, int M) {
        if (dp[i][M][0] != -1) {
            return dp[i][M][0];
        }
        if (i == n) {
            dp[i][M][0] = 0;
            return 0;
        }
        int res = 0;
        int cur = 0;
        for (int X = 1; X <= 2 * M && i + X - 1 < n; X++) {
            cur += piles[i + X - 1];
            res = Math.max(res, cur + process2(piles, i + X, n, Math.max(M, X)));
        }
        dp[i][M][0] = res;
        return res;
    }

    //爱丽丝后手情况下可以得到石头的最大数量
    private int process2(int[] piles, int i, int n, int M) {
        if (dp[i][M][1] != -1) {
            return dp[i][M][1];
        }
        if (i == n) {
            dp[i][M][1] = 0;
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int X = 1; X <= 2 * M && i + X - 1 < n; X++) {
            res = Math.min(res, process1(piles, i + X, n, Math.max(M, X)));
        }
        dp[i][M][1] = res;
        return res;
    }

}
