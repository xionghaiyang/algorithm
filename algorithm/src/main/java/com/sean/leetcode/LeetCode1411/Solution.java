package com.sean.leetcode.LeetCode1411;

/**
 * @Author xionghaiyang
 * @Date 2026-01-03 09:10
 * @Description https://leetcode.cn/problems/number-of-ways-to-paint-n-3-grid
 * 1411. 给 N x 3 网格图涂色的方案数
 * 你有一个 n x 3 的网格图 grid ，你需要用 红，黄，绿 三种颜色之一给每一个格子上色，且确保相邻格子颜色不同（也就是有相同水平边或者垂直边的格子颜色不同）。
 * 给你网格图的行数 n 。
 * 请你返回给 grid 涂色的方案数。
 * 由于答案可能会非常大，请你返回答案对 10^9 + 7 取余的结果。
 * n == grid.length
 * grid[i].length == 3
 * 1 <= n <= 5000
 */
public class Solution {

    private static final int MOD = 1_000_000_007;

    public int numOfWays(int n) {
        int[][] m = {{5, -2}, {1, 0}};
        int[][] f1 = {{12}, {3}};
        int[][] fn = powMul(m, n - 1, f1);
        return (fn[0][0] + MOD) % MOD;
    }

    //(m^n)*f0
    private int[][] powMul(int[][] m, int n, int[][] f0) {
        int[][] res = f0;
        while (n > 0) {
            if ((n & 1) > 0) {
                res = mul(m, res);
            }
            m = mul(m, m);
            n >>= 1;
        }
        return res;
    }

    //a * b
    private int[][] mul(int[][] a, int[][] b) {
        int[][] res = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    res[i][j] = (int) ((res[i][j] + (long) a[i][k] * b[k][j] % MOD) % MOD);
                }
            }
        }
        return res;
    }

}
