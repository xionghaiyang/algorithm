package com.sean.leetcode.LeetCode746;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-14 19:45
 * @Description: https://leetcode.cn/problems/min-cost-climbing-stairs/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 746. 使用最小花费爬楼梯
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。
 * 一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * 请你计算并返回达到楼梯顶部的最低花费。
 */
public class Solution {

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    public int minCostClimbingStairs1(int[] cost) {
        int n = cost.length;
        int p = 0, q = 0;
        for (int i = 2; i <= n; i++) {
            int r = Math.min(p + cost[i - 2], q + cost[i - 1]);
            p = q;
            q = r;
        }
        return q;
    }

}
