package com.sean.leetcode.LeetCode3129;

/**
 * @Author xionghaiyang
 * @Date 2024-08-06 17:29
 * @Description https://leetcode.cn/problems/find-all-possible-stable-binary-arrays-i/description/
 * 3129. 找出所有稳定的二进制数组 I
 * 给你 3 个正整数 zero ，one 和 limit 。
 * 一个二进制数组arr 如果满足以下条件，那么我们称它是 稳定的 ：
 * 0 在 arr 中出现次数 恰好 为 zero 。
 * 1 在 arr 中出现次数 恰好 为 one 。
 * arr 中每个长度超过 limit 的
 * 子数组都 同时 包含 0 和 1 。
 * 请你返回 稳定 二进制数组的 总 数目。
 * 由于答案可能很大，将它对 10^9 + 7 取余 后返回。
 */
public class Solution {

    private static final long MOD = 1_000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {
        //dp[i][j][k]表示已经填入i个0和j个1，并且最后填入的数字为k(0或者1)的可行方案数目
        long[][][] dp = new long[zero + 1][one + 1][2];
        for (int i = 0; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
        }
        for (int j = 0; j <= Math.min(one, limit); j++) {
            dp[0][j][1] = 1;
        }
        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                if (i > limit) {
                    dp[i][j][0] = dp[i - 1][j][0] + dp[i - 1][j][1] - dp[i - limit - 1][j][1];
                } else {
                    dp[i][j][0] = dp[i - 1][j][0] + dp[i - 1][j][1];
                }
                dp[i][j][0] = (dp[i][j][0] % MOD + MOD) % MOD;
                if (j > limit) {
                    dp[i][j][1] = dp[i][j - 1][1] + dp[i][j - 1][0] - dp[i][j - limit - 1][0];
                } else {
                    dp[i][j][1] = dp[i][j - 1][1] + dp[i][j - 1][0];
                }
                dp[i][j][1] = (dp[i][j][1] % MOD + MOD) % MOD;
            }
        }
        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }

}
