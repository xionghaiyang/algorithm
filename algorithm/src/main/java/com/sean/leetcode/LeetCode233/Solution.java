package com.sean.leetcode.LeetCode233;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-16 12:19
 * @Description: https://leetcode.cn/problems/number-of-digit-one/
 * 233. 数字 1 的个数
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 */
public class Solution {

    private char[] s;
    private int[][] dp;

    public int countDigitOne(int n) {
        s = Integer.toString(n).toCharArray();
        int m = s.length;
        dp = new int[m][m];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(0, 0, true);
    }

    //构造到从左往右第i位，已经出现了cnt个1,在这种情况下，继续构造最终会得到1的个数
    private int dfs(int i, int cnt1, boolean isLimit) {
        if (i == s.length) {
            return cnt1;
        }
        if (!isLimit && dp[i][cnt1] != -1) {
            return dp[i][cnt1];
        }
        int res = 0;
        //枚举要填入的数字d
        for (int d = 0, up = isLimit ? s[i] - '0' : 9; d <= up; d++) {
            res += dfs(i + 1, cnt1 + (d == 1 ? 1 : 0), isLimit && d == up);
        }
        if (!isLimit) {
            dp[i][cnt1] = res;
        }
        return res;
    }

}
