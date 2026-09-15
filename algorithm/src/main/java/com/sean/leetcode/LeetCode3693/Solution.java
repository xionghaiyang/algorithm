package com.sean.leetcode.LeetCode3693;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-15 19:24
 * @Description: https://leetcode.cn/problems/climbing-stairs-ii
 * 3693. 爬楼梯 II
 * 你正在爬一个有 n + 1 级台阶的楼梯，台阶编号从 0 到 n。
 * 你还得到了一个长度为 n 的 下标从 1 开始 的整数数组 costs，其中 costs[i] 是第 i 级台阶的成本。
 * 从第 i 级台阶，你 只能 跳到第 i + 1、i + 2 或 i + 3 级台阶。
 * 从第 i 级台阶跳到第 j 级台阶的成本定义为： costs[j] + (j - i)^2
 * 你从第 0 级台阶开始，初始 cost = 0。
 * 返回到达第 n 级台阶所需的 最小 总成本。
 * 1 <= n == costs.length <= 10^5
 * 1 <= costs[i] <= 10^4
 */
public class Solution {

    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        //dp[0]=0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + costs[i - 1] + 1;
            if (i >= 2) {
                dp[i] = Math.min(dp[i], dp[i - 2] + costs[i - 1] + 4);
            }
            if (i >= 3) {
                dp[i] = Math.min(dp[i], dp[i - 3] + costs[i - 1] + 9);
            }
        }
        return dp[n];
    }

    public int climbStairs1(int n, int[] costs) {
        int f0 = 0, f1 = 0, f2 = 0;
        for (int cost : costs) {
            int newF = Math.min(Math.min(f0 + 9, f1 + 4), f2 + 1) + cost;
            f0 = f1;
            f1 = f2;
            f2 = newF;
        }
        return f2;
    }

}
