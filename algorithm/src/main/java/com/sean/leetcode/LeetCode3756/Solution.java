package com.sean.leetcode.LeetCode3756;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-08 07:39
 * @Description: https://leetcode.cn/problems/concatenate-non-zero-digits-and-multiply-by-sum-ii
 * 3756. 连接非零数字并乘以其数字和 II
 * 给你一个长度为 m 的字符串 s，其中仅包含数字。
 * 另给你一个二维整数数组 queries，其中 queries[i] = [li, ri]。
 * 对于每个 queries[i]，提取 子串 s[li..ri]，然后执行以下操作：
 * 将子串中所有 非零数字 按照原始顺序连接起来，形成一个新的整数 x。如果没有非零数字，则 x = 0。
 * 令 sum 为 x 中所有数字的 数字和 。
 * 答案为 x * sum。
 * 返回一个整数数组 answer，其中 answer[i] 是第 i 个查询的答案。
 * 由于答案可能非常大，请返回其对 10^9 + 7 取余数的结果。
 * 子串 是字符串中的一个连续、非空 字符序列。
 * 1 <= m == s.length <= 10^5
 * s 仅由数字组成。
 * 1 <= queries.length <= 10^5
 * queries[i] = [li, ri]
 * 0 <= li <= ri < m
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private static final int MAX_N = 100_001;
    private static final int[] pow10 = new int[MAX_N];
    private static boolean initialized = false;

    public Solution() {
        if (initialized) {
            return;
        }
        initialized = true;
        pow10[0] = 1;
        for (int i = 1; i < MAX_N; i++) {
            pow10[i] = (int) (pow10[i - 1] * 10L % MOD);
        }
    }

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        int[] sumD = new int[n + 1];
        int[] preNum = new int[n + 1];
        int[] sumNonZero = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            sumD[i + 1] = sumD[i] + d;
            preNum[i + 1] = d > 0 ? (int) ((preNum[i] * 10L + d) % MOD) : preNum[i];
            sumNonZero[i + 1] = sumNonZero[i] + (d > 0 ? 1 : 0);
        }
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int left = queries[i][0], right = queries[i][1] + 1;
            int len = sumNonZero[right] - sumNonZero[left];
            long x = preNum[right] - (long) preNum[left] * pow10[len] % MOD + MOD;
            res[i] = (int) (x * (sumD[right] - sumD[left]) % MOD);
        }
        return res;
    }

}
