package com.sean.leetcode.LeetCode2906;

/**
 * @Author xionghaiyang
 * @Date 2026-03-24 06:49
 * @Description https://leetcode.cn/problems/construct-product-matrix
 * 2906. 构造乘积矩阵
 * 给你一个下标从 0 开始、大小为 n * m 的二维整数矩阵 grid ，定义一个下标从 0 开始、大小为 n * m 的的二维矩阵 p。
 * 如果满足以下条件，则称 p 为 grid 的 乘积矩阵 ：
 * 对于每个元素 p[i][j] ，它的值等于除了 grid[i][j] 外所有元素的乘积。
 * 乘积对 12345 取余数。
 * 返回 grid 的乘积矩阵。
 * 1 <= n == grid.length <= 10^5
 * 1 <= m == grid[i].length <= 10^5
 * 2 <= n * m <= 10^5
 * 1 <= grid[i][j] <= 10^9
 */
public class Solution {

    private static final int MOD = 12345;

    public int[][] constructProductMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] res = new int[m][n];
        long pre = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = (int) pre;
                pre = pre * grid[i][j] % MOD;
            }
        }
        long suf = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                res[i][j] = (int) (res[i][j] * suf % MOD);
                suf = suf * grid[i][j] % MOD;
            }
        }
        return res;
    }

}
