package com.sean.leetcode.LeetCode2410;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-06-01 10:20
 * @Description https://leetcode.cn/problems/maximum-matching-of-players-with-trainers
 * 2410. 运动员和训练师的最大匹配数
 * 给你一个下标从 0 开始的整数数组 players ，其中 players[i] 表示第 i 名运动员的 能力 值，
 * 同时给你一个下标从 0 开始的整数数组 trainers ，其中 trainers[j] 表示第 j 名训练师的 训练能力值 。
 * 如果第 i 名运动员的能力值 小于等于 第 j 名训练师的能力值，那么第 i 名运动员可以 匹配 第 j 名训练师。
 * 除此以外，每名运动员至多可以匹配一位训练师，每位训练师最多可以匹配一位运动员。
 * 请你返回满足上述要求 players 和 trainers 的 最大 匹配数。
 * 1 <= players.length, trainers.length <= 10^5
 * 1 <= players[i], trainers[j] <= 10^9
 */
public class Solution {

    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int n = players.length, m = trainers.length;
        int res = 0;
        for (int i = n - 1, j = m - 1; i >= 0; i--) {
            if (j >= 0 && trainers[j] >= players[i]) {
                res++;
                j--;
            }
        }
        return res;
    }

}
