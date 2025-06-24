package com.sean.leetcode.LeetCode58;

/**
 * @Author xionghaiyang
 * @Date 2025-06-24 18:43
 * @Description https://leetcode.cn/problems/length-of-last-word
 * 58. 最后一个单词的长度
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。
 * 返回字符串中 最后一个 单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * 1 <= s.length <= 10^4
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 */
public class Solution {

    public int lengthOfLastWord(String s) {
        int n = s.length();
        int index = n - 1;
        while (index >= 0 && s.charAt(index) == ' ') {
            index--;
        }
        int res = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            index--;
            res++;
        }
        return res;
    }

}
