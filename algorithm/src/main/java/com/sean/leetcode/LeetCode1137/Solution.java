package com.sean.leetcode.LeetCode1137;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-09 19:49
 * @Description: https://leetcode.cn/problems/n-th-tribonacci-number/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 1137. 第 N 个泰波那契数
 * 泰波那契序列 Tn 定义如下： 
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 */
public class Solution {

    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }
        int p = 0, q = 0, r = 1, s = 1;
        for (int i = 3; i <= n; i++) {
            p = q;
            q = r;
            r = s;
            s = p + q + r;
        }
        return s;
    }

    public int tribonacci1(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }
        int[][] A = {{1, 1, 0}, {1, 0, 1}, {1, 0, 0}};
        int[][] B = pow(A, n - 2);
        return B[0][0] + B[1][0];
    }

    private int[][] pow(int[][] a, int n) {
        int[][] res = new int[3][3];
        for (int i = 0; i < 3; i++) {
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

    private int[][] multiply(int[][] a, int[][] b) {
        int[][] res = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

}
