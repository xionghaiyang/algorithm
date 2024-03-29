package com.sean.leetcode.LeetCode1599;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-06 09:39
 * @Description: https://leetcode.cn/problems/maximum-profit-of-operating-a-centennial-wheel/
 * 1599. 经营摩天轮的最大利润
 * 你正在经营一座摩天轮，该摩天轮共有 4 个座舱 ，每个座舱 最多可以容纳 4 位游客 。
 * 你可以 逆时针 轮转座舱，但每次轮转都需要支付一定的运行成本 runningCost 。
 * 摩天轮每次轮转都恰好转动 1 / 4 周。
 * 给你一个长度为 n 的数组 customers ， customers[i] 是在第 i 次轮转（下标从 0 开始）之前到达的新游客的数量。
 * 这也意味着你必须在新游客到来前轮转 i 次。
 * 每位游客在登上离地面最近的座舱前都会支付登舱成本 boardingCost ，一旦该座舱再次抵达地面，他们就会离开座舱结束游玩。
 * 你可以随时停下摩天轮，即便是 在服务所有游客之前 。
 * 如果你决定停止运营摩天轮，为了保证所有游客安全着陆，将免费进行所有后续轮转 。
 * 注意，如果有超过 4 位游客在等摩天轮，那么只有 4 位游客可以登上摩天轮，其余的需要等待 下一次轮转 。
 * 返回最大化利润所需执行的 最小轮转次数 。 如果不存在利润为正的方案，则返回 -1 。
 */
public class Solution {

    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int res = -1;
        int maxProfit = 0;
        int totalProfit = 0;
        int operations = 0;
        int customersCount = 0;
        int n = customers.length;
        for (int i = 0; i < n; i++) {
            operations++;
            customersCount += customers[i];
            int curCustomers = Math.min(customersCount, 4);
            customersCount -= curCustomers;
            totalProfit += boardingCost * curCustomers - runningCost;
            if (totalProfit > maxProfit) {
                maxProfit = totalProfit;
                res = operations;
            }
        }
        if (customersCount == 0) {
            return res;
        }
        int profitEachTime = boardingCost * 4 - runningCost;
        if (profitEachTime <= 0) {
            return res;
        }
        if (customersCount > 0) {
            int fullTimes = customersCount / 4;
            totalProfit += profitEachTime * fullTimes;
            operations += fullTimes;
            if (totalProfit > maxProfit) {
                maxProfit = totalProfit;
                res = operations;
            }
            int remainingCustomers = customersCount % 4;
            int remainingProfit = boardingCost * remainingCustomers - runningCost;
            totalProfit += remainingProfit;
            if (totalProfit > maxProfit) {
                maxProfit = totalProfit;
                operations++;
                res++;
            }
        }
        return res;
    }

}
