package com.sean.leetcode.LeetCodeInterview1706;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2024-01-16 12:31
 * @Description: https://leetcode.cn/problems/number-of-2s-in-range-lcci/
 * 面试题 17.06. 2出现的次数
 * 编写一个方法，计算从 0 到 n (含 n) 中数字 2 出现的次数。
 */
public class Solution {

    private char[] s;
    private int[][] dp;

    public int numberOf2sInRange(int n) {
        s = Integer.toString(n).toCharArray();
        int m = s.length;
        dp = new int[m][m];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(0, 0, true);
    }

    private int process(int i, int cnt2, boolean isLimit) {
        if (i == s.length) {
            return cnt2;
        }
        if (!isLimit && dp[i][cnt2] != -1) {
            return dp[i][cnt2];
        }
        int res = 0;
        //枚举要填入的数字d
        for (int d = 0, up = isLimit ? s[i] - '0' : 9; d <= up; d++) {
            res += process(i + 1, cnt2 + (d == 2 ? 1 : 0), isLimit && d == up);
        }
        if (!isLimit) {
            dp[i][cnt2] = res;
        }
        return res;
    }

}
