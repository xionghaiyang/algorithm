package com.sean.leetcode.LeetCode2412;

/**
 * @Author xionghaiyang
 * @Date 2025-01-25 19:43
 * @Description https://leetcode.cn/problems/minimum-money-required-before-transactions/
 * 2412. 完成所有交易的初始最少钱数
 * 给你一个下标从 0 开始的二维整数数组 transactions，其中transactions[i] = [costi, cashbacki] 。
 * 数组描述了若干笔交易。
 * 其中每笔交易必须以 某种顺序 恰好完成一次。
 * 在任意一个时刻，你有一定数目的钱 money ，为了完成交易 i ，money >= costi 这个条件必须为真。
 * 执行交易后，你的钱数 money 变成 money - costi + cashbacki 。
 * 请你返回 任意一种 交易顺序下，你都能完成所有交易的最少钱数 money 是多少。
 * 1 <= transactions.length <= 10^5
 * transactions[i].length == 2
 * 0 <= costi, cashbacki <= 10^9
 */
public class Solution {

    public long minimumMoney(int[][] transactions) {
        long totalLose = 0;
        int res = 0;
        for (int[] transaction : transactions) {
            int cost = transaction[0];
            int cashback = transaction[1];
            totalLose += Math.max(cost - cashback, 0);
            res = Math.max(res, Math.min(cost, cashback));
        }
        return totalLose + res;
    }

}
