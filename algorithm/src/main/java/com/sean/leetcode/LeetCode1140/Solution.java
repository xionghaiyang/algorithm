package com.sean.leetcode.LeetCode1140;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-02-22 08:20
 * @Description: https://leetcode.cn/problems/stone-game-ii
 * 1140. 石子游戏 II
 * 爱丽丝和鲍勃继续他们的石子游戏。
 * 许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。
 * 游戏以谁手中的石子最多来决出胜负。
 * 爱丽丝和鲍勃轮流进行，爱丽丝先开始。
 * 最初，M = 1。
 * 在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。然后，令 M = max(M, X)。
 * 游戏一直持续到所有石子都被拿走。
 * 假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 10^4
 */
public class Solution {

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[][][] memo = new int[n][n + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n + 1; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return process1(piles, memo, 0, 1);
    }

    //先手
    private int process1(int[] piles, int[][][] memo, int i, int M) {
        if (i == piles.length) {
            return 0;
        }
        if (memo[i][M][0] != -1) {
            return memo[i][M][0];
        }
        int n = piles.length;
        int res = 0;
        for (int X = 1, cur = 0; X <= 2 * M && i + X - 1 < n; X++) {
            cur += piles[i + X - 1];
            res = Math.max(res, cur + process2(piles, memo, i + X, Math.max(M, X)));
        }
        return memo[i][M][0] = res;
    }

    //后手
    private int process2(int[] piles, int[][][] memo, int i, int M) {
        if (i == piles.length) {
            return 0;
        }
        if (memo[i][M][1] != -1) {
            return memo[i][M][1];
        }
        int n = piles.length;
        int res = Integer.MAX_VALUE;
        for (int X = 1; X <= 2 * M && i + X - 1 < n; X++) {
            res = Math.min(res, process1(piles, memo, i + X, Math.max(M, X)));
        }
        return memo[i][M][1] = res;
    }

}
