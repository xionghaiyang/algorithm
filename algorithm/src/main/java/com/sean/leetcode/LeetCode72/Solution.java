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

    public int minDistance(String word1, String word2) {
        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        int m = str1.length, n = str2.length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return process(str1, str2, m - 1, n - 1, dp);
    }

    private int process(char[] str1, char[] str2, int i, int j, int[][] dp) {
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
            dp[i][j] = process(str1, str2, i - 1, j - 1, dp);
        } else {
            dp[i][j] = Math.min(Math.min(process(str1, str2, i - 1, j, dp),
                    process(str1, str2, i, j - 1, dp)),
                    process(str1, str2, i - 1, j - 1, dp)) + 1;
        }
        return dp[i][j];
    }

}
