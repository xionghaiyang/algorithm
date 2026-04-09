package com.sean.leetcode.LeetCode409;

/**
 * @Author xionghaiyang
 * @Date 2026-04-09 09:43
 * @Description https://leetcode.cn/problems/longest-palindrome
 * 409. 最长回文串
 * 给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的 回文串 的长度。
 * 在构造过程中，请注意 区分大小写 。
 * 比如 "Aa" 不能当做一个回文字符串。
 * 1 <= s.length <= 2000
 * s 只由小写 和/或 大写英文字母组成
 */
public class Solution {

    public int longestPalindrome(String s) {
        int res = 0;
        int[] cnt = new int[128];
        int rest = 0;
        for (char c : s.toCharArray()) {
            if (cnt[c] == 1) {
                res += 2;
                cnt[c]--;
                rest--;
            } else {
                cnt[c]++;
                rest++;
            }
        }
        return res + (rest > 0 ? 1 : 0);
    }

}
