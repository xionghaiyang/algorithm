package com.sean.leetcode.LeetCode680;

/**
 * @Author xionghaiyang
 * @Date 2025-02-03 16:52
 * @Description https://leetcode.cn/problems/valid-palindrome-ii/
 * 680. 验证回文串 II
 * 给你一个字符串 s，最多 可以从中删除一个字符。
 * 请你判断 s 是否能成为回文字符串：如果能，返回 true ；否则，返回 false 。
 * 1 <= s.length <= 10^5
 * s 由小写英文字母组成
 */
public class Solution {

    public boolean validPalindrome(String s) {
        int n = s.length();
        return process(s, 0, n - 1, true);
    }

    private boolean process(String s, int left, int right, boolean flag) {
        if (left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                return process(s, left + 1, right - 1, flag);
            } else if (flag) {
                return process(s, left + 1, right, !flag) || process(s, left, right - 1, !flag);
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

}
