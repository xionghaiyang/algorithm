package com.sean.nowcoder;

import java.util.Arrays;

public class NowCoderBM70 {

    public static int minMoney(int[] arr, int aim) {
        if (aim < 1) {
            return 0;
        }
        //dp[i]表示凑齐i元最少需要多少货币数
        int[] dp = new int[aim + 1];
        Arrays.fill(dp, aim + 1);
        dp[0] = 0;
        for (int i = 1; i <= aim; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - arr[j]] + 1);
                }
            }
        }
        return dp[aim] > aim ? -1 : dp[aim];
    }

}
