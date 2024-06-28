package com.sean.leetcode;

public class LeetCode868 {

    public static int binaryGap(int n) {
        int ans = 0, last = -1;
        for (int i = 0; n != 0; i++) {
            if ((n & 1) == 1) {
                if (last != -1) {
                    ans = Math.max(ans, i - last);
                }
                last = i;
            }
            n = n >> 1;
        }
        return ans;
    }

}
