package com.sean.leetcode.LeetCode3652;

/**
 * @Author xionghaiyang
 * @Date 2025-10-31 21:01
 * @Description https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-using-strategy
 * 3652. 按策略买卖股票的最佳时机
 * 给你两个整数数组 prices 和 strategy，其中：
 * prices[i] 表示第 i 天某股票的价格。
 * strategy[i] 表示第 i 天的交易策略，其中：
 * -1 表示买入一单位股票。
 * 0 表示持有股票。
 * 1 表示卖出一单位股票。
 * 同时给你一个 偶数 整数 k，你可以对 strategy 进行 最多一次 修改。
 * 一次修改包括：
 * 选择 strategy 中恰好 k 个 连续 元素。
 * 将前 k / 2 个元素设为 0（持有）。
 * 将后 k / 2 个元素设为 1（卖出）。
 * 利润 定义为所有天数中 strategy[i] * prices[i] 的 总和 。
 * 返回你可以获得的 最大 可能利润。
 * 注意： 没有预算或股票持有数量的限制，因此所有买入和卖出操作均可行，无需考虑过去的操作。
 * 2 <= prices.length == strategy.length <= 10^5
 * 1 <= prices[i] <= 10^5
 * -1 <= strategy[i] <= 1
 * 2 <= k <= prices.length
 * k 是偶数
 */
public class Solution {

    public long maxProfit(int[] prices, int[] strategy, int k) {
        long total = 0, maxSum = 0, sum = 0;
        for (int i = 0; i < prices.length; i++) {
            int p = prices[i], s = strategy[i];
            total += p * s;
            sum += p * (1 - s);
            if (i < k - 1) {//窗口长度不足k
                if (i >= k / 2 - 1) {
                    sum -= prices[i - k / 2 + 1];
                }
                continue;
            }
            maxSum = Math.max(maxSum, sum);
            sum -= prices[i - k / 2 + 1] - prices[i - k + 1] * strategy[i - k + 1];
        }
        return total + maxSum;
    }

}
