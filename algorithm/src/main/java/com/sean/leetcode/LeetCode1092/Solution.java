package com.sean.leetcode.LeetCode1092;

/**
 * @Author: xionghaiyang
 * @Date: 2023-03-28 08:11
 * @Description: https://leetcode.cn/problems/shortest-common-supersequence
 * 1092. 最短公共超序列
 * 给出两个字符串 str1 和 str2，返回同时以 str1 和 str2 作为子序列的最短字符串。
 * 如果答案不止一个，则可以返回满足条件的任意一个答案。
 * （如果从字符串 T 中删除一些字符（也可能不删除，并且选出的这些字符可以位于 T 中的 任意位置），可以得到字符串 S，那么 S 就是 T 的子序列）
 * 1 <= str1.length, str2.length <= 1000
 * str1 和 str2 都由小写英文字母组成。
 */
public class Solution {

    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            dp[i][n] = m - i;
        }
        for (int j = 0; j < n; j++) {
            dp[m][j] = n - j;
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + 1;
                }
            }
        }
        StringBuilder res = new StringBuilder();
        int t1 = 0, t2 = 0;
        while (t1 < m && t2 < n) {
            if (str1.charAt(t1) == str2.charAt(t2)) {
                res.append(str1.charAt(t1));
                t1++;
                t2++;
            } else if (dp[t1 + 1][t2] == dp[t1][t2] - 1) {
                res.append(str1.charAt(t1));
                t1++;
            } else if (dp[t1][t2 + 1] == dp[t1][t2] - 1) {
                res.append(str2.charAt(t2));
                t2++;
            }
        }
        if (t1 < m) {
            res.append(str1.substring(t1));
        } else if (t2 < n) {
            res.append(str2.substring(t2));
        }
        return res.toString();
    }

}
