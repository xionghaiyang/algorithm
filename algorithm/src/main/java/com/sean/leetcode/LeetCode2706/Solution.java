package com.sean.leetcode.LeetCode2706;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-12-29 11:28
 * @Description: https://leetcode.cn/problems/buy-two-chocolates/description/
 * 2706. 购买两块巧克力
 * 给你一个整数数组 prices ，它表示一个商店里若干巧克力的价格。
 * 同时给你一个整数 money ，表示你一开始拥有的钱数。
 * 你必须购买 恰好 两块巧克力，而且剩余的钱数必须是 非负数 。
 * 同时你想最小化购买两块巧克力的总花费。
 * 请你返回在购买两块巧克力后，最多能剩下多少钱。
 * 如果购买任意两块巧克力都超过了你拥有的钱，请你返回 money 。
 * 注意剩余钱数必须是非负数。
 */
public class Solution {

    public int buyChoco(int[] prices, int money) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price < first) {
                second = first;
                first = price;
            } else if (price < second) {
                second = price;
            }
        }
        return money < first + second ? money : money - first - second;
    }

}
