package com.sean.leetcode;

/**
 * @Author xionghaiyang
 * @Date 2022/7/24 11:12
 */
public class LeetCode72 {

    /**
     * https://leetcode.cn/problems/edit-distance/
     * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
     * 你可以对一个单词进行如下三种操作：
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     */

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if (m == 0 || n == 0) {
            return m + n;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1));
            }
        }
        return dp[m][n];
    }

}
