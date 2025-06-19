package com.sean.leetcode.LeetCode122;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-15 19:55
 * @Description: https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii
 * 122. 买卖股票的最佳时机 II
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。
 * 你在任何时候 最多 只能持有 一股 股票。
 * 你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 * 1 <= prices.length <= 3 * 10^4
 * 0 <= prices[i] <= 10^4
 */
public class Solution {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        //dp[i][0]第i天结束时手上没有股票
        //dp[i][1]第i天结束时手上有股票
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int res = 0;
        for (int i = 1; i < n; i++) {
            res += Math.max(0, prices[i] - prices[i - 1]);
        }
        return res;
    }

}
