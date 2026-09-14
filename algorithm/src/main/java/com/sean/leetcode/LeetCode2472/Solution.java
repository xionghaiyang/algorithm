package com.sean.leetcode.LeetCode2472;

/**
 * @Author: xionghaiyang
 * @Date: 2026-09-15 06:37
 * @Description: https://leetcode.cn/problems/maximum-number-of-non-overlapping-palindrome-substrings
 * 2472. 不重叠回文子字符串的最大数目
 * 给你一个字符串 s 和一个 正 整数 k 。
 * 从字符串 s 中选出一组满足下述条件且 不重叠 的子字符串：
 * 每个子字符串的长度 至少 为 k 。
 * 每个子字符串是一个 回文串 。
 * 返回最优方案中能选择的子字符串的 最大 数目。
 * 子字符串 是字符串中一个连续的字符序列。
 * 1 <= k <= s.length <= 2000
 * s 仅由小写英文字母组成
 */
public class Solution {

    public int maxPalindromes(String s, int k) {
        char[] str = s.toCharArray();
        int n = s.length();
        //dp[i]表示从str[0,i-1]中选出的回文子串的最大数目
        int[] dp = new int[n + 1];
        for (int i = k; i <= n; i++) {
            dp[i] = dp[i - 1];
            if (isPalindrome(str, i - k, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k] + 1);
            }
            if (i > k && isPalindrome(str, i - k - 1, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k - 1] + 1);
            }
        }
        return dp[n];
    }

    private boolean isPalindrome(char[] str, int left, int right) {
        while (left < right) {
            if (str[left++] != str[right--]) {
                return false;
            }
        }
        return true;
    }

}
