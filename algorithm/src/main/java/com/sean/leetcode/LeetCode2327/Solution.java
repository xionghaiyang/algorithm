package com.sean.leetcode.LeetCode2327;

/**
 * @Author xionghaiyang
 * @Date 2025-09-09 06:06
 * @Description https://leetcode.cn/problems/number-of-people-aware-of-a-secret
 * 2327. 知道秘密的人数
 * 在第 1 天，有一个人发现了一个秘密。
 * 给你一个整数 delay ，表示每个人会在发现秘密后的 delay 天之后，每天 给一个新的人 分享 秘密。
 * 同时给你一个整数 forget ，表示每个人在发现秘密 forget 天之后会 忘记 这个秘密。
 * 一个人 不能 在忘记秘密那一天及之后的日子里分享秘密。
 * 给你一个整数 n ，请你返回在第 n 天结束时，知道秘密的人数。
 * 由于答案可能会很大，请你将结果对 10^9 + 7 取余 后返回。
 * 2 <= n <= 1000
 * 1 <= delay < forget <= n
 */
public class Solution {

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        final int MOD = 1_000_000_007;
        int[] dp = new int[n];
        dp[0] = 1;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (i + delay >= n) {
                cnt = (cnt + dp[i]) % MOD;
            }
            for (int j = i + delay; j < Math.min(i + forget, n); j++) {
                dp[j] = (dp[j] + dp[i]) % MOD;
            }
        }
        return (dp[n - 1] + cnt) % MOD;
    }

}
