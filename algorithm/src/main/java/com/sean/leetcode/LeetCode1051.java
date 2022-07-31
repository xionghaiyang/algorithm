package com.sean.leetcode;

public class LeetCode1051 {

    public static int heightChecker(int[] heights) {
        int max = Integer.MIN_VALUE;
        for (int height : heights) {
            if (height > max) {
                max = height;
            }
        }
        int[] cnt = new int[max + 1];
        for (int height : heights) {
            cnt[height]++;
        }
        int res = 0, index = 0;
        for (int i = 1; i <= max; i++) {
            for (int j = 1; j <= cnt[i]; j++) {
                if (heights[index] != i) {
                    res++;
                }
                index++;
            }
        }
        return res;
    }

}
