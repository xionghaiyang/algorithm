package com.sean.leetcode.LeetCode1563;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-17 05:59
 * @Description: https://leetcode.cn/problems/stone-game-v
 * 1563. 石子游戏 V
 * 几块石子 排成一行 ，每块石子都有一个关联值，关联值为整数，由数组 stoneValue 给出。
 * 游戏中的每一轮：Alice 会将这行石子分成两个 非空行（即，左侧行和右侧行）；Bob 负责计算每一行的值，即此行中所有石子的值的总和。
 * Bob 会丢弃值最大的行，Alice 的得分为剩下那行的值（每轮累加）。
 * 如果两行的值相等，Bob 让 Alice 决定丢弃哪一行。
 * 下一轮从剩下的那一行开始。
 * 只 剩下一块石子 时，游戏结束。
 * Alice 的分数最初为 0 。
 * 返回 Alice 能够获得的最大分数 。
 * 1 <= stoneValue.length <= 500
 * 1 <= stoneValue[i] <= 10^6
 */
public class Solution {

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[][] memo = new int[n][n];
        return process(stoneValue, memo, 0, n - 1);
    }

    private int process(int[] stoneValue, int[][] memo, int left, int right) {
        if (left == right) {
            return 0;
        }
        if (memo[left][right] != 0) {
            return memo[left][right];
        }
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += stoneValue[i];
        }
        for (int i = left, sumLeft = 0; i < right; i++) {
            sumLeft += stoneValue[i];
            int sumRight = sum - sumLeft;
            if (sumLeft < sumRight) {
                memo[left][right] = Math.max(memo[left][right], process(stoneValue, memo, left, i) + sumLeft);
            } else if (sumLeft > sumRight) {
                memo[left][right] = Math.max(memo[left][right], process(stoneValue, memo, i + 1, right) + sumRight);
            } else {
                memo[left][right] = Math.max(memo[left][right], Math.max(process(stoneValue, memo, left, i), process(stoneValue, memo, i + 1, right)) + sumLeft);
            }
        }
        return memo[left][right];
    }

}
