package com.sean.leetcode.LeetCode242;

/**
 * @Author xionghaiyang
 * @Date 2025-07-10 06:46
 * @Description https://leetcode.cn/problems/valid-anagram
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的 字母异位词。
 * 1 <= s.length, t.length <= 5 * 10^4
 * s 和 t 仅包含小写字母
 */
public class Solution {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            if (--cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

}
