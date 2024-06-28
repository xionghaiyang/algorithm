package com.sean.leetcode.LeetCodeOffer63;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-28 21:07
 * @Description: https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/?plan=lcof&plan_progress=zq3t7ii
 * 剑指 Offer 63. 股票的最大利润
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 */
public class Solution {

    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int res = 0;
        int n = prices.length;
        for (int i = 0; i < n; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > res) {
                res = prices[i] - minprice;
            }
        }
        return res;
    }

}
