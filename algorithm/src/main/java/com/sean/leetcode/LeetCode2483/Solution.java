package com.sean.leetcode.LeetCode2483;

/**
 * @Author xionghaiyang
 * @Date 2025-12-26 12:22
 * @Description https://leetcode.cn/problems/minimum-penalty-for-a-shop
 * 2483. 商店的最少代价
 * 给你一个顾客访问商店的日志，用一个下标从 0 开始且只包含字符 'N' 和 'Y' 的字符串 customers 表示：
 * 如果第 i 个字符是 'Y' ，它表示第 i 小时有顾客到达。
 * 如果第 i 个字符是 'N' ，它表示第 i 小时没有顾客到达。
 * 如果商店在第 j 小时关门（0 <= j <= n），代价按如下方式计算：
 * 在开门期间，如果某一个小时没有顾客到达，代价增加 1 。
 * 在关门期间，如果某一个小时有顾客到达，代价增加 1 。
 * 请你返回在确保代价 最小 的前提下，商店的 最早 关门时间。
 * 注意，商店在第 j 小时关门表示在第 j 小时以及之后商店处于关门状态。
 * 1 <= customers.length <= 10^5
 * customers 只包含字符 'Y' 和 'N' 。
 */
public class Solution {

    public int bestClosingTime(String customers) {
        int res = 0, cost = 0, minCost = 0, n = customers.length();
        for (int i = 0; i < n; i++) {
            cost += customers.charAt(i) == 'N' ? 1 : -1;
            if (cost < minCost) {
                minCost = cost;
                res = i + 1;
            }
        }
        return res;
    }

}
