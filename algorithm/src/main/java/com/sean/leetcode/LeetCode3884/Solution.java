package com.sean.leetcode.LeetCode3884;

/**
 * @Author xionghaiyang
 * @Date 2026-03-31 08:01
 * @Description https://leetcode.cn/problems/first-matching-character-from-both-ends
 * 3884. 双端字符匹配
 * 给你一个长度为 n 的字符串 s，其中只包含小写英文字母。
 * 返回最小的下标 i，使得 s[i] == s[n - i - 1]。
 * 如果不存在这样的下标，返回 -1。
 * 1 <= n == s.length <= 100
 * s 仅包含小写英文字母。
 */
public class Solution {

    public int firstMatchingIndex(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == s.charAt(n - i - 1)) {
                return i;
            }
        }
        return -1;
    }

}
