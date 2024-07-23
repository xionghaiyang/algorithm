package com.sean.leetcode.LeetCode3098;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xionghaiyang
 * @Date 2024-07-23 08:36
 * @Description https://leetcode.cn/problems/find-the-sum-of-subsequence-powers/
 * 3098. 求出所有子序列的能量和
 * 给你一个长度为 n 的整数数组 nums 和一个 正 整数 k 。
 * 一个子序列 的 能量 定义为子序列中 任意 两个元素的差值绝对值的 最小值 。
 * 请你返回 nums 中长度 等于 k 的 所有 子序列的 能量和 。
 * 由于答案可能会很大，将答案对 10^9 + 7 取余 后返回。
 */
public class Solution {

    static final int MOD = 1_000_000_007, INF = 0x3f3f3f3f;

    public int sumOfPowers(int[] nums, int k) {
        int n = nums.length;
        int res = 0;
        Map<Integer, Integer>[][] dp = new Map[n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = new HashMap<>();
            }
        }
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            dp[i][1].put(INF, 1);
            for (int j = 0; j < i; j++) {
                int diff = Math.abs(nums[i] - nums[j]);
                for (int p = 2; p <= k; p++) {
                    for (Map.Entry<Integer, Integer> entry : dp[j][p - 1].entrySet()) {
                        int v = entry.getKey(), cnt = entry.getValue();
                        int currKey = Math.min(diff, v);
                        dp[i][p].put(currKey, (dp[i][p].getOrDefault(currKey, 0) + cnt) % MOD);
                    }
                }
            }
            for (Map.Entry<Integer, Integer> entry : dp[i][k].entrySet()) {
                int v = entry.getKey(), cnt = entry.getValue();
                res = (int) ((res + 1L * v * cnt % MOD) % MOD);
            }
        }
        return res;
    }

}
