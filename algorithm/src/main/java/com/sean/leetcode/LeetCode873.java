package com.sean.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode873 {

    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            map.put(arr[i], i);
        }
        int[][] dp = new int[len][len];
        int res = 0;
        for (int j = 0; j < len; j++) {
            for (int i = j - 1; i >= 0 && arr[i] * 2 - arr[j] > 0; i--) {
                int k = map.getOrDefault(arr[j] - arr[i], -1);
                if (k != -1) {
                    dp[i][j] = Math.max(dp[k][i] + 1, 3);
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

}
