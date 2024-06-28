package com.sean.leetcode;

/**
 * 交错字符串
 * https://leetcode-cn.com/problems/interleaving-string/
 */
public class LeetCode97 {

    public static boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), s = s3.length();
        if (m + n != s) {
            return false;
        }
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                int t = i + j - 1;
                if (i > 0) {
                    dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(t));
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(t));
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(isInterleave("", "", ""));
    }

}
