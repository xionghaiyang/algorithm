package com.sean.leetcode.LeetCode1621;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-16 06:15
 * @Description: https://leetcode.cn/problems/number-of-sets-of-k-non-overlapping-line-segments
 * 1621. 大小为 K 的不重叠线段的数目
 * 给你一维空间的 n 个点，其中第 i 个点（编号从 0 到 n-1）位于 x = i 处，请你找到 恰好 k 个不重叠 线段且每个线段至少覆盖两个点的方案数。
 * 线段的两个端点必须都是 整数坐标 。
 * 这 k 个线段不需要全部覆盖全部 n 个点，且它们的端点 可以 重合。
 * 请你返回 k 个不重叠线段的方案数。
 * 由于答案可能很大，请将结果对 10^9 + 7 取余 后返回。
 * 2 <= n <= 1000
 * 1 <= k <= n-1
 */
public class Solution {

    private static final int MOD = 1_000_000_007;
    private static final int MAX = 1999;
    //F[i] = i!
    private static final long[] F = new long[MAX];
    //INV_F[i] = (i!)^(-1) = pow(i!,MOD-2)
    private static final long[] INV_F = new long[MAX];
    private static boolean initialized = false;

    public Solution() {
        if (initialized) {
            return;
        }
        initialized = true;
        F[0] = 1;
        for (int i = 1; i < MAX; i++) {
            F[i] = F[i - 1] * i % MOD;
        }
        INV_F[MAX - 1] = pow(F[MAX - 1], MOD - 2);
        for (int i = MAX - 1; i > 0; i--) {
            INV_F[i - 1] = INV_F[i] * i % MOD;
        }
    }

    //x^n
    private long pow(long x, int n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }

    public int numberOfSets(int n, int k) {
        return (int) comb(n + k - 1, k * 2);
    }

    private long comb(int n, int m) {
        return F[n] * INV_F[m] % MOD * INV_F[n - m] % MOD;
    }

}
