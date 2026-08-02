package com.sean.leetcode.LeetCode1406;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-03 06:23
 * @Description: https://leetcode.cn/problems/stone-game-iii
 * 1406. 石子游戏 III
 * Alice 和 Bob 继续他们的石子游戏。
 * 几堆石子 排成一行 ，每堆石子都对应一个得分，由数组 stoneValue 给出。
 * Alice 和 Bob 轮流取石子，Alice 总是先开始。
 * 在每个玩家的回合中，该玩家可以拿走剩下石子中的的前 1、2 或 3 堆石子 。
 * 比赛一直持续到所有石头都被拿走。
 * 每个玩家的最终得分为他所拿到的每堆石子的对应得分之和。
 * 每个玩家的初始分数都是 0 。
 * 比赛的目标是决出最高分，得分最高的选手将会赢得比赛，比赛也可能会出现平局。
 * 假设 Alice 和 Bob 都采取 最优策略 。
 * 如果 Alice 赢了就返回 "Alice" ，Bob 赢了就返回 "Bob"，分数相同返回 "Tie" 。
 * 1 <= stoneValue.length <= 5 * 10^4
 * -1000 <= stoneValue[i] <= 1000
 */
public class Solution {

    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = stoneValue[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + stoneValue[i];
        }
        int[] f = new int[n + 1];
        //f[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int best = f[i + 1];
            for (int j = i + 2; j <= i + 3 && j <= n; j++) {
                best = Math.min(best, f[j]);
            }
            f[i] = suffixSum[i] - best;
        }
        int total = 0;
        for (int num : stoneValue) {
            total += num;
        }
        return f[0] * 2 != total ? (f[0] * 2 > total ? "Alice" : "Bob") : "Tie";
    }

}
