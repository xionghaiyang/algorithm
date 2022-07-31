package com.sean.nowcoder;

public class NowCoderBM76 {

    public static boolean match(String str, String pattern) {
        int n1 = str.length();
        int n2 = pattern.length();
        //dp[i][j]表示str前i个字符和pattern前j个字符是否匹配
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];
        for (int i = 0; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                if (j == 0) {
                    dp[i][j] = (i == 0 ? true : false);
                } else {
                    if (pattern.charAt(j - 1) != '*') {
                        if (i >= 1 && (str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '.')) {
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    } else {
                        if (j >= 2) {
                            dp[i][j] |= dp[i][j - 2];
                        }
                        if (i >= 1 && j >= 2 && (str.charAt(i - 1) == pattern.charAt(j - 2) || pattern.charAt(j - 2) == '.')) {
                            dp[i][j] |= dp[i - 1][j];
                        }
                    }
                }
            }
        }
        return dp[n1][n2];
    }

}
