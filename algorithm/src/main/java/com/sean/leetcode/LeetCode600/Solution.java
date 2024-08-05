package com.sean.leetcode.LeetCode600;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-16 12:40
 * @Description: https://leetcode.cn/problems/non-negative-integers-without-consecutive-ones/
 * 600. 不含连续1的非负整数
 * 给定一个正整数 n ，请你统计在 [0, n] 范围的非负整数中，有多少个整数的二进制表示中不存在 连续的 1 。
 */
public class Solution {

    private char[] s;
    private int[][] dp;

    public int findIntegers(int n) {
        s = Integer.toBinaryString(n).toCharArray();
        int m = s.length;
        dp = new int[m][2];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(0, false, true);
    }

    private int process(int i, boolean pre, boolean isLimit) {
        if (i == s.length) {
            return 1;
        }
        if (!isLimit && dp[i][pre ? 1 : 0] != -1) {
            return dp[i][pre ? 1 : 0];
        }
        int up = isLimit ? s[i] - '0' : 1;
        int res = process(i + 1, false, isLimit && up == 0);//填0
        if (!pre && up == 1) {
            res += process(i + 1, true, isLimit);//填1
        }
        if (!isLimit) {
            dp[i][pre ? 1 : 0] = res;
        }
        return res;
    }

    public int findIntegers1(int n) {
        int[] dp = new int[31];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < 31; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        int pre = 0, res = 0;
        for (int i = 29; i >= 0; i--) {
            int val = 1 << i;
            if ((n & val) != 0) {
                res += dp[i + 1];
                if (pre == 1) {
                    break;
                }
                pre = 1;
            } else {
                pre = 0;
            }
            if (i == 0) {
                res++;
            }
        }
        return res;
    }

}
