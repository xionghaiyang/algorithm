package com.sean.leetcode.LeetCode3225;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-04-29 08:11
 * @Description https://leetcode.cn/problems/maximum-score-from-grid-operations
 * 3225. 网格图操作后的最大分数
 * 给你一个大小为 n x n 的二维矩阵 grid ，一开始所有格子都是白色的。
 * 一次操作中，你可以选择任意下标为 (i, j) 的格子，并将第 j 列中从最上面到第 i 行所有格子改成黑色。
 * 如果格子 (i, j) 为白色，且左边或者右边的格子至少一个格子为黑色，那么我们将 grid[i][j] 加到最后网格图的总分中去。
 * 请你返回执行任意次操作以后，最终网格图的 最大 总分数。
 * 1 <= n == grid.length <= 100
 * n == grid[i].length
 * 0 <= grid[i][j] <= 10^9
 */
public class Solution {

    public long maximumScore(int[][] grid) {
        int n = grid.length;
        long[][] colPreSum = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                colPreSum[j][i + 1] = colPreSum[j][i] + grid[i][j];
            }
        }
        long[][][] memo = new long[n - 1][n + 1][2];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        long res = 0;
        for (int i = 0; i <= n; i++) {
            res = Math.max(res, process(colPreSum, memo, n - 2, i, 0));
        }
        return res;
    }

    //pre表示第j+1列的黑格个数
    //dec = 1 表示第j+1列的黑格个数(pre) 小于第j + 2列的黑格个数
    private long process(long[][] colPreSum, long[][][] memo, int j, int pre, int dec) {
        if (j < 0) {
            return 0;
        }
        if (memo[j][pre][dec] != -1) {
            return memo[j][pre][dec];
        }
        long res = 0;
        //枚举第j列有cur个黑格
        for (int cur = 0; cur <= colPreSum.length; cur++) {
            if (cur == pre) {
                //情况一：相等
                //没有可以计入总分的格子
                res = Math.max(res, process(colPreSum, memo, j - 1, cur, 0));
            } else if (cur < pre) {
                //情况二：右边黑格多
                res = Math.max(res, process(colPreSum, memo, j - 1, cur, 1) + colPreSum[j][pre] - colPreSum[j][cur]);
            } else if (dec == 0) {
                //情况三：cur > pre >= 第j+2列的黑格个数
                res = Math.max(res, process(colPreSum, memo, j - 1, cur, 0) + colPreSum[j + 1][cur] - colPreSum[j + 1][pre]);
            } else if (pre == 0) {
                //情况四（凹形）：cur > pre < 第j + 2列的黑格个数
                res = Math.max(res, process(colPreSum, memo, j - 1, cur, 0));
            }
        }
        return memo[j][pre][dec] = res;
    }

}
