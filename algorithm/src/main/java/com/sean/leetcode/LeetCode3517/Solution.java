package com.sean.leetcode.LeetCode3517;

/**
 * @Author: xionghaiyang
 * @Date: 2026-07-28 18:28
 * @Description: https://leetcode.cn/problems/smallest-palindromic-rearrangement-i
 * 3517. 最小回文排列 I
 * 给你一个 回文 字符串 s。
 * 返回 s 的按字典序排列的 最小 回文排列。
 * 如果一个字符串从前往后和从后往前读都相同，那么这个字符串是一个 回文 字符串。
 * 排列 是字符串中所有字符的重排。
 * 如果字符串 a 按字典序小于字符串 b，则表示在第一个不同的位置，a 中的字符比 b 中的对应字符在字母表中更靠前。
 * 如果在前 min(a.length, b.length) 个字符中没有区别，则较短的字符串按字典序更小。
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成。
 * 保证 s 是回文字符串。
 */
public class Solution {

    public String smallestPalindrome(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n / 2; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                res.append((char) ('a' + i));
            }
        }
        StringBuilder t = new StringBuilder(res);
        if (n % 2 > 0) {
            res.append(s.charAt(n / 2));
        }
        res.append(t.reverse());
        return res.toString();
    }

}
