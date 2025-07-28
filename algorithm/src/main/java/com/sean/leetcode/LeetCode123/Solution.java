package com.sean.leetcode.LeetCode123;

/**
 * @Author xionghaiyang
 * @Date 2025-07-28 15:30
 * @Description https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii
 * 123. 买卖股票的最佳时机 III
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。
 * 你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^5
 */
public class Solution {

    public int maxProfit(int[] prices) {
        int k = 2;
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
