package com.sean.leetcode.LeetCode712;

/**
 * @Author xionghaiyang
 * @Date 2026-01-10 08:59
 * @Description https://leetcode.cn/problems/minimum-ascii-delete-sum-for-two-strings
 * 712. 两个字符串的最小ASCII删除和
 * 给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。
 * 0 <= s1.length, s2.length <= 1000
 * s1 和 s2 由小写英文字母组成
 */
public class Solution {

    public int minimumDeleteSum(String s1, String s2) {
        int total = s1.chars().sum() + s2.chars().sum();
        char[] s = s1.toCharArray();
        char[] t = s2.toCharArray();
        int n = s.length, m = t.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (s[i] == t[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + s[i];
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return total - dp[n][m] * 2;
    }

    public int minimumDeleteSum1(String s1, String s2) {
        int total = s1.chars().sum() + s2.chars().sum();
        char[] s = s1.toCharArray();
        char[] t = s2.toCharArray();
        int m = t.length;
        int[] dp = new int[m + 1];
        for (char x : s) {
            int pre = 0;
            for (int j = 0; j < m; j++) {
                int tmp = dp[j + 1];
                if (x == t[j]) {
                    dp[j + 1] = pre + x;
                } else {
                    dp[j + 1] = Math.max(dp[j + 1], dp[j]);
                }
                pre = tmp;
            }
        }
        return total - dp[m] * 2;
    }

}
