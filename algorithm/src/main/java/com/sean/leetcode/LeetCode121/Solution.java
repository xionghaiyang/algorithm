package com.sean.leetcode.LeetCode121;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-15 19:47
 * @Description: https://leetcode.cn/problems/best-time-to-buy-and-sell-stock
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
 * 设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。
 * 如果你不能获取任何利润，返回 0 。
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^4
 */
public class Solution {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int res = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, prices[i]);
            res = Math.max(res, prices[i] - min);
        }
        return res;
    }

}
