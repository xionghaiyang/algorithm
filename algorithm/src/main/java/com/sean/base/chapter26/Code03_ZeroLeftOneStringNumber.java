package com.sean.base.chapter26;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-11-21 19:54
 * @Description: TODO
 */
public class Code03_ZeroLeftOneStringNumber {

    public int getNum1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }

    private int process(int i, int n) {
        if (i == n - 1) {
            return 2;
        }
        if (i == n) {
            return 1;
        }
        return process(i + 1, n) + process(i + 2, n);
    }

    public int getNum2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int pre = 1;
        int cur = 1;
        int tmp = 0;
        for (int i = 2; i < n + 1; i++) {
            tmp = cur;
            cur += pre;
            pre = tmp;
        }
        return cur;
    }

    public int getNum3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    private int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = product(res, tmp);
            }
            tmp = product(tmp, tmp);
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
        Code03_ZeroLeftOneStringNumber solution = new Code03_ZeroLeftOneStringNumber();
        for (int i = 0; i <= 20; i++) {
            int res1 = solution.getNum1(i);
            int res2 = solution.getNum2(i);
            int res3 = solution.getNum3(i);
            if (res1 != res2 || res1 != res3) {
                System.out.println("Oops!");
                break;
            }
        }
    }

}
