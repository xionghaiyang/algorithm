package com.sean.leetcode.LeetCode316;

/**
 * @Author xionghaiyang
 * @Date 2025-08-22 19:05
 * @Description https://leetcode.cn/problems/remove-duplicate-letters
 * 316. 去除重复字母
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 1 <= s.length <= 10^4
 * s 由小写英文字母组成
 */
public class Solution {

    public String removeDuplicateLetters(String s) {
        char[] str = s.toCharArray();
        int[] cnt = new int[26];
        for (char c : str) {
            cnt[c - 'a']++;
        }
        StringBuilder res = new StringBuilder(26);
        boolean[] inRes = new boolean[26];
        for (char c : str) {
            cnt[c - 'a']--;
            if (inRes[c - 'a']) {
                continue;
            }
            while (res.length() > 0 && c < res.charAt(res.length() - 1) && cnt[res.charAt(res.length() - 1) - 'a'] > 0) {
                inRes[res.charAt(res.length() - 1) - 'a'] = false;
                res.deleteCharAt(res.length() - 1);
            }
            inRes[c - 'a'] = true;
            res.append(c);
        }
        return res.toString();
    }

}
