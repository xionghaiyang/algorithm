package com.sean.leetcode.LeetCode1012;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-20 08:30
 * @Description: https://leetcode.cn/problems/numbers-with-repeated-digits/
 * 1012. 至少有 1 位重复的数字
 * 给定正整数 n，返回在 [1, n] 范围内具有 至少 1 位 重复数字的正整数的个数。
 */
public class Solution {

    int[][] dp;

    public int numDupDigitsAtMostN1(int n) {
        String sn = String.valueOf(n);
        dp = new int[sn.length()][1 << 10];
        for (int i = 0; i < sn.length(); i++) {
            Arrays.fill(dp[i], -1);
        }
        return n + 1 - f(0, sn, 0, true);
    }

    private int f(int mask, String sn, int i, boolean same) {
        if (i == sn.length()) {
            return 1;
        }
        if (!same && dp[i][mask] >= 0) {
            return dp[i][mask];
        }
        int res = 0, t = same ? (sn.charAt(i) - '0') : 9;
        for (int k = 0; k <= t; k++) {
            if ((mask & (1 << k)) != 0) {
                continue;
            }
            res += f(mask == 0 && k == 0 ? mask : mask | (1 << k), sn, i + 1, same && k == t);
        }
        if (!same) {
            dp[i][mask] = res;
        }
        return res;
    }


    public int numDupDigitsAtMostN(int n) {
        String sn = String.valueOf(n);
        return n + 1 - f1(0, sn, 0, true);
    }

    private int f1(int mask, String sn, int i, boolean same) {
        if (i == sn.length()) {
            return 1;
        }
        int t = same ? sn.charAt(i) - '0' : 9, res = 0, c = Integer.bitCount(mask) + 1;
        for (int k = 0; k <= t; k++) {
            if ((mask & (1 << k)) != 0) {
                continue;
            }
            if (same && k == t) {
                res += f1(mask | (1 << t), sn, i + 1, true);
            } else if (mask == 0 && k == 0) {
                res += f1(0, sn, i + 1, false);
            } else {
                res += f2(sn.length() - 1 - i, 10 - c);
            }
        }
        return res;
    }

    private int f2(int x, int y) {
        int res = 1;
        for (int i = 0; i < x; i++) {
            res *= y--;
        }
        return res;
    }

}
