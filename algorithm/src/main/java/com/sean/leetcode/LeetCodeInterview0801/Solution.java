package com.sean.leetcode.LeetCodeInterview0801;

/**
 * @Author xionghaiyang
 * @Date 2026-03-13 21:26
 * @Description https://leetcode.cn/problems/three-steps-problem-lcci
 * 面试题 08.01. 三步问题
 * 三步问题。
 * 有个小孩正在上楼梯，楼梯有 n 阶台阶，小孩一次可以上 1 阶、2 阶或 3 阶。
 * 实现一种方法，计算小孩有多少种上楼梯的方式。
 * 结果可能很大，你需要对结果模 1000000007。
 * n 范围在[1, 1000000]之间
 */
public class Solution {

    private final int MOD = 1_000_000_007;

    public int waysToStep(int n) {
        if (n <= 2) {
            return n;
        }
        long[][] A = new long[][]{{1, 1, 1}, {1, 0, 0}, {0, 1, 0}};
        long[][] f0 = new long[][]{{2}, {1}, {1}};
        long[][] res = acc(A, n, f0);
        return (int) res[2][0];
    }

    //(A^n)*f0
    private long[][] acc(long[][] A, int n, long[][] f0) {
        int m = A.length;
        long[][] res = new long[m][m];
        for (int i = 0; i < m; i++) {
            res[i][i] = 1;
        }
        while (n > 0) {
            if ((n & 1) != 0) {
                res = multiply(res, A);
            }
            A = multiply(A, A);
            n >>= 1;
        }
        res = multiply(res, f0);
        return res;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int p = a.length, q = a[0].length, r = b[0].length;
        long[][] res = new long[p][r];
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < q; k++) {
                    res[i][j] = (res[i][j] + (a[i][k] * b[k][j]) % MOD) % MOD;
                }
            }
        }
        return res;
    }

}
