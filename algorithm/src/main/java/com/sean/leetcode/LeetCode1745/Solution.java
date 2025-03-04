package com.sean.leetcode.LeetCode1745;

/**
 * @Author xionghaiyang
 * @Date 2025-03-04 08:24
 * @Description https://leetcode.cn/problems/palindrome-partitioning-iv/
 * 1745. 分割回文串 IV
 * 给你一个字符串 s ，如果可以将它分割成三个 非空 回文子字符串，那么返回 true ，否则返回 false 。
 * 当一个字符串正着读和反着读是一模一样的，就称其为 回文字符串 。
 * 3 <= s.length <= 2000
 * s​​​​​​ 只包含小写英文字母。
 */
public class Solution {

    public boolean checkPartitioning(String s) {
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        for (int length = 1; length < n; length++) {
            for (int start = 0; start <= n - length; start++) {
                int end = start + length - 1;
                if (length == 1) {
                    f[start][end] = true;
                } else if (length == 2) {
                    f[start][end] = (s.charAt(start) == s.charAt(end));
                } else {
                    f[start][end] = (s.charAt(start) == s.charAt(end)) && f[start + 1][end - 1];
                }
            }
        }
        for (int start = 1; start < n - 1; start++) {
            if (!f[0][start - 1]) {
                continue;
            }
            for (int end = start; end < n - 1; end++) {
                if (f[start][end] && f[end + 1][n - 1]) {
                    return true;
                }
            }
        }
        return false;
    }

}
