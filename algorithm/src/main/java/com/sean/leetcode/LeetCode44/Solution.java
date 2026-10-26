package com.sean.leetcode.LeetCode44;

/**
 * @Author xionghaiyang
 * @Date 2025-07-31 19:19
 * @Description https://leetcode.cn/problems/wildcard-matching
 * 44. 通配符匹配
 * 给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符序列（包括空字符序列）。
 * 判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
 * 0 <= s.length, p.length <= 2000
 * s 仅由小写英文字母组成
 * p 仅由小写英文字母、'?' 或 '*' 组成
 */
public class Solution {

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        int[][] memo = new int[m + 1][n + 1];
        return process(s.toCharArray(), p.toCharArray(), memo, 0, 0);
    }

    private boolean process(char[] s, char[] p, int[][] memo, int i, int j) {
        if (memo[i][j] != 0) {
            return memo[i][j] == 1;
        }
        int m = s.length, n = p.length;
        if (i == m) {
            if (j == n) {
                memo[i][j] = 1;
            } else {
                memo[i][j] = p[j] == '*' && process(s, p, memo, i, j + 1) ? 1 : -1;
            }
            return memo[i][j] == 1;
        }
        if (j == n) {
            memo[i][j] = -1;
            return memo[i][j] == 1;
        }
        if (p[j] == '*') {
            if (j == n - 1) {
                memo[i][j] = 1;
            } else {
                memo[i][j] = process(s, p, memo, i, j + 1) || process(s, p, memo, i + 1, j) || process(s, p, memo, i + 1, j + 1) ? 1 : -1;
            }
        } else if (p[j] == '?') {
            memo[i][j] = process(s, p, memo, i + 1, j + 1) ? 1 : -1;
        } else {
            memo[i][j] = s[i] == p[j] && process(s, p, memo, i + 1, j + 1) ? 1 : -1;
        }
        return memo[i][j] == 1;
    }

}
