package com.sean.nowcoder;

public class NowCoderBM72 {

    public static int FindGreatestSumOfSubArray(int[] array) {
        int[] dp = new int[array.length];
        dp[0] = array[0];
        int res = dp[0];
        for (int i = 1; i < array.length; i++) {
            dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
