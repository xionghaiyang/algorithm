package com.sean.leetcode.LeetCode132;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-03-02 09:33
 * @Description https://leetcode.cn/problems/palindrome-partitioning-ii/
 * 132. 分割回文串 II
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串。
 * 返回符合要求的 最少分割次数 。
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 */
public class Solution {

    public int minCut(String s) {
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(f[i], true);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int j = 0; j < n; j++) {
            if (f[0][j]) {
                dp[j] = 0;
            } else {
                for (int i = 0; i < j; i++) {
                    if (f[i + 1][j]) {
                        dp[j] = Math.min(dp[j], dp[i] + 1);
                    }
                }
            }
        }
        return dp[n - 1];
    }

    private int n;
    private char[] str;
    private int[] dp;

    public int minCut1(String s) {
        n = s.length();
        str = s.toCharArray();
        dp = new int[n];
        Arrays.fill(dp, n);
        for (int i = 0; i < n; i++) {
            process(i, i);
            process(i, i + 1);
        }
        return dp[n - 1];
    }

    private void process(int left, int right) {
        while (left >= 0 && right < n && str[left] == str[right]) {
            if (left == 0) {
                dp[right] = 0;
            } else {
                dp[right] = Math.min(dp[right], dp[left - 1] + 1);
            }
            left--;
            right++;
        }
    }

}
