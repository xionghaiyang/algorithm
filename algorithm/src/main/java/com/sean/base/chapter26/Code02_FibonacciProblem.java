package com.sean.base.chapter26;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-21 19:36
 * @Description: TODO
 */
public class Code02_FibonacciProblem {

    public int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    public int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int res = 1;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res += pre;
            pre = tmp;
        }
        return res;
    }

    //O(logN)
    public int f3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    private int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] t = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = product(res, t);
            }
            t = product(t, t);
        }
        return res;
    }

    private int[][] product(int[][] a, int[][] b) {
        int n = a.length;
        int m = b[0].length;
        int k = a[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int c = 0; c < k; c++) {
                    res[i][j] += a[i][c] * b[c][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Code02_FibonacciProblem solution = new Code02_FibonacciProblem();
        for (int i = 1; i <= 50; i++) {
            int res1 = solution.f1(i);
            int res2 = solution.f2(i);
            int res3 = solution.f3(i);
            if (res1 != res2 || res1 != res3) {
                System.out.println("Oops!");
                break;
            }
        }
    }

}
