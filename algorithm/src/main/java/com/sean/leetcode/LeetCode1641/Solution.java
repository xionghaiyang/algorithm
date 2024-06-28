package com.sean.leetcode.LeetCode1641;

/**
 * @Auther: xionghaiyang
 * @Date: 2023-03-29 09:16
 * @Description: https://leetcode.cn/problems/count-sorted-vowel-strings/
 * 1641. 统计字典序元音字符串的数目
 * 给你一个整数 n，请返回长度为 n 、仅由元音 (a, e, i, o, u) 组成且按 字典序排列 的字符串数量。
 * 字符串 s 按 字典序排列 需要满足：对于所有有效的 i，s[i] 在字母表中的位置总是与 s[i+1] 相同或在 s[i+1] 之前。
 */
public class Solution {

    public int countVowelStrings(int n) {
        int[][] dp = new int[n + 1][5];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < 5; j++) {
                dp[i][j] = -1;
            }
        }
        return process(n, 0, 0, dp);
    }

    private int process(int n, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (i == n) {
            dp[i][j] = 1;
            return dp[i][j];
        }
        int res = 0;
        for (int k = j; k < 5; k++) {
            res += process(n, i + 1, k, dp);
        }
        dp[i][j] = res;
        return dp[i][j];
    }

}
