package com.sean.leetcode.LeetCode3336;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-14 06:22
 * @Description: https://leetcode.cn/problems/find-the-number-of-subsequences-with-equal-gcd
 * 3336. 最大公约数相等的子序列数量
 * 给你一个整数数组 nums。
 * 请你统计所有满足以下条件的 非空 子序列 对 (seq1, seq2) 的数量：
 * 子序列 seq1 和 seq2 不相交，意味着 nums 中 不存在 同时出现在两个序列中的下标。
 * seq1 元素的 GCD 等于 seq2 元素的 GCD。
 * 返回满足条件的子序列对的总数。
 * 由于答案可能非常大，请返回其对 10^9 + 7 取余 的结果。
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 200
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        int m = Arrays.stream(nums).max().getAsInt();
        int[][][] memo = new int[n][m + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return (dfs(nums, memo, n - 1, 0, 0) - 1 + MOD) % MOD;
    }

    private int dfs(int[] nums, int[][][] memo, int i, int j, int k) {
        if (i < 0) {
            return j == k ? 1 : 0;
        }
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        long res = (long) dfs(nums, memo, i - 1, j, k) +
                dfs(nums, memo, i - 1, gcd(j, nums[i]), k) +
                dfs(nums, memo, i - 1, j, gcd(k, nums[i]));
        memo[i][j][k] = (int) (res % MOD);
        return memo[i][j][k];
    }

    private int gcd(int a, int b) {
        return a != 0 ? gcd(b % a, a) : b;
    }

}
