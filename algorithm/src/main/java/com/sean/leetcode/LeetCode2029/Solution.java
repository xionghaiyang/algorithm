package com.sean.leetcode.LeetCode2029;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-16 06:10
 * @Description: https://leetcode.cn/problems/stone-game-ix
 * 2029. 石子游戏 IX
 * Alice 和 Bob 再次设计了一款新的石子游戏。
 * 现有一行 n 个石子，每个石子都有一个关联的数字表示它的价值。
 * 给你一个整数数组 stones ，其中 stones[i] 是第 i 个石子的价值。
 * Alice 和 Bob 轮流进行自己的回合，Alice 先手。
 * 每一回合，玩家需要从 stones 中移除任一石子。
 * 如果玩家移除石子后，导致 所有已移除石子 的价值 总和 可以被 3 整除，那么该玩家就 输掉游戏 。
 * 如果不满足上一条，且移除后没有任何剩余的石子，那么 Bob 将会直接获胜（即便是在 Alice 的回合）。
 * 假设两位玩家均采用 最佳 决策。
 * 如果 Alice 获胜，返回 true ；如果 Bob 获胜，返回 false 。
 * 1 <= stones.length <= 10^5
 * 1 <= stones[i] <= 10^4
 */
public class Solution {

    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        for (int stone : stones) {
            cnt[stone % 3]++;
        }
        if (cnt[0] % 2 == 0) {
            return cnt[1] > 0 && cnt[2] > 0;
        }
        return Math.abs(cnt[1] - cnt[2]) > 2;
    }

}
