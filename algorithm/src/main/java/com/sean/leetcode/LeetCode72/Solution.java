package com.sean.leetcode.LeetCode72;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
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

    private char[] str1;
    private char[] str2;
    private int[][] dp;

    public int minDistance(String word1, String word2) {
        str1 = word1.toCharArray();
        str2 = word2.toCharArray();
        int m = str1.length;
        int n = str2.length;
        dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(m - 1, n - 1);
    }

    private int process(int i, int j) {
        if (i < 0) {
            return j + 1;
        }
        if (j < 0) {
            return i + 1;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (str1[i] == str2[j]) {
            dp[i][j] = process(i - 1, j - 1);
        } else {
            dp[i][j] = Math.min(Math.min(process(i - 1, j), process(i, j - 1)), process(i - 1, j - 1)) + 1;
        }
        return dp[i][j];
    }

}
