package com.sean.leetcode.LeetCode3130;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2024-08-07 09:14
 * @Description https://leetcode.cn/problems/find-all-possible-stable-binary-arrays-ii
 * 3130. 找出所有稳定的二进制数组 II
 * 给你 3 个正整数 zero ，one 和 limit 。
 * 一个二进制数组arr 如果满足以下条件，那么我们称它是 稳定的 ：
 * 0 在 arr 中出现次数 恰好 为 zero 。
 * 1 在 arr 中出现次数 恰好 为 one 。
 * arr 中每个长度超过 limit 的子数组都 同时 包含 0 和 1 。
 * 请你返回 稳定 二进制数组的 总 数目。
 * 由于答案可能很大，将它对 10^9 + 7 取余 后返回。
 * 1 <= zero, one, limit <= 1000
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private int limit;

    public int numberOfStableArrays(int zero, int one, int limit) {
        this.limit = limit;
        int[][][] memo = new int[zero + 1][one + 1][2];
        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return (process(memo, zero, one, 0) + process(memo, zero, one, 1)) % MOD;
    }

    private int process(int[][][] memo, int i, int j, int k) {
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        if (i == 0) {
            memo[i][j][k] = k == 1 && j <= limit ? 1 : 0;
            return memo[i][j][k];
        }
        if (j == 0) {
            memo[i][j][k] = k == 0 && i <= limit ? 1 : 0;
            return memo[i][j][k];
        }
        if (k == 0) {
            memo[i][j][k] = (int) (((long) process(memo, i - 1, j, 0)
                    + process(memo, i - 1, j, 1)
                    + (i > limit ? MOD - process(memo, i - limit - 1, j, 1) : 0)) % MOD);
        } else {
            memo[i][j][k] = (int) (((long) process(memo, i, j - 1, 0)
                    + process(memo, i, j - 1, 1)
                    + (j > limit ? MOD - process(memo, i, j - limit - 1, 0) : 0)) % MOD);
        }
        return memo[i][j][k];
    }

}
