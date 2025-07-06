package com.sean.leetcode.LeetCode3603;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-07-06 18:12
 * @Description https://leetcode.cn/problems/minimum-cost-path-with-alternating-directions-ii
 * 3603. 交替方向的最小路径代价 II
 * 给你两个整数 m 和 n，分别表示网格的行数和列数。
 * 进入单元格 (i, j) 的成本定义为 (i + 1) * (j + 1)。
 * 另外给你一个二维整数数组 waitCost，其中 waitCost[i][j] 定义了在该单元格 等待 的成本。
 * 你从第 1 秒开始在单元格 (0, 0)。
 * 每一步，你都遵循交替模式：
 * 在 奇数秒 ，你必须向 右 或向 下 移动到 相邻 的单元格，并支付其进入成本。
 * 在 偶数秒 ，你必须原地 等待 ，并支付 waitCost[i][j]。
 * 返回到达 (m - 1, n - 1) 所需的 最小 总成本。
 * 1 <= m, n <= 10^5
 * 2 <= m * n <= 10^5
 * waitCost.length == m
 * waitCost[0].length == n
 * 0 <= waitCost[i][j] <= 10^5
 */
public class Solution {

    private int m;
    private int n;
    private int[][] waitCost;

    public long minCost(int m, int n, int[][] waitCost) {
        this.m = m;
        this.n = n;
        this.waitCost = waitCost;
        long[][] dp = new long[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }
        return process(m - 1, n - 1, dp);
    }

    private long process(int i, int j, long[][] dp) {
        if (dp[i][j] != Long.MAX_VALUE) {
            return dp[i][j];
        }
        if (i == 0 && j == 0) {
            dp[i][j] = (i + 1) * (j + 1);
            return dp[i][j];
        }
        long res = (i + 1) * (j + 1);
        if (!(i == m - 1 && j == n - 1)) {
            res += waitCost[i][j];
        }
        long res1 = Long.MAX_VALUE;
        if (i > 0) {
            res1 = Math.min(res1, process(i - 1, j, dp));
        }
        if (j > 0) {
            res1 = Math.min(res1, process(i, j - 1, dp));
        }
        dp[i][j] = res + res1;
        return dp[i][j];
    }

}
