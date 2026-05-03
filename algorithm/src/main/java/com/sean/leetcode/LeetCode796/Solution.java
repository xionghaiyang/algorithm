package com.sean.leetcode.LeetCode796;

/**
 * @Autnor: xionghaiyang
 * @Date: 2026-05-03 20:19
 * @Description: https://leetcode.cn/problems/rotate-string
 * 796. 旋转字符串
 * 给定两个字符串, s 和 goal。
 * 如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
 * s 的 旋转操作 就是将 s 最左边的字符移动到最右边。
 * 例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。
 * 1 <= s.length, goal.length <= 100
 * s 和 goal 由小写英文字母组成
 */
public class Solution {

    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        if (s.isEmpty()) {
            return true;
        }
        return kmp(s + s, goal);
    }

    private boolean kmp(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        if (m == 0) {
            return true;
        }
        int[] lps = getLps(pattern);
        int i = 0, j = 0;
        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    return true;
                }
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return false;
    }

    //计算模式串的最长相等前后缀
    private int[] getLps(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        for (int i = 1; i < m; ) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
        return lps;
    }

}
