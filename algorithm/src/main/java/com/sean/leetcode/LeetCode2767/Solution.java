package com.sean.leetcode.LeetCode2767;

import java.util.*;

/**
 * @Author: xionghaiyang
 * @Date: 2026-11-04 17:37
 * @Description: https://leetcode.cn/problems/partition-string-into-minimum-beautiful-substrings
 * 2767. 将字符串分割为最少的美丽子字符串
 * 给你一个二进制字符串 s ，你需要将字符串分割成一个或者多个 子字符串  ，使每个子字符串都是 美丽 的。
 * 如果一个字符串满足以下条件，我们称它是 美丽 的：
 * 它不包含前导 0 。
 * 它是 5 的幂的 二进制 表示。
 * 请你返回分割后的子字符串的 最少 数目。
 * 如果无法将字符串 s 分割成美丽子字符串，请你返回 -1 。
 * 子字符串是一个字符串中一段连续的字符序列。
 * 1 <= s.length <= 15
 * s[i] 要么是 '0' 要么是 '1' 。
 */
public class Solution {

    private String[] strs = {"1", "101", "11001", "1111101", "1001110001", "110000110101", "11110100001001"};

    public int minimumBeautifulSubstrings(String s) {
        int n = s.length();
        //dp[i]表示长度为i的前缀字符串中，可被分割出来的子串的最少数量
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (String str : strs) {
                int len = str.length();
                if (i < len || !str.equals(s.substring(i - len, i)) || dp[i - len] == -1) {
                    continue;
                }
                if (dp[i] == -1) {
                    dp[i] = dp[i - len] + 1;
                } else {
                    dp[i] = Math.min(dp[i], dp[i - len] + 1);
                }
            }
        }
        return dp[n];
    }

}
