package com.sean.leetcode;

/**
 * 使用最小花费爬楼梯
 * https://leetcode-cn.com/problems/min-cost-climbing-stairs/
 */
public class LeetCode746 {

    public static int minCostClimbingStairs1(int[] cost) {
        int n = cost.length;
        int p = 0, q = 0;
        for (int i = 2; i <= n; i++) {
            int r = Math.min(q + cost[i - 1], p + cost[i - 2]);
            p = q;
            q = r;
        }
        return q;
    }

    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{10,15,20}));
        System.out.println(minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));

        System.out.println(minCostClimbingStairs1(new int[]{10,15,20}));
        System.out.println(minCostClimbingStairs1(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }

}
