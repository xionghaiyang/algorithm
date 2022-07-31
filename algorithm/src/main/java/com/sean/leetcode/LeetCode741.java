package com.sean.leetcode;

import java.util.Arrays;

public class LeetCode741 {

    public int cherryPickup(int[][] grid) {
        int len = grid.length;
        int[][][] dp = new int[len * 2 - 1][len][len];
        for (int i = 0; i < 2 * len - 1; i++) {
            for (int j = 0; j < len; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        dp[0][0][0] = grid[0][0];
        for (int k = 1; k < len * 2 - 1; k++) {
            for (int x1 = Math.max(k - len + 1, 0); x1 <= Math.min(k, len - 1); x1++) {
                int y1 = k - x1;
                if (grid[x1][y1] == -1) {
                    continue;
                }
                for (int x2 = x1; x2 <= Math.min(k, len - 1); x2++) {
                    int y2 = k - x2;
                    if (grid[x2][y2] == -1) {
                        continue;
                    }
                    int res = dp[k - 1][x1][x2];
                    if (x1 > 0) {
                        res = Math.max(res, dp[k - 1][x1 - 1][x2]);
                    }
                    if (x2 > 0) {
                        res = Math.max(res, dp[k - 1][x1][x2 - 1]);
                    }
                    if (x1 > 0 && x2 > 0) {
                        res = Math.max(res, dp[k - 1][x1 - 1][x2 - 1]);
                    }
                    res += grid[x1][y1];
                    if (x2 != x1) {
                        res += grid[x2][y2];
                    }
                    dp[k][x1][x2] = res;
                }

            }
        }
        return Math.max(dp[2 * len - 2][len - 1][len - 1], 0);
    }

}
