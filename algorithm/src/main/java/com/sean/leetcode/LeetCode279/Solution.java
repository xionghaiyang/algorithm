package com.sean.leetcode.LeetCode279;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 14:19
 * @Description: https://leetcode.cn/problems/perfect-squares/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 279. 完全平方数
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
 * 例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 */
public class Solution {

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        int res = process(n, dp);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int process(int cur, int[] dp) {
        if (dp[cur] != -1) {
            return dp[cur];
        }
        if (cur == 0) {
            dp[cur] = 0;
            return dp[cur];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i * i <= cur; i++) {
            res = Math.min(res, process(cur - i * i, dp));
        }
        if (res != Integer.MAX_VALUE) {
            res++;
        }
        dp[cur] = res;
        return res;
    }

}
