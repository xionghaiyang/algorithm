package com.sean.leetcode.LeetCode131;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author xionghaiyang
 * @Date 2025-03-01 08:37
 * @Description https://leetcode.cn/problems/palindrome-partitioning/
 * 131. 分割回文串
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。
 * 返回 s 所有可能的分割方案。
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 */
public class Solution {

    private int n;
    private boolean[][] dp;
    private List<List<String>> res = new ArrayList<>();
    private List<String> temp = new ArrayList<>();

    public List<List<String>> partition(String s) {
        n = s.length();
        dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], true);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }
        process(s, 0);
        return res;
    }

    private void process(String s, int i) {
        if (i == n) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int j = i; j < n; j++) {
            if (dp[i][j]) {
                temp.add(s.substring(i, j + 1));
                process(s, j + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

}
