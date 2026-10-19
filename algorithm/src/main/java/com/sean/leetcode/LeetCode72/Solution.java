package com.sean.leetcode.LeetCode72;

/**
 * @Author: xionghaiyang
 * @Date: 2022-12-29 13:11
 * @Description: https://leetcode.cn/problems/edit-distance
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 */
public class Solution {

    public int minDistance(String word1, String word2) {
        char[] str1 = word1.toCharArray(), str2 = word2.toCharArray();
        int m = str1.length, n = str2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
            for (int j = 1; j <= n; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        return dp[m][n];
    }

}
