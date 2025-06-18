package com.sean.leetcode.LeetCode70;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-14 19:38
 * @Description: https://leetcode.cn/problems/climbing-stairs
 * 70. 爬楼梯
 * 假设你正在爬楼梯。
 * 需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。
 * 你有多少种不同的方法可以爬到楼顶呢？
 * 1 <= n <= 45
 */
public class Solution {

    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    public int climbStairs1(int n) {
        if (n <= 2) {
            return n;
        }
        int[][] a = {{1, 1}, {1, 0}};
        int[][] f = {{2}, {1}};
        int[][] res = multi(pow(a, n - 2), f);
        return res[0][0];
    }

    //a^k
    private int[][] pow(int[][] a, int k) {
        int n = a.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }
        while (k > 0) {
            if ((k & 1) == 1) {
                res = multi(res, a);
            }
            a = multi(a, a);
            k >>= 1;
        }
        return res;
    }

    //a * b
    private int[][] multi(int[][] a, int[][] b) {
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
