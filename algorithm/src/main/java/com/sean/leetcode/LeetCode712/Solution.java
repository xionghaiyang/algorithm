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
        char[] str1 = s1.toCharArray(), str2 = s2.toCharArray();
        int m = str1.length, n = str2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + str1[i - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return total - dp[m][n] * 2;
    }

}
