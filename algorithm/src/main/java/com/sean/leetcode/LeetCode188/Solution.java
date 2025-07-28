package com.sean.leetcode.LeetCode188;

/**
 * @Author xionghaiyang
 * @Date 2025-07-28 16:00
 * @Description https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv
 * 188. 买卖股票的最佳时机 IV
 * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。
 * 你最多可以完成 k 笔交易。
 * 也就是说，你最多可以买 k 次，卖 k 次。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 1 <= k <= 100
 * 1 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */
public class Solution {

    public int maxProfit(int k, int[] prices) {
        int[][] f = new int[k + 2][2];
        for (int j = 1; j <= k + 1; j++) {
            f[j][1] = Integer.MIN_VALUE / 2;
        }
        f[0][0] = Integer.MIN_VALUE / 2;
        for (int price : prices) {
            for (int j = k + 1; j > 0; j--) {
                f[j][0] = Math.max(f[j][0], f[j][1] + price);
                f[j][1] = Math.max(f[j][1], f[j - 1][0] - price);
            }
        }
        return f[k + 1][0];
    }

}
