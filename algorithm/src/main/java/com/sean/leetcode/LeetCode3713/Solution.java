package com.sean.leetcode.LeetCode3713;

/**
 * @Author xionghaiyang
 * @Date 2025-10-12 19:06
 * @Description https://leetcode.cn/problems/longest-balanced-substring-i
 * 3713. 最长的平衡子串 I
 * 给你一个由小写英文字母组成的字符串 s。
 * 如果一个 子串 中所有 不同 字符出现的次数都 相同 ，则称该子串为 平衡 子串。
 * 请返回 s 的 最长平衡子串 的 长度 。
 * 子串 是字符串中连续的、非空 的字符序列。
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成。
 */
public class Solution {

    public int longestBalanced(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            int max = 0, kinds = 0;
            for (int j = i; j < n; j++) {
                int c = str[j] - 'a';
                if (cnt[c] == 0) {
                    kinds++;
                }
                max = Math.max(max, ++cnt[c]);
                if (max * kinds == j - i + 1) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }

}
