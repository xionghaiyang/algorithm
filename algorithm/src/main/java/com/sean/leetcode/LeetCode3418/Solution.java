package com.sean.leetcode.LeetCode3418;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2026-04-02 06:42
 * @Description https://leetcode.cn/problems/maximum-amount-of-money-robot-can-earn
 * 3418. 机器人可以获得的最大金币数
 * 给你一个 m x n 的网格。
 * 一个机器人从网格的左上角 (0, 0) 出发，目标是到达网格的右下角 (m - 1, n - 1)。
 * 在任意时刻，机器人只能向右或向下移动。
 * 网格中的每个单元格包含一个值 coins[i][j]：
 * 如果 coins[i][j] >= 0，机器人可以获得该单元格的金币。
 * 如果 coins[i][j] < 0，机器人会遇到一个强盗，强盗会抢走该单元格数值的 绝对值 的金币。
 * 机器人有一项特殊能力，可以在行程中 最多感化 2个单元格的强盗，从而防止这些单元格的金币被抢走。
 * 注意：机器人的总金币数可以是负数。
 * 返回机器人在路径上可以获得的 最大金币数 。
 * m == coins.length
 * n == coins[i].length
 * 1 <= m, n <= 500
 * -1000 <= coins[i][j] <= 1000
 */
public class Solution {

    private int m;
    private int n;

    public int maximumAmount(int[][] coins) {
        m = coins.length;
        n = coins[0].length;
        int[][][] memo = new int[m][n][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], Integer.MIN_VALUE);
            }
        }
        return process(coins, memo, 0, 0, 2);
    }

    private int process(int[][] coins, int[][][] memo, int i, int j, int k) {
        if (memo[i][j][k] != Integer.MIN_VALUE) {
            return memo[i][j][k];
        }
        if (i == m - 1 && j == n - 1) {
            return memo[i][j][k] = coins[i][j] < 0 && k > 0 ? 0 : coins[i][j];
        }
        int res = Integer.MIN_VALUE;
        if (i + 1 < m) {
            res = Math.max(res, coins[i][j] + process(coins, memo, i + 1, j, k));
        }
        if (j + 1 < n) {
            res = Math.max(res, coins[i][j] + process(coins, memo, i, j + 1, k));
        }
        if (coins[i][j] < 0 && k > 0) {
            if (i + 1 < m) {
                res = Math.max(res, process(coins, memo, i + 1, j, k - 1));
            }
            if (j + 1 < n) {
                res = Math.max(res, process(coins, memo, i, j + 1, k - 1));
            }
        }
        return memo[i][j][k] = res;
    }

}
