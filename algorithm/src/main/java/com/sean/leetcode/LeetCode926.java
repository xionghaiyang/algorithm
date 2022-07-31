package com.sean.leetcode;

public class LeetCode926 {

    public static int minFlipsMonoIncr(String s) {
        int dp0 = 0, dp1 = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int dp0New = dp0, dp1New = Math.min(dp0, dp1);
            if (c == '1') {
                dp0New++;
            } else {
                dp1New++;
            }
            dp0 = dp0New;
            dp1 = dp1New;
        }
        return Math.min(dp0, dp1);
    }

}
