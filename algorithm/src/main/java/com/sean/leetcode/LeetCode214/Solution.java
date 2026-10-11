package com.sean.leetcode.LeetCode214;

/**
 * @Author: xionghaiyang
 * @Date: 2026-10-11 18:45
 * @Description: https://leetcode.cn/problems/shortest-palindrome
 * 214. 最短回文串
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。
 * 找到并返回可以用这种方式转换的最短回文串。
 * 0 <= s.length <= 5 * 10^4
 * s 仅由小写英文字母组成
 */
public class Solution {

    public String shortestPalindrome(String s) {
        String str = s + "#" + new StringBuilder(s).reverse().toString();
        int[] next = getNext(str);
        return new StringBuilder(s.substring(next[str.length() - 1])).reverse().toString() + s;
    }

    private int[] getNext(String s) {
        int n = s.length();
        int[] next = new int[n];
        for (int i = 1; i < n; i++) {
            int j = next[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
