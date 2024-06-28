package com.sean.leetcode.LeetCode5;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-29 10:12
 * @Description: https://leetcode.cn/problems/longest-palindromic-substring/?envType=study-plan&id=dong-tai-gui-hua-ru-men&plan=dynamic-programming&plan_progress=yn1qp15
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 */
public class Solution {

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int n = s.length();
        //dp[i][j]表示s[i..j]是否是回文串
        boolean[][] dp = new boolean[n][n];
        //初始化：所有长度为1的子串都是回文串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        char[] chars = s.toCharArray();
        int maxLen = 1;
        int begin = 0;
        //枚举子串长度
        for (int L = 2; L <= n; L++) {
            //枚举左边界
            for (int i = 0; i < n; i++) {
                //由L和i可以确定右边界,即j-i+1=L得
                int j = L + i - 1;
                //如果右边界越界，就可以退出当前循环
                if (j >= n) {
                    break;
                }
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                //只要dp[i][j]==true成立，就表示字串s[i..j]是回文，此时记录回文长度和起始位置
                if (dp[i][j] && L > maxLen) {
                    maxLen = L;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

}
