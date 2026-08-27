package com.sean.leetcode.LeetCode3734;

/**
 * @Author: xionghaiyang
 * @Date: 2026-08-28 05:36
 * @Description: https://leetcode.cn/problems/lexicographically-smallest-palindromic-permutation-greater-than-target
 * 3734. 大于目标字符串的最小字典序回文排列
 * 给你两个长度均为 n 的字符串 s 和目标字符串 target，它们都由小写英文字母组成。
 * 返回 字典序 最小的字符串 ，该字符串 既 是 s 的一个 回文 排列 ，又是字典序 严格 大于 target 的。
 * 如果不存在这样的排列，则返回一个空字符串。
 * 如果字符串 a 和字符串 b 长度相同，在它们首次出现不同的位置上，字符串 a 处的字母在字母表中的顺序晚于字符串 b 处的对应字母，则字符串 a 在 字典序上严格大于 字符串 b。
 * 排列 是指对字符串中所有字符的重新排列。
 * 如果一个字符串从前向后读和从后向前读都一样，则该字符串是 回文 的。
 * 1 <= n == s.length == target.length <= 300
 * s 和 target 仅由小写英文字母组成。
 */
public class Solution {

    public String lexPalindromicPermutation(String s, String target) {
        int[] left = new int[26];
        for (char c : s.toCharArray()) {
            left[c - 'a']++;
        }
        if (!valid(left)) {
            return "";
        }
        String midCh = "";
        for (int i = 0; i < 26; i++) {
            int c = left[i];
            if (c % 2 == 0) {
                continue;
            }
            if (!midCh.isEmpty()) {
                return "";
            }
            midCh = "" + (char) ('a' + i);
            left[i]--;
        }
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            left[target.charAt(i) - 'a'] -= 2;
        }
        if (valid(left)) {
            String leftS = target.substring(0, n / 2);
            String rightS = midCh + new StringBuilder(leftS).reverse();
            if (rightS.compareTo(target.substring(n / 2)) > 0) {
                return leftS + rightS;
            }
        }
        for (int i = n / 2 - 1; i >= 0; i--) {
            int b = target.charAt(i) - 'a';
            left[b] += 2;
            if (!valid(left)) {
                continue;
            }
            for (int j = b + 1; j < 26; j++) {
                if (left[j] == 0) {
                    continue;
                }
                left[j] -= 2;
                StringBuilder res = new StringBuilder(target.substring(0, i + 1));
                res.setCharAt(i, (char) ('a' + j));
                for (int k = 0; k < 26; k++) {
                    repeat(res, (char) ('a' + k), left[k] / 2);
                }
                StringBuilder rightS = new StringBuilder(res).reverse();
                return res.append(midCh).append(rightS).toString();
            }
        }
        return "";
    }

    private boolean valid(int[] left) {
        for (int c : left) {
            if (c < 0) {
                return false;
            }
        }
        return true;
    }

    private void repeat(StringBuilder str, char c, int cnt) {
        for (int i = 0; i < cnt; i++) {
            str.append(c);
        }
    }

}
