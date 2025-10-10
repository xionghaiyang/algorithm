package com.sean.leetcode.LeetCode3147;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-10-10 10:49
 * @Description https://leetcode.cn/problems/taking-maximum-energy-from-the-mystic-dungeon
 * 3147. 从魔法师身上吸取的最大能量
 * 在神秘的地牢中，n 个魔法师站成一排。
 * 每个魔法师都拥有一个属性，这个属性可以给你提供能量。
 * 有些魔法师可能会给你负能量，即从你身上吸取能量。
 * 你被施加了一种诅咒，当你从魔法师 i 处吸收能量后，你将被立即传送到魔法师 (i + k) 处。
 * 这一过程将重复进行，直到你到达一个不存在 (i + k) 的魔法师为止。
 * 换句话说，你将选择一个起点，然后以 k 为间隔跳跃，直到到达魔法师序列的末端，在过程中吸收所有的能量。
 * 给定一个数组 energy 和一个整数k，返回你能获得的 最大 能量。
 * 1 <= energy.length <= 10^5
 * -1000 <= energy[i] <= 1000
 * 1 <= k <= energy.length - 1
 */
public class Solution {

    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfs(energy, k, dp, i));
        }
        return res;
    }

    private int dfs(int[] energy, int k, int[] dp, int i) {
        if (dp[i] != Integer.MIN_VALUE) {
            return dp[i];
        }
        int res = energy[i];
        if (i + k < energy.length) {
            res += dfs(energy, k, dp, i + k);
        }
        return dp[i] = res;
    }

}
