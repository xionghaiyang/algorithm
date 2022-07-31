package com.sean.nowcoder;

public class NowCoderBM78 {

    public static int rob(int[] nums) {
        int n = nums.length;
        //dp[i]表示长度为i的数组，最多能偷取多少钱
        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        }
        return dp[n];
    }

}
