package com.sean.leetcode.LeetCode509;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-09 19:28
 * @Description: https://leetcode.cn/problems/fibonacci-number/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 509. 斐波那契数
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。
 * 该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 */
public class Solution {

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
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

    public int fib2(int n) {
        if (n < 2) {
            return n;
        }
        int[][] A = {{1, 1}, {1, 0}};
        int[][] B = pow(A, n - 2);
        return B[0][0] + B[1][0];
    }

    private int[][] pow(int[][] a, int n) {
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

    private int[][] multiply(int[][] a, int[][] b) {
        if (a[0].length != b.length) {
            return null;
        }
        int[][] res = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

}
