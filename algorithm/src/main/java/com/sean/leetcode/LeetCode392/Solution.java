package com.sean.leetcode.LeetCode392;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-09 21:21
 * @Description: https://leetcode.cn/problems/is-subsequence
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。
 * 在这种情况下，你会怎样改变代码？
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * 两个字符串都只由小写字符组成。
 */
public class Solution {

    public boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        for (int i = 0, j = 0; i < m; i++, j++) {
            char c = s.charAt(i);
            while (j < n && c != t.charAt(j)) {
                j++;
            }
            if (j == n) {
                return false;
            }
        }
        return true;
    }

    public boolean isSubsequence1(String s, String t) {
        int m = s.length();
        int n = t.length();
        //dp[i][j]表示字符串t中从位置i开始往后字符j第一次出现的位置
        //dp[i][j] = n 表示字符串t中从位置i开始往后不存在字符j
        int[][] dp = new int[n + 1][26];
        for (int j = 0; j < 26; j++) {
            dp[n][j] = n;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) - 'a' == j) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        for (int i = 0, index = 0; i < m; i++) {
            if (dp[index][s.charAt(i) - 'a'] == n) {
                return false;
            }
            index = dp[index][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }

}
