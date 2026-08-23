package com.sean.leetcode.LeetCode1872;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-24 06:07
 * @Description: https://leetcode.cn/problems/stone-game-viii
 * 1872. 石子游戏 VIII
 * Alice 和 Bob 玩一个游戏，两人轮流操作， Alice 先手 。
 * 总共有 n 个石子排成一行。
 * 轮到某个玩家的回合时，如果石子的数目 大于 1 ，他将执行以下操作：
 * 选择一个整数 x > 1 ，并且 移除 最左边的 x 个石子。
 * 将 移除 的石子价值之 和 累加到该玩家的分数中。
 * 将一个 新的石子 放在最左边，且新石子的值为被移除石子值之和。
 * 当只剩下 一个 石子时，游戏结束。
 * Alice 和 Bob 的 分数之差 为 (Alice 的分数 - Bob 的分数) 。
 * Alice 的目标是 最大化 分数差，Bob 的目标是 最小化 分数差。
 * 给你一个长度为 n 的整数数组 stones ，其中 stones[i] 是 从左边起 第 i 个石子的价值。
 * 请你返回在双方都采用 最优 策略的情况下，Alice 和 Bob 的 分数之差 。
 * n == stones.length
 * 2 <= n <= 10^5
 * -10^4 <= stones[i] <= 10^4
 */
public class Solution {

    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] preSum = new int[n];
        preSum[0] = stones[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + stones[i];
        }
        int[] memo = new int[n - 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        return dfs(preSum, memo, 1);
    }

    private int dfs(int[] preSum, int[] memo, int i) {
        int n = preSum.length;
        if (i == n - 1) {
            return preSum[n - 1];
        }
        if (memo[i] != Integer.MAX_VALUE) {
            return memo[i];
        }
        return memo[i] = Math.max(dfs(preSum, memo, i + 1), preSum[i] - dfs(preSum, memo, i + 1));
    }

}
