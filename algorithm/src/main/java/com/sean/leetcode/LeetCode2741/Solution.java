package com.sean.leetcode.LeetCode2741;

import java.util.*;

/**
 * @Author xionghaiyang
 * @Date 2024-06-26 20:55
 * @Description https://leetcode.cn/problems/special-permutations/
 * 2741. 特别的排列
 * 给你一个下标从 0 开始的整数数组 nums ，它包含 n 个 互不相同 的正整数。
 * 如果 nums 的一个排列满足以下条件，我们称它是一个特别的排列：
 * 对于 0 <= i < n - 1 的下标 i ，要么 nums[i] % nums[i+1] == 0 ，要么 nums[i+1] % nums[i] == 0 。
 * 请你返回特别排列的总数目，由于答案可能很大，请将它对 10^9 + 7 取余 后返回。
 */
public class Solution {

    int[][] dp;
    int[] nums;
    int n;
    static final int MOD = 1_000_000_007;

    public int specialPerm(int[] nums) {
        this.nums = nums;
        n = nums.length;
        dp = new int[1 << n][n];
        for (int i = 0; i < 1 << n; i++) {
            Arrays.fill(dp[i], -1);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = (res + process((1 << n) - 1, i)) % MOD;
        }
        return res;
    }

    private int process(int state, int i) {
        if (dp[state][i] != -1) {
            return dp[state][i];
        }
        if (state == (1 << i)) {
            return 1;
        }
        dp[state][i] = 0;
        for (int j = 0; j < n; j++) {
            if (i == j || (state >> j & 1) == 0) {
                continue;
            }
            if (nums[i] % nums[j] != 0 && nums[j] % nums[i] != 0) {
                continue;
            }
            dp[state][i] = (dp[state][i] + process(state ^ (1 << i), j)) % MOD;
        }
        return dp[state][i];
    }

    public int specialPerm1(int[] nums) {
        int MOD = 1_000_000_007;
        int n = nums.length;
        int[][] dp = new int[1 << n][n];
        for (int i = 0; i < n; i++) {
            dp[1 << i][i] = 1;
        }
        for (int state = 1; state < (1 << n); state++) {
            for (int i = 0; i < n; i++) {
                if ((state >> i & 1) == 0) {
                    continue;
                }
                for (int j = 0; j < n; j++) {
                    if (i == j || (state >> j & 1) == 0) {
                        continue;
                    }
                    if (nums[i] % nums[j] != 0 && nums[j] % nums[i] != 0) {
                        continue;
                    }
                    dp[state][i] = (dp[state][i] + dp[state ^ (1 << i)][j]) % MOD;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = (res + dp[(1 << n) - 1][i]) % MOD;
        }
        return res;
    }

}
