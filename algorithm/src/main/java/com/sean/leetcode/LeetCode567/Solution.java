package com.sean.leetcode.LeetCode567;

/**
 * @Author xionghaiyang
 * @Date 2025-11-19 18:30
 * @Description https://leetcode.cn/problems/permutation-in-string
 * 567. 字符串的排列
 * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的 排列。
 * 如果是，返回 true ；否则，返回 false 。
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 * 1 <= s1.length, s2.length <= 10^4
 * s1 和 s2 仅包含小写字母
 */
public class Solution {

    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        int less = 0;
        for (char c : s1.toCharArray()) {
            if (cnt[c - 'a']++ == 0) {
                less++;
            }
        }
        for (int i = 0; i < m; i++) {
            if (--cnt[s2.charAt(i) - 'a'] == 0) {
                less--;
            }
            int left = i + 1 - n;
            if (left < 0) {
                continue;
            }
            if (less == 0) {
                return true;
            }
            if (cnt[s2.charAt(left) - 'a']++ == 0) {
                less++;
            }
        }
        return false;
    }

}
