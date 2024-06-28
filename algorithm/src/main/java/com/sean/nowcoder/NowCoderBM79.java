package com.sean.nowcoder;

import java.util.Arrays;

public class NowCoderBM79 {

    public static int rob(int[] nums) {
        //dp[i]表示长度为i的数组，最多能偷取多少钱
        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        }
        int res = dp[nums.length - 1];
        Arrays.fill(dp, 0);
        dp[1] = 0;
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        }
        res = Math.max(res, dp[nums.length]);
        return res;
    }

}
