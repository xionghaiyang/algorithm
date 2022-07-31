package com.sean.nowcoder;

public class NowCoderBM65 {

    private static String x = "";
    private static String y = "";

    public static String ans(int i, int j, int[][] b) {
        String res = "";
        if (i == 0 || j == 0) {
            return res;
        }
        if (b[i][j] == 1) {
            res += ans(i - 1, j - 1, b);
            res += x.charAt(i - 1);
        } else if (b[i][j] == 2) {
            res += ans(i - 1, j, b);
        } else if (b[i][j] == 3) {
            res += ans(i, j - 1, b);
        }
        return res;
    }

    public static String LCS(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return "-1";
        }
        int len1 = s1.length();
        int len2 = s2.length();
        x = s1;
        y = s2;
        //dp[i][j]表示第一个字符串到第i位，第二个字符串到第j位位置的最长公共子序列长度
        int[][] dp = new int[len1 + 1][len2 + 1];
        //动态规划数组相加的方向
        int[][] b = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    b[i][j] = 1;
                } else {
                    if (dp[i - 1][j] > dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                        b[i][j] = 2;
                    } else {
                        dp[i][j] = dp[i][j - 1];
                        b[i][j] = 3;
                    }
                }
            }
        }
        String res = ans(len1, len2, b);
        if (res.isEmpty()) {
            return "-1";
        } else {
            return res;
        }
    }

}
