package com.sean.leetcode.LeetCode557;

/**
 * @Author xionghaiyang
 * @Date 2026-04-10 11:02
 * @Description https://leetcode.cn/problems/reverse-words-in-a-string-iii
 * 557. 反转字符串中的单词 III
 * 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 1 <= s.length <= 5 * 10^4
 * s 包含可打印的 ASCII 字符。
 * s 不包含任何开头或结尾空格。
 * s 里 至少 有一个词。
 * s 中的所有单词都用一个空格隔开。
 */
public class Solution {

    public String reverseWords(String s) {
        int n = s.length();
        char[] str = s.toCharArray();
        for (int start = 0, i = 0; i < n; i++) {
            if (str[i] == ' ') {
                reverse(str, start, i - 1);
                start = i + 1;
                continue;
            }
            if (i == n - 1) {
                reverse(str, start, i);
            }
        }
        return String.valueOf(str);
    }

    private void reverse(char[] str, int left, int right) {
        while (left < right) {
            swap(str, left++, right--);
        }
    }

    private void swap(char[] str, int i, int j) {
        char c = str[i];
        str[i] = str[j];
        str[j] = c;
    }

}
