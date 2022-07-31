package com.sean.leetcode;

/**
 * 斐波那契数
 * https://leetcode-cn.com/problems/fibonacci-number/
 */
public class LeetCode509 {

    public static int fib(int n) {
        if (n < 2) {
            return n;
        }
        int[][] A = {{1, 1}, {1, 0}};
        int[][] B = pow(A, n - 2);
        return B[0][0] + B[1][0];
    }

    private static int[][] pow(int[][] a, int n) {
        if (a.length != a[0].length) {
            return null;
        }
        int[][] res = new int[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            res[i][i] = 1;
        }
        while (n > 0) {
            if ((n & 1) == 1) {
                res = multiply(res, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return res;
    }

    private static int[][] multiply(int[][] a, int[][] b) {
        if (a[0].length != b.length) {
            return null;
        }
        int[][] res = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int t = 0; t < a[0].length; t++) {
                    res[i][j] += a[i][t] * b[t][j];
                }
            }
        }
        return res;
    }

    public int fib1(int n) {
        if (n < 2) {
            return n;
        }
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

}
