package com.sean.leetcode;

/**
 * 除数博弈
 * https://leetcode-cn.com/problems/divisor-game/
 */
public class LeetCode1025 {

    public static boolean divisorGame(int n) {
        if (n == 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        boolean[] dp = new boolean[n + 1];
        dp[1] = false;
        dp[2] = true;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if ((i % j == 0) && !dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public static boolean divisorGame1(int n) {
        return n % 2 == 0;
    }

}
