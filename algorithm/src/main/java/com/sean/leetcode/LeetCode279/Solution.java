package com.sean.leetcode.LeetCode279;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 14:19
 * @Description: https://leetcode.cn/problems/perfect-squares
 * 279. 完全平方数
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
 * 例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * 1 <= n <= 10^4
 */
public class Solution {

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        int res = process(dp, n);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int process(int[] dp, int i) {
        if (dp[i] != -1) {
            return dp[i];
        }
        if (i == 0) {
            dp[i] = 0;
            return dp[i];
        }
        int res = Integer.MAX_VALUE;
        for (int j = 1; j * j <= i; j++) {
            res = Math.min(res, process(dp, i - j * j));
        }
        if (res != Integer.MAX_VALUE) {
            res++;
        }
        dp[i] = res;
        return dp[i];
    }

}
