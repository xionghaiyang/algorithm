package com.sean.leetcode.LeetCode3700;

/**
 * @Author: xionghaiyang
 * @Date: 2026-06-24 06:08
 * @Description: https://leetcode.cn/problems/number-of-zigzag-arrays-ii
 * 3700. 锯齿形数组的总数 II
 * 给你三个整数 n、l 和 r。
 * 长度为 n 的锯齿形数组定义如下：
 * 每个元素的取值范围为 [l, r]。
 * 任意 两个 相邻的元素都不相等。
 * 任意 三个 连续的元素不能构成一个 严格递增 或 严格递减 的序列。
 * 返回满足条件的锯齿形数组的总数。
 * 由于答案可能很大，请将结果对 10^9 + 7 取余数。
 * 序列 被称为 严格递增 需要满足：当且仅当每个元素都严格大于它的前一个元素（如果存在）。
 * 序列 被称为 严格递减 需要满足，当且仅当每个元素都严格小于它的前一个元素（如果存在）。
 * 3 <= n <= 10^9
 * 1 <= l < r <= 75
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int k = r - l + 1;
        long[][] m = new long[k * 2][k * 2];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < i; j++) {
                m[i][k + j] = 1;
            }
            for (int j = i + 1; j < k; j++) {
                m[k + i][j] = 1;
            }
        }
        long[][] f1 = new long[k * 2][1];
        for (int i = 0; i < k * 2; i++) {
            f1[i][0] = 1;
        }
        long[][] fn = powMul(m, n - 1, f1);
        long res = 0;
        for (long[] row : fn) {
            res += row[0];
        }
        return (int) (res % MOD);
    }

    //a^n * f0
    private long[][] powMul(long[][] a, int n, long[][] f0) {
        long[][] res = f0;
        while (n > 0) {
            if ((n & 1) > 0) {
                res = mul(a, res);
            }
            a = mul(a, a);
            n >>= 1;
        }
        return res;
    }

    //a * b
    private long[][] mul(long[][] a, long[][] b) {
        long[][] res = new long[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return res;
    }

}
