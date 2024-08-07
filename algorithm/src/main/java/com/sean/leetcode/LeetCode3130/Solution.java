package com.sean.leetcode.LeetCode3130;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-08-07 09:14
 * @Description https://leetcode.cn/problems/find-all-possible-stable-binary-arrays-ii/
 * 3130. 找出所有稳定的二进制数组 II
 * 给你 3 个正整数 zero ，one 和 limit 。
 * 一个二进制数组arr 如果满足以下条件，那么我们称它是 稳定的 ：
 * 0 在 arr 中出现次数 恰好 为 zero 。
 * 1 在 arr 中出现次数 恰好 为 one 。
 * arr 中每个长度超过 limit 的子数组都 同时 包含 0 和 1 。
 * 请你返回 稳定 二进制数组的 总 数目。
 * 由于答案可能很大，将它对 10^9 + 7 取余 后返回。
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private int[][][] dp;
    private int limit;

    public int numberOfStableArrays(int zero, int one, int limit) {
        dp = new int[zero + 1][one + 1][2];
        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        this.limit = limit;
        return (process(zero, one, 0) + process(zero, one, 1)) % MOD;
    }

    private int process(int zero, int one, int lastBit) {
        if (zero == 0) {
            return (lastBit == 0 || one > limit) ? 0 : 1;
        } else if (one == 0) {
            return (lastBit == 1 || zero > limit) ? 0 : 1;
        }
        if (dp[zero][one][lastBit] == -1) {
            int res = 0;
            if (lastBit == 0) {
                res = (process(zero - 1, one, 0) + process(zero - 1, one, 1)) % MOD;
                if (zero > limit) {
                    res = (res - process(zero - limit - 1, one, 1) + MOD) % MOD;
                }
            } else {
                res = (process(zero, one - 1, 0) + process(zero, one - 1, 1)) % MOD;
                if (one > limit) {
                    res = (res - process(zero, one - limit - 1, 0) + MOD) % MOD;
                }
            }
            dp[zero][one][lastBit] = res % MOD;
        }
        return dp[zero][one][lastBit];
    }

}
