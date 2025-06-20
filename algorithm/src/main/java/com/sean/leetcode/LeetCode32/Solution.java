package com.sean.leetcode.LeetCode32;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-09-27 20:02
 * @Description: https://leetcode.cn/problems/longest-valid-parentheses
 * 32. 最长有效括号
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 0 <= s.length <= 3 * 10^4
 * s[i] 为 '(' 或 ')'
 */
public class Solution {

    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int res = 0;
        //以i结尾的最长有效括号子串的长度
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
