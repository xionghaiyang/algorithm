package com.sean.leetcode;

/**
 * 鸡蛋掉落
 * https://leetcode-cn.com/problems/super-egg-drop/
 */
public class LeetCode887 {

    public int superEggDrop(int k, int n) {
        if (n == 1) {
            return 1;
        }
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= k; i++) {
            f[1][i] = 1;
        }
        int res = -1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                f[i][j] = 1 + f[i - 1][j - 1] + f[i - 1][j];
            }
            if (f[i][k] >= n) {
                res = i;
                break;
            }
        }
        return res;
    }

}
