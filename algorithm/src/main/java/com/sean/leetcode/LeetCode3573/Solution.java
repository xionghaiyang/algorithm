package com.sean.leetcode.LeetCode3573;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-06-08 08:47
 * @Description https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-v
 * 3573. 买卖股票的最佳时机 V
 * 给你一个整数数组 prices，其中 prices[i] 是第 i 天股票的价格（美元），以及一个整数 k。
 * 你最多可以进行 k 笔交易，每笔交易可以是以下任一类型：
 * 普通交易：在第 i 天买入，然后在之后的第 j 天卖出，其中 i < j。你的利润是 prices[j] - prices[i]。
 * 做空交易：在第 i 天卖出，然后在之后的第 j 天买回，其中 i < j。你的利润是 prices[i] - prices[j]。
 * 注意：你必须在开始下一笔交易之前完成当前交易。
 * 此外，你不能在已经进行买入或卖出操作的同一天再次进行买入或卖出操作。
 * 通过进行 最多 k 笔交易，返回你可以获得的最大总利润。
 * 2 <= prices.length <= 10^3
 * 1 <= prices[i] <= 10^9
 * 1 <= k <= prices.length / 2
 */
public class Solution {

    private int[] prices;
    private long[][][] dp;

    public long maximumProfit(int[] prices, int k) {
        this.prices = prices;
        int n = prices.length;
        dp = new long[n][k + 1][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        return process(n - 1, k, 0);
    }

    //在[0..i]中完成至多j笔交易，第i天结束时的状态为state的情况下的最大收益
    private long process(int i, int j, int state) {
        if (j < 0) {
            return Long.MIN_VALUE / 2;
        }
        if (i < 0) {
            return state > 0 ? Long.MIN_VALUE / 2 : 0;
        }
        if (dp[i][j][state] != -1) {
            return dp[i][j][state];
        }
        //做空操作的核心步骤:
        //1、借入股票（没有股票时卖股票），预期某股票未来价格会下跌，向券商借股票，卖出
        //2、买回归还（有股票时买回股票），等待股票价格如预期般下跌，买入股票，归还券商
        if (state == 0) {//未持有股票
            dp[i][j][state] = Math.max(process(i - 1, j, 0), //前一天未持有股票
                    Math.max(process(i - 1, j, 1) + prices[i] //前一天持有股票，今天卖出股票（普通交易）
                            , process(i - 1, j, 2) - prices[i]));//前一天处于做空状态，今天买回股票 （做空交易）
        } else if (state == 1) {//持有股票
            dp[i][j][state] = Math.max(process(i - 1, j, 1),//前一天持有股票
                    process(i - 1, j - 1, 0) - prices[i]); //前一天未持有股票，今天买入股票（普通交易）
        } else {//做空
            dp[i][j][state] = Math.max(process(i - 1, j, 2),//前一天处于做空状态
                    process(i - 1, j - 1, 0) + prices[i]); // 前一天未持有股票，今天卖出股票 （做空交易）
        }
        return dp[i][j][state];
    }

}
