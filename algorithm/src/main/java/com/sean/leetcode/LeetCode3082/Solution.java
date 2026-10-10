package com.sean.leetcode.LeetCode3082;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-10 19:27
 * @Description: https://leetcode.cn/problems/find-the-sum-of-the-power-of-all-subsequences
 * 3082. 求出所有子序列的能量和
 * 给你一个长度为 n 的整数数组 nums 和一个 正 整数 k 。
 * 一个整数数组的 能量 定义为和 等于 k 的子序列的数目。
 * 请你返回 nums 中所有子序列的 能量和 。
 * 由于答案可能很大，请你将它对 10^9 + 7 取余 后返回。
 * 1 <= n <= 100
 * 1 <= nums[i] <= 10^4
 * 1 <= k <= 100
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int sumOfPower(int[] nums, int k) {
        int n = nums.length;
        int[][] f = new int[k + 1][n + 1];
        f[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = k; j >= nums[i]; j--) {
                for (int c = i + 1; c > 0; c--) {
                    f[j][c] = (f[j][c] + f[j - nums[i]][c - 1]) % MOD;
                }
            }
        }
        long res = 0;
        for (int i = n, pow2 = 1; i > 0; i--) {
            res = (res + (long) f[k][i] * pow2) % MOD;
            pow2 = pow2 * 2 % MOD;
        }
        return (int) res;
    }

    public int sumOfPower1(int[] nums, int k) {
        long[] f = new long[k + 1];
        f[0] = 1;
        for (int x : nums) {
            for (int j = k; j >= 0; j--) {
                f[j] = (f[j] * 2 + (j >= x ? f[j - x] : 0)) % MOD;
            }
        }
        return (int) f[k];
    }

}
