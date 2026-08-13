package com.sean.leetcode.LeetCode3090;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-14 05:43
 * @Description: https://leetcode.cn/problems/maximum-length-substring-with-two-occurrences
 * 3090. 每个字符最多出现两次的最长子字符串
 * 给你一个字符串 s ，请找出满足每个字符最多出现两次的最长子字符串，并返回该子字符串的 最大 长度。
 * 2 <= s.length <= 100
 * s 仅由小写英文字母组成。
 */
public class Solution {

    public int maximumLengthSubstring(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        int res = 0;
        for (int left = 0, right = -1; left < n; left++) {
            while (right + 1 < n && cnt[s.charAt(right + 1) - 'a'] < 2) {
                cnt[s.charAt(++right) - 'a']++;
            }
            res = Math.max(res, right - left + 1);
            cnt[s.charAt(left) - 'a']--;
        }
        return res;
    }

}
