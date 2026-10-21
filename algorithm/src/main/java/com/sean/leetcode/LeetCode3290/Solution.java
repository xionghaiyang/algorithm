package com.sean.leetcode.LeetCode3290;

import java.util.Arrays;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-21 17:50
 * @Description: https://leetcode.cn/problems/maximum-multiplication-score
 * 3290. 最高乘法得分
 * 给你一个大小为 4 的整数数组 a 和一个大小 至少为 4 的整数数组 b。
 * 你需要从数组 b 中选择四个下标 i0, i1, i2, 和 i3，并满足 i0 < i1 < i2 < i3。
 * 你的得分将是 a[0] * b[i0] + a[1] * b[i1] + a[2] * b[i2] + a[3] * b[i3] 的值。
 * 返回你能够获得的 最大 得分。
 * a.length == 4
 * 4 <= b.length <= 10^5
 * -10^5 <= a[i], b[i] <= 10^5
 */
public class Solution {

    public long maxScore(int[] a, int[] b) {
        int n = b.length;
        long[][] memo = new long[n][4];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], Long.MIN_VALUE);
        }
        return dfs(a, b, memo, n - 1, 3);
    }

    private long dfs(int[] a, int[] b, long[][] memo, int i, int j) {
        if (j < 0) {
            return 0;
        }
        if (i < 0) {
            return Long.MIN_VALUE / 2;
        }
        if (memo[i][j] != Long.MIN_VALUE) {
            return memo[i][j];
        }
        return memo[i][j] = Math.max(dfs(a, b, memo, i - 1, j), dfs(a, b, memo, i - 1, j - 1) + (long) a[j] * b[i]);
    }

    public long maxScore1(int[] a, int[] b) {
        int n = b.length;
        long[][] f = new long[n + 1][5];
        Arrays.fill(f[0], 1, 5, Long.MIN_VALUE / 2);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                f[i + 1][j + 1] = Math.max(f[i][j + 1], f[i][j] + (long) a[j] * b[i]);
            }
        }
        return f[n][4];
    }

    public long maxScore2(int[] a, int[] b) {
        long[] f = new long[5];
        Arrays.fill(f, 1, 5, Long.MIN_VALUE / 2);
        for (int y : b) {
            for (int j = 3; j >= 0; j--) {
                f[j + 1] = Math.max(f[j + 1], f[j] + (long) a[j] * y);
            }
        }
        return f[4];
    }

}
