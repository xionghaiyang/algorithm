package com.sean.leetcode.LeetCode2414;

/**
 * @Author xionghaiyang
 * @Date 2024-09-19 09:34
 * @Description https://leetcode.cn/problems/length-of-the-longest-alphabetical-continuous-substring/
 * 2414. 最长的字母序连续子字符串的长度
 * 字母序连续字符串 是由字母表中连续字母组成的字符串。
 * 换句话说，字符串 "abcdefghijklmnopqrstuvwxyz" 的任意子字符串都是 字母序连续字符串 。
 * 例如，"abc" 是一个字母序连续字符串，而 "acb" 和 "za" 不是。
 * 给你一个仅由小写英文字母组成的字符串 s ，返回其 最长 的 字母序连续子字符串 的长度。
 */
public class Solution {

    public int longestContinuousSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int res = 1;
        int cur = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == s.charAt(i - 1) + 1) {
                cur++;
            } else {
                cur = 1;
            }
            res = Math.max(res, cur);
        }
        return res;
    }

}
