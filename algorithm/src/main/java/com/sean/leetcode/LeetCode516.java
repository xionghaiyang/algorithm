package com.sean.leetcode;

/**
 * 最长回文子序列
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 */
public class LeetCode516 {

    public static int longestPalindromeSubseq(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];
        for (int i = length - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][length - 1];
    }

}
