package com.sean.leetcode.LeetCode5;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 10:12
 * @Description: https://leetcode.cn/problems/longest-palindromic-substring
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 */
public class Solution {

    public String longestPalindrome(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }
        int maxLen = 1, start = 0;
        //dp[i][j]表示s[i..j]是否是回文串
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        char[] str = s.toCharArray();
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n; i++) {
                int j = i + len - 1;
                if (j >= n) {
                    break;
                }
                if (str[i] != str[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && len > maxLen) {
                    maxLen = len;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    public String longestPalindrome1(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }
        int start = 0, end = 0;
        char[] str = s.toCharArray();
        for (int i = 0; i < n; i++) {
            int len1 = process(str, i, i);
            int len2 = process(str, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int process(char[] str, int left, int right) {
        while (left >= 0 && right < str.length && str[left] == str[right]) {
            left--;
            right++;
        }
        return right - left + 1;
    }

}
