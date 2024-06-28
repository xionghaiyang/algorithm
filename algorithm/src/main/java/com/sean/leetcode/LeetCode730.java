package com.sean.leetcode;

public class LeetCode730 {

    public static int countPalindromicSubsequences(String s) {
        int mod = 1000000007;
        int n = s.length();
        //dp(i,j)表示字符串区间s(i,j)的不同回文串个数
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    int low = i + 1;
                    int high = j - 1;
                    while (low <= high && s.charAt(low) != s.charAt(i)) {
                        low++;
                    }
                    while (high >= low && s.charAt(high) != s.charAt(j)) {
                        high--;
                    }
                    if (low > high) {
                        dp[i][j] = (2 + dp[i + 1][j - 1] * 2) % mod;
                    } else if (low == high) {
                        dp[i][j] = (1 + dp[i + 1][j - 1] * 2) % mod;
                    } else {
                        dp[i][j] = (dp[i + 1][j - 1] * 2 % mod - dp[low + 1][high - 1] + mod) % mod;
                    }
                } else {
                    dp[i][j] = ((dp[i + 1][j] + dp[i][j - 1]) % mod - dp[i + 1][j - 1] + mod) % mod;
                }
            }
        }
        return dp[0][n - 1];
    }

}
