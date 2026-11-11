package com.sean.leetcode.LeetCode1278;

import java.util.Arrays;

/**
 * @Author xionghaiyang
 * @Date 2025-03-03 08:08
 * @Description https://leetcode.cn/problems/palindrome-partitioning-iii
 * 1278. 分割回文串 III
 * 给你一个由小写字母组成的字符串 s，和一个整数 k。
 * 请你按下面的要求分割字符串：
 * 首先，你可以将 s 中的部分字符修改为其他的小写英文字母。
 * 接着，你需要把 s 分割成 k 个非空且不相交的子串，并且每个子串都是回文串。
 * 请返回以这种方式分割字符串所需修改的最少字符数。
 * 1 <= k <= s.length <= 100
 * s 中只含有小写英文字母。
 */
public class Solution {

    public int palindromePartition(String s, int k) {
        int n = s.length();
        //memo[i][j]:将s[i,j]修改为回文串的最小修改次数
        int[][] memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        //dp[i][j]:将s[0,j]分割成i+1个回文串的最小修改次数
        int[][] dp = new int[k][n];
        for (int i = 0; i < k; i++) {
            Arrays.fill(dp[i], -1);
        }
        return dfs(s.toCharArray(), memo, dp, k - 1, n - 1);
    }

    private int dfs(char[] str, int[][] memo, int[][] dp, int i, int right) {
        if (i == 0) {
            return process(str, memo, 0, right);
        }
        if (dp[i][right] != -1) {
            return dp[i][right];
        }
        int res = Integer.MAX_VALUE;
        for (int left = i; left <= right; left++) {
            res = Math.min(res, dfs(str, memo, dp, i - 1, left - 1) + process(str, memo, left, right));
        }
        return dp[i][right] = res;
    }

    private int process(char[] str, int[][] memo, int left, int right) {
        if (left > right) {
            return 0;
        }
        if (memo[left][right] != -1) {
            return memo[left][right];
        }
        return memo[left][right] = process(str, memo, left + 1, right - 1) + (str[left] != str[right] ? 1 : 0);
    }

}
