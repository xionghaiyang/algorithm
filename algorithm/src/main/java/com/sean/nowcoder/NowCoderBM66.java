package com.sean.nowcoder;

public class NowCoderBM66 {

    public static String LCS(String str1, String str2) {
        if (str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0) {
            return null;
        }
        //dp[i][j]表示st1第i个到str2第j个为止的公共字串长度
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        int max = 0;
        int index = 0;
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                if (dp[i][j] > max) {
                    max = dp[i][j];
                    index = i - 1;
                }
            }
        }
        return str1.substring(index - max + 1, index + 1);
    }

}
