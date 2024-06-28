package com.sean.nowcoder;

import java.util.Arrays;

public class NowCoderBM71 {

    public static int LIS(int[] arr) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    res = Math.max(res, dp[i]);
                }
            }
        }
        return res;
    }

}
