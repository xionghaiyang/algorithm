package com.sean.leetcode.LeetCode132;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-03-02 09:33
 * @Description https://leetcode.cn/problems/palindrome-partitioning-ii
 * 132. 分割回文串 II
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串。
 * 返回符合要求的 最少分割次数 。
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 */
public class Solution {

    public int minCut(String s) {
        char[] str = s.toCharArray();
        int n = s.length();
        //f[i][j]:s[i..j]是否为回文串
        boolean[][] f = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(f[i], true);
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = str[i] == str[j] && f[i + 1][j - 1];
            }
        }
        int[] dp = new int[n];
        for (int j = 0; j < n; j++) {
            if (f[0][j]) {
                continue;
            }
            int res = Integer.MAX_VALUE;
            for (int i = 1; i <= j; i++) {
                if (f[i][j]) {
                    res = Math.min(res, dp[i - 1] + 1);
                }
            }
            dp[j] = res;
        }
        return dp[n - 1];
    }

}
