package com.sean.leetcode.LeetCode3621;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-07-21 12:48
 * @Description https://leetcode.cn/problems/number-of-integers-with-popcount-depth-equal-to-k-i
 * 3621. 位计数深度为 K 的整数数目 I
 * 给你两个整数 n 和 k。
 * 对于任意正整数 x，定义以下序列：
 * p0 = x
 * pi+1 = popcount(pi)，对于所有 i >= 0，其中 popcount(y) 是 y 的二进制表示中 1 的数量。
 * 这个序列最终会达到值 1。
 * x 的 popcount-depth （位计数深度）定义为使得 pd = 1 的 最小 整数 d >= 0。
 * 例如，如果 x = 7（二进制表示 "111"）。
 * 那么，序列是：7 → 3 → 2 → 1，所以 7 的 popcount-depth 是 3。
 * 你的任务是确定范围 [1, n] 中 popcount-depth 恰好 等于 k 的整数数量。
 * 返回这些整数的数量。
 * 1 <= n <= 10^15
 * 0 <= k <= 5
 */
public class Solution {

    public long popcountDepth(long n, int k) {
        if (k == 0) {
            return 1;
        }
        char[] s = Long.toBinaryString(n).toCharArray();
        int m = s.length;
        if (k == 1) {
            return m - 1;
        }
        long[][] memo = new long[m][m + 1];
        for (int i = 0; i < m; i++) {
            Arrays.fill(memo[i], -1);
        }
        long res = 0;
        int[] f = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            f[i] = f[Integer.bitCount(i)] + 1;
            if (f[i] == k) {
                res += dfs(0, i, true, s, memo);
            }
        }
        return res;
    }

    private long dfs(int i, int left, boolean isLimit, char[] s, long[][] memo) {
        if (i == s.length) {
            return left == 0 ? 1 : 0;
        }
        if (!isLimit && memo[i][left] != -1) {
            return memo[i][left];
        }
        int up = isLimit ? s[i] - '0' : 1;
        long res = 0;
        for (int d = 0; d <= Math.min(up, left); d++) {
            res += dfs(i + 1, left - d, isLimit && d == up, s, memo);
        }
        if (!isLimit) {
            memo[i][left] = res;
        }
        return res;
    }

}
