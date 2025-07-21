package com.sean.leetcode.LeetCode3352;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-07-21 14:07
 * @Description https://leetcode.cn/problems/count-k-reducible-numbers-less-than-n
 * 3352. 统计小于 N 的 K 可约简整数
 * 给你一个 二进制 字符串 s，它表示数字 n 的二进制形式。
 * 同时，另给你一个整数 k。
 * 如果整数 x 可以通过最多 k 次下述操作约简到 1 ，则将整数 x 称为 k-可约简 整数：
 * 将 x 替换为其二进制表示中的置位数（即值为 1 的位）。
 * 例如，数字 6 的二进制表示是 "110"。
 * 一次操作后，它变为 2（因为 "110" 中有两个置位）。
 * 再对 2（二进制为 "10"）进行操作后，它变为 1（因为 "10" 中有一个置位）。
 * 返回小于 n 的正整数中有多少个是 k-可约简 整数。
 * 由于答案可能很大，返回结果需要对 10^9 + 7 取余。
 * 二进制中的置位是指二进制表示中值为 1 的位。
 * 1 <= s.length <= 800
 * s 中没有前导零。
 * s 仅由字符 '0' 和 '1' 组成。
 * 1 <= k <= 5
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int countKReducibleNumbers(String s, int k) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        long res = 0;
        int[] f = new int[n];
        for (int i = 1; i < n; i++) {
            f[i] = f[Integer.bitCount(i)] + 1;
            if (f[i] <= k) {
                //计算有多少个二进制数恰好有i个1
                res = (res + dfs(0, i, true, str, memo)) % MOD;
            }
        }
        return (int) (res % MOD);
    }

    private int dfs(int i, int left, boolean isLimit, char[] str, int[][] memo) {
        if (i == str.length) {
            return !isLimit && left == 0 ? 1 : 0;
        }
        if (!isLimit && memo[i][left] != -1) {
            return memo[i][left];
        }
        int up = isLimit ? str[i] - '0' : 1;
        int res = 0;
        for (int d = 0; d <= Math.min(up, left); d++) {
            res = (res + dfs(i + 1, left - d, isLimit && d == up, str, memo)) % MOD;
        }
        if (!isLimit) {
            memo[i][left] = res;
        }
        return res;
    }

}
