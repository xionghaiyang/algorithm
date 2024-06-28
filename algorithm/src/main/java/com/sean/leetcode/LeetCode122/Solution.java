package com.sean.leetcode.LeetCode122;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-15 19:55
 * @Description: https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 122. 买卖股票的最佳时机 II
 */
public class Solution {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        int res = 0;
        for (int i = 1; i < n; i++) {
            res += Math.max(prices[i] - prices[i - 1], 0);
        }
        return res;
    }

}
