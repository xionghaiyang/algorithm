package com.sean.leetcode.LeetCode1510;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-10 06:01
 * @Description: https://leetcode.cn/problems/stone-game-iv
 * 1510. 石子游戏 IV
 * Alice 和 Bob 两个人轮流玩一个游戏，Alice 先手。
 * 一开始，有 n 个石子堆在一起。
 * 每个人轮流操作，正在操作的玩家可以从石子堆里拿走 任意 非零 平方数 个石子。
 * 如果石子堆里没有石子了，则无法操作的玩家输掉游戏。
 * 给你正整数 n ，且已知两个人都采取最优策略。
 * 如果 Alice 会赢得比赛，那么返回 True ，否则返回 False 。
 * 1 <= n <= 10^5
 */
public class Solution {

    public boolean winnerSquareGame(int n) {
        int[][] memo = new int[n + 1][2];
        return process1(memo, n);
    }

    //先手
    private boolean process1(int[][] memo, int i) {
        if (memo[i][0] != 0) {
            return memo[i][0] == 1;
        }
        if (i == 0) {
            memo[i][0] = -1;
            return false;
        }
        for (int x = 1; x * x <= i; x++) {
            if (process2(memo, i - x * x)) {
                memo[i][0] = 1;
                return true;
            }
        }
        memo[i][0] = -1;
        return false;
    }

    //后手
    private boolean process2(int[][] memo, int i) {
        if (memo[i][1] != 0) {
            return memo[i][1] == 1;
        }
        if (i == 0) {
            memo[i][1] = 1;
            return true;
        }
        for (int x = 1; x * x <= i; x++) {
            if (!process1(memo, i - x * x)) {
                memo[i][1] = -1;
                return false;
            }
        }
        memo[i][1] = 1;
        return true;
    }

}
