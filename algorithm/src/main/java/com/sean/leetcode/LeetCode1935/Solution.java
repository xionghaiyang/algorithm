package com.sean.leetcode.LeetCode1935;

/**
 * @Author xionghaiyang
 * @Date 2025-09-15 06:51
 * @Description https://leetcode.cn/problems/maximum-number-of-words-you-can-type
 * 1935. 可以输入的最大单词数
 * 键盘出现了一些故障，有些字母键无法正常工作。
 * 而键盘上所有其他键都能够正常工作。
 * 给你一个由若干单词组成的字符串 text ，单词间由单个空格组成（不含前导和尾随空格）；
 * 另有一个字符串 brokenLetters ，由所有已损坏的不同字母键组成，返回你可以使用此键盘完全输入的 text 中单词的数目。
 * 1 <= text.length <= 10^4
 * 0 <= brokenLetters.length <= 26
 * text 由若干用单个空格分隔的单词组成，且不含任何前导和尾随空格
 * 每个单词仅由小写英文字母组成
 * brokenLetters 由 互不相同 的小写英文字母组成
 */
public class Solution {

    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] broken = new boolean[26];
        for (char c : brokenLetters.toCharArray()) {
            broken[c - 'a'] = true;
        }
        int res = 0;
        boolean isBroken = false;
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                if (!isBroken) {
                    res++;
                } else {
                    isBroken = false;
                }
            } else {
                if (broken[c - 'a']) {
                    isBroken = true;
                }
            }
        }
        if (!isBroken) {
            res++;
        }
        return res;
    }

}
