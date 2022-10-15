package com.sean.leetcode.LeetCode940;

import java.util.Arrays;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-10-14 08:22
 * @Description: https://leetcode.cn/problems/distinct-subsequences-ii/
 * 940. 不同的子序列 II
 * 给定一个字符串 s，计算 s 的 不同非空子序列 的个数。
 * 因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
 * 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
 * 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。
 */
public class Solution {

    public int distinctSubseqII(String s) {
        int mod = 1000000007;
        int[] last = new int[26];
        Arrays.fill(last, -1);
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                if (last[j] != -1) {
                    dp[i] = (dp[i] + dp[last[j]]) % mod;
                }
            }
            last[s.charAt(i) - 'a'] = i;
        }
        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (last[i] != -1) {
                res = (res + dp[last[i]]) % mod;
            }
        }
        return res;
    }

}
