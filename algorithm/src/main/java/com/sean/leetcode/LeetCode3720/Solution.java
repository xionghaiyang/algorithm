package com.sean.leetcode.LeetCode3720;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-27 06:03
 * @Description: https://leetcode.cn/problems/lexicographically-smallest-permutation-greater-than-target
 * 3720. 大于目标字符串的最小字典序排列
 * 给你两个长度均为 n 且仅由小写英文字母组成的字符串 s 和 target。
 * 返回 s 的 字典序最小的排列，要求该排列 严格 大于 target。
 * 如果 s 不存在任何字典序严格大于 target 的排列，则返回一个空字符串。
 * 如果两个长度相同的字符串 a 和 b 在它们首次出现不同字符的位置上，字符串 a 对应的字母在字母表中出现在 b 对应字母的 后面 ，则字符串 a 字典序严格大于 字符串 b。
 * 排列 是字符串中所有字符的一种重新排列。
 * 1 <= s.length == target.length <= 300
 * s 和 target 仅由小写英文字母组成。
 */
public class Solution {

    public String lexGreaterPermutation(String s, String target) {
        char[] t = target.toCharArray();
        int n = t.length;
        int[] left = new int[26];
        for (int i = 0; i < n; i++) {
            left[s.charAt(i) - 'a']++;
            left[t[i] - 'a']--;
        }
        next:
        for (int i = n - 1; i >= 0; i--) {
            int b = t[i] - 'a';
            left[b]++;
            for (int c : left) {
                if (c < 0) {
                    continue next;
                }
            }
            for (int j = b + 1; j < 26; j++) {
                if (left[j] == 0) {
                    continue;
                }
                left[j]--;
                StringBuilder res = new StringBuilder(target.substring(0, i + 1));
                res.setCharAt(i, (char) ('a' + j));
                for (int k = 0; k < 26; k++) {
                    repeat(res, (char) ('a' + k), left[k]);
                }
                return res.toString();
            }
        }
        return "";
    }

    private void repeat(StringBuilder str, char c, int cnt) {
        for (int i = 0; i < cnt; i++) {
            str.append(c);
        }
    }

}
